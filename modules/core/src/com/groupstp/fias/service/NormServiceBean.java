package com.groupstp.fias.service;

import com.groupstp.fias.entity.*;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import org.apache.commons.math3.analysis.function.Add;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service(NormService.NAME)
public class NormServiceBean implements NormService {

    @Inject
    private DataManager dataManager;

    private static final Map<Integer, Class> levels = new HashMap<Integer, Class>() {
        {
            put(1, Region.class);
            put(2, AdmRegion.class);
            put(3, MunRegion.class);
            put(4, Settlement.class);
            put(5, City.class);
            put(6, Locality.class );
            put(7, PlanningStructure.class);
            put(8, Street.class);
            put(9, Stead.class);
            put(10 , House.class);

        }
    };

    private static final Map<Class, Integer> reverse = new HashMap<Class, Integer>() {
        {
            levels.forEach((k, v) -> put(v, k));
        }
    };

    @Override
    public Address normalize(String srcAddress) {

        //разбиваем адрес на составные части, разделитель - запятая
        String[] components = srcAddress.split(",");
        //последний найденный компонент
        AtomicReference<Integer> lastType = new AtomicReference<>(0);
        //тип - компонент
        Map<Integer, Integer> types = new HashMap<>();
        for(int j=0; j<components.length;j++) {
            for (int i = 1; i < levels.size()+1; i++) {
                if (types.containsKey(i))
                    continue;
                String component = components[j].trim();
                int finalI = i;
                int finalJ = j;
                Arrays.stream(component.split(" ")).forEach(token -> {
                    if (checkLevel(token, levels.get(finalI).getSimpleName())) {
                        types.put(finalI, finalJ);
                        components[finalJ] = components[finalJ].replace(token, "").trim();
                        lastType.set(finalI);
                    }
                });
                if (types.containsKey(i)) {
                    break;
                }
            }
        }

        List<Entity> parents = new ArrayList<>();
        types.keySet().stream().sorted().forEach(type -> {
            final List<Entity> entities = new ArrayList<>();
            if(parents.size()==0)
                entities.addAll(findByNameTypeAndParent(components[types.get(type)], levels.get(type), null));
            else
                if(type==10)
                    parents.forEach(p-> entities.addAll(findHouse(components[types.get(type)], (FiasEntity) p)));
                else if (type==9);
                else
                    parents.forEach(p-> entities.addAll(findByNameTypeAndParent(components[types.get(type)], levels.get(type), (FiasEntity) p)));
            parents.clear();
            parents.addAll(new ArrayList<>(new HashSet<>(entities)));
        });

        if(parents.size()>0) {
            Address address = dataManager.load(Address.class)
                    .view("full")
                    .query("e.srcAddress=?1", srcAddress)
                    .optional()
                    .orElseGet(()-> {
                        Address a = dataManager.create(Address.class);
                        a.setSrcAddress(srcAddress);
                        return a;
                    });
            final List<House> houses = new ArrayList<>();
            parents.forEach(entity -> { if (entity.getClass().equals(House.class)) houses.add((House) entity); });
            address.setHouse(houses);
            final List<FiasEntity> entities = new ArrayList<>();
            parents.forEach(entity -> { if (entity instanceof FiasEntity && !(entity instanceof House)) entities.add((FiasEntity) entity);});
            address.setFiasEntity(entities);
            if(houses.size()>0)
                address.setNormAddress(constructAddress(houses, components, types.get(9)));
            else
                address.setNormAddress(constructAddress(entities, components, types.get(lastType)));
            System.out.println(address.getNormAddress());
            dataManager.commit(address);
            return address;
        }

        return null;
    }

    private List<House> findHouse(String component, FiasEntity p) {
        List<House> entities = dataManager.load(House.class)
                .query("e.housenum like ?1 and e.parentAdm=?2", component, p)
                .list();
        entities.addAll(dataManager.load(House.class)
                        .query("e.housenum like ?1 and e.parentMun=?2", component, p)
                        .list());
        return entities;
    }


    boolean checkLevel(String token, String type) {
        return dataManager.load(DivisionTag.class)
                .query("e.type=?1 and e.tag=?2", type, token.trim())
                .list()
                .size()>0;
    }


    List<String> determineComponentType(String component) {
        List<String> types = new ArrayList<>();
        //пробуем искать тип компонента адреса по ризнаку: г, обл и т.п.
        dataManager.loadValues("select e.type from fias_DivisionTag e where e.tag=:tag")
                .properties("type")
                .parameter("tag", component)
                .list().stream()
                .forEach(kve -> types.add(kve.getValue("type")));
        //если не нашли - ищем по наименованию
//        if(types.size()==0) {
//            Arrays.asList(tokens).forEach(s -> findByName(s).forEach(fiasEntity -> types.add(fiasEntity.getClass().getSimpleName())));
//        }
        return types;
    }

    List<FiasEntity> findByName(String name) {
        return findByNameAndType(name, FiasEntity.class);
    }

    private List<FiasEntity> findByNameAndType(String name, Class clazz) {
        List<FiasEntity> entities = dataManager.load(clazz)
                .query("e.name=?1", name)
                .list();
        if(entities.size()>0)
            return entities;
        //нечёткий поиск
        for(int i=0;i<name.length();i++) {
            for(int j=i;j<name.length();j++) {
                char[] _chars = name.toCharArray();
                _chars[i] = '_';
                _chars[j] = '_';
                String _name = new String(_chars);
                entities.addAll(dataManager.load(clazz)
                        .query("e.name like ?1", _name)
                        .list());
            }
        }
        return entities;
    }

    private List<FiasEntity> findByNameTypeAndParent(String name, Class clazz, FiasEntity parent) {
        String query = "select e from fias_"+clazz.getSimpleName()+" e";
        if(parent!=null) {
            //чёткий поиск
            List<String> variants = nameVariants(name);
            for (String variant: variants) {
                String join = " join e";
                for(int i = reverse.get(parent.getClass());i<reverse.get(clazz)-1;i++) {
                    join += ".parent";
                    List<FiasEntity> entities = dataManager.load(clazz)
                            .query(query + join.replace("parent", "parentAdm") +
                                    " p where p=:parent and e.name like :name")
                            .parameter("name", variant)
                            .parameter("parent", parent)
                            .list();
                    entities.addAll(dataManager.load(clazz)
                            .query(query + join.replace("parent", "parentMun") +
                                    " p where p=:parent and e.name like :name")
                            .parameter("name", variant)
                            .parameter("parent", parent)
                            .list());
                    if (entities.size() > 0)
                        return entities;
                }
            }
        } else {
            return findByNameAndType(name, clazz);
        }
        return new ArrayList<>();
    }

    List<String> nameVariants(String name) {
        List<String> variants = new ArrayList<>();
        variants.add(name);

        //одна или две опечатки, пропущена буква или две
        if(name.length()>4) {
            for (int i = 0; i < name.length(); i++) {
                for (int j = i; j < name.length(); j++) {
                    char[] _chars = name.toCharArray();
                    _chars[i] = '_';
                    _chars[j] = '_';
                    String _name = new String(_chars);
                    variants.add(_name);
                }
            }
        }

        //-й -я для числительных
        if(name.matches(".*(\\d+).*")) {
            variants.add(name.replaceAll("(\\d+)", "$1-й"));
            variants.add(name.replaceAll("(\\d+)", "$1-я"));
            variants.add(name.replaceAll("(\\d+)", "$1-ый"));
            variants.add(name.replaceAll("(\\d+)", "$1-ой"));
            variants.add(name.replaceAll("(\\d+)", "$1-ая"));
        }

        return variants;
    }

    private String constructAddress(List entities, String[] components, Integer lastComponent) {
        List<String> str = new ArrayList<>();
        entities.forEach(e -> {
            List<String> addrAdm = new ArrayList<>();
            List<String> addrMun = new ArrayList<>();
            Entity eAdm = (FiasEntity) e;
            Entity eMun = (FiasEntity) e;
            do {
                eAdm = dataManager.reload(eAdm, "parent");
                if(eAdm.getClass().equals(House.class)) {
                    addrAdm.add(0, "дом "+((House) eAdm).getHousenum());
                } else {
                    addrAdm.add(0, eAdm.getValue("shortname")+" "+eAdm.getValue("offname"));
                }
                eAdm = eAdm.getValue("parentAdm");
            } while (eAdm!=null);
            do {
                eMun = dataManager.reload(eMun, "parent");
                if(eMun.getClass().equals(House.class)) {
                    addrMun.add(0, "дом "+((House) eMun).getHousenum());
                } else {
                    addrMun.add(0, eMun.getValue("shortname")+" "+eMun.getValue("offname"));
                }
                eMun = eMun.getValue("parentMun");
            } while(eMun!=null);
            for(int c=lastComponent+1;c<components.length;c++) {
                addrAdm.add(components[c].trim());
                addrMun.add(components[c].trim());
            }
            str.add(String.join(", ", addrAdm));
            str.add(String.join(", ", addrMun));
        });
        return String.join("\n", str);
    }


}