package com.groupstp.fias.service;

import com.groupstp.fias.entity.*;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.MetadataTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service(NormService.NAME)
public class NormServiceBean implements NormService {

    private static final Logger log = LoggerFactory.getLogger(NormServiceBean.class);
    @Inject
    private DataManager dataManager;

    private static final HashMap<Integer, Class<? extends StandardEntity>> levels = new HashMap<Integer, Class<? extends StandardEntity>>() {
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
            put(10, House.class);
        }
    };

    private static final Map<Class<?>, Integer> reverse = new HashMap<Class<?>, Integer>() {
        {
            levels.forEach((k, v) -> put(v, k));
        }
    };
    @Inject
    private MetadataTools metadataTools;

    @Override
    public Address normalize(String srcAddress) {

        //разбиваем адрес на составные части, разделитель - запятая
        String[] components = srcAddress.split(",");
        //последний найденный компонент
        AtomicReference<Integer> lastType = new AtomicReference<>(0);
        //тип - компонент
        Map<Integer, Integer> types = new HashMap<>();
        for(int j=0; j<components.length;j++) {
            components[j] = components[j].trim();
            for (int i = 1; i < levels.size()+1; i++) {
                if (types.containsKey(i))
                    continue;
                String component = components[j].trim();
                int finalI = i;
                int finalJ = j;
                String[] tokens = component.split(" ");
                if(tokens.length==1 && component.indexOf(".")>0) {
                    tokens = component.replace(".", ". ").split(" ");
                }
                if(tokens.length>1) {
                    String[] finalTokens = tokens;
                    Arrays.stream(tokens).forEach(token -> {
                        if (checkLevel(token, levels.get(finalI).getSimpleName())) {
                            types.put(finalI, finalJ);
                            String componentWithoutToken = Arrays.stream(finalTokens).filter(s -> !s.equals(token)).collect(Collectors.joining(" "));
                            components[finalJ] = componentWithoutToken.trim();
                        }
                    });
                }
                if (types.containsKey(i)) {
                    break;
                }
            }
        }

        log.info("-------------------------------------------------");
        log.info(srcAddress);
        log.info("found types {}", types);

        List<StandardEntity> parents = new ArrayList<>();
        types.keySet().stream().sorted().forEach(type -> {
            final List<StandardEntity> entities = new ArrayList<>();
            if(parents.size()==0) {
                if (type < 9)
                    entities.addAll(findByNameTypeAndParent(components[types.get(type)], levels.get(type), null));
                else
                    return;
            }
            else {
                if (type == 10)
                    parents.forEach(p -> entities.addAll(findHouseOrStead(components[types.get(type)], (FiasEntity) p)));
                else
                    parents.forEach(p -> entities.addAll(findByNameTypeAndParent(components[types.get(type)], levels.get(type), (FiasEntity) p)));
            }
            if (entities.size()==0)
                return;

            lastType.set(type);

            parents.clear();
            parents.addAll(new ArrayList<>(new HashSet<>(entities)));
            log.info("found components of type {}", levels.get(type).getSimpleName());
            parents.forEach(p->log.info(metadataTools.getInstanceName(p)));
            //если у следующей компоненты нет типа
            int nc = types.get(type)+1;
            while (components.length>nc-1 && !types.containsValue(nc)) {
                for(int i=type+1;i<levels.size()+1;i++) {
                    int finalNc = nc;
                    int finalI = i;
                    entities.clear();
                    if(i<9)
                        parents.forEach(p->entities.addAll(findByNameTypeAndParent(components[finalNc], levels.get(finalI), (FiasEntity) p)));
                    else
                        parents.forEach(p->entities.addAll(findHouseOrStead(components[finalNc], (FiasEntity) p)));
                    if(entities.size()>0) {
                        parents.clear();
                        parents.addAll(new ArrayList<>(new HashSet<>(entities)));
                        log.info("found components of type {} by name", levels.get(i).getSimpleName());
                        parents.forEach(p->log.info(metadataTools.getInstanceName(p)));
                        lastType.set(i);
                        break;
                    }
                }
                nc++;
            }
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

            final List<Stead> steads = new ArrayList<>();
            parents.forEach(entity -> {if(entity.getClass().equals(Stead.class)) steads.add((Stead) entity); });
            address.setStead(steads);

            final List<FiasEntity> entities = new ArrayList<>();
            parents.forEach(entity -> { if (entity instanceof FiasEntity) entities.add((FiasEntity) entity);});
            address.setFiasEntity(entities);
            if(houses.size()>0)
                address.setNormAddress(constructAddress(new ArrayList<>(houses), components, types.get(10)));
            else if(steads.size()>0)
                address.setNormAddress(constructAddress(new ArrayList<>(steads), components, types.get(10)));
            else
                address.setNormAddress(constructAddress(new ArrayList<>(entities), components, types.get(lastType.get())));
            log.info(address.getNormAddress());
            dataManager.commit(address);
            return address;
        }
        return null;
    }

    private List<StandardEntity> findHouseOrStead(String component, FiasEntity p) {
        List<StandardEntity> entities = new ArrayList<>();
        for (String variant:
             numVariants(component)) {
            entities.addAll(dataManager.loadList(LoadContext.create(House.class).
                    setQuery(LoadContext.createQuery("select e from fias_House e where lower(e.housenum) like lower(:housenum) and e.parentAdm=:parent")
                            .setParameter("housenum", variant)
                            .setParameter("parent", p)
                            .setCacheable(true))
                    .setView("parent")));
            entities.addAll(dataManager.loadList(LoadContext.create(House.class).
                    setQuery(LoadContext.createQuery("select e from fias_House e where lower(e.housenum) like lower(:housenum) and e.parentMun=:parent")
                            .setParameter("housenum", variant)
                            .setParameter("parent", p)
                            .setCacheable(true))
                    .setView("parent")));
            if(entities.size()==0) {
                entities.addAll(dataManager.loadList(LoadContext.create(Stead.class).
                        setQuery(LoadContext.createQuery("select e from fias_Stead e where lower(e.number) like lower(:number) and e.parentAdm=:parent")
                                .setParameter("number", variant)
                                .setParameter("parent", p)
                                .setCacheable(true))
                        .setView("parent")));
                entities.addAll(dataManager.loadList(LoadContext.create(Stead.class).
                        setQuery(LoadContext.createQuery("select e from fias_Stead e where lower(e.number) like lower(:number) and e.parentMun=:parent")
                                .setParameter("number", variant)
                                .setParameter("parent", p)
                                .setCacheable(true))
                        .setView("parent")));
            }
            if(entities.size()>0)
                return entities;
        }
        return entities;
    }


    Map<String, String> tags = new HashMap<>();

    boolean checkLevel(String token, String type) {
        token = token.trim().toLowerCase();
        if(tags.containsKey(token+type))
            return true;
        List<DivisionTag> divisionTags = dataManager.load(DivisionTag.class)
                .query("e.type=?1 and lower(e.tag)=lower(?2)", type, token.trim())
                .list();
        divisionTags.forEach(t->tags.put(t.getTag().toLowerCase()+t.getType(), t.getType()));
        return divisionTags.size()>0;
    }


    List<String> determineComponentType(String component) {
        List<String> types = new ArrayList<>();
        //пробуем искать тип компонента адреса по ризнаку: г, обл и т.п.
        dataManager.loadValues("select e.type from fias_DivisionTag e where lower(e.tag)=lower(:tag)")
                .properties("type")
                .parameter("tag", component)
                .list()
                .forEach(kve -> types.add(kve.getValue("type")));
        //если не нашли - ищем по наименованию
//        if(types.size()==0) {
//            Arrays.asList(tokens).forEach(s -> findByName(s).forEach(fiasEntity -> types.add(fiasEntity.getClass().getSimpleName())));
//        }
        return types;
    }

    List<StandardEntity> findByName(String name) {
        return findByNameAndType(name, FiasEntity.class);
    }

    private List<StandardEntity> findByNameAndType(String name,  Class<? extends StandardEntity> clazz) {
        List<StandardEntity> entities = new ArrayList<>(dataManager.loadList(
                LoadContext.create(clazz).setQuery(
                        LoadContext.createQuery("select e from fias_" + clazz.getSimpleName() + " e where lower(e.name)=lower(:name)")
                                .setParameter("name", name)
                                .setCacheable(true))));
        if(entities.size()>0)
            return entities;
        //нечёткий поиск
        for(int i=0;i<name.length();i++) {
            for(int j=i;j<name.length();j++) {
                char[] _chars = name.toCharArray();
                _chars[i] = '_';
                _chars[j] = '_';
                String _name = new String(_chars);
                entities.addAll(dataManager.loadList(
                        LoadContext.create(clazz).setQuery(
                            LoadContext.createQuery("select e from fias_"+clazz.getSimpleName()+" e where lower(e.name) like lower(:name)")
                                .setParameter("name", _name)
                                .setCacheable(true))));
            }
        }
        return entities;
    }

    private List<StandardEntity> findByNameTypeAndParent(String name, Class<? extends StandardEntity> clazz, FiasEntity parent) {
        String query = "select e from fias_"+clazz.getSimpleName()+" e";
        List<StandardEntity> entities = new ArrayList<>();
        if(parent!=null) {
            //чёткий поиск
            List<String> variants = nameVariants(name);
            for (String variant: variants) {
                StringBuilder join = new StringBuilder(" join e");
                for(int i = reverse.get(parent.getClass());i<reverse.get(clazz);i++) {
                    join.append(".parent");
                    entities.addAll(dataManager.loadList(LoadContext.create(clazz).setQuery(LoadContext.createQuery((query + join.toString().replace("parent", "parentAdm") +
                            " p where p=:parent and lower(e.name) like lower(:name)"))
                            .setParameter("name", variant)
                            .setParameter("parent", parent)
                            .setCacheable(true))
                        .setView("parent")));
                    entities.addAll(dataManager.loadList(LoadContext.create(clazz).setQuery(LoadContext.createQuery((query + join.toString().replace("parent", "parentMun") +
                                    " p where p=:parent and lower(e.name) like lower(:name)"))
                            .setParameter("name", variant)
                            .setParameter("parent", parent)
                            .setCacheable(true))
                        .setView("parent")));

                    if (entities.size() > 0 && !variant.matches(".*(\\d+).*"))
                        return entities;
                }
            }
        } else {
            return findByNameAndType(name, clazz);
        }
        return entities;
    }

    List<String> numVariants(String num) {
        List<String> variants = new ArrayList<>();

        variants.add(num);

        variants.add(num.replaceAll("-", "")
                .replaceAll(" ", "")
                .replaceAll("\"", ""));

        return variants.stream().distinct().collect(Collectors.toList());
    }

    List<String> nameVariants(String name) {
        List<String> variants = new ArrayList<>();
        variants.add(name);

        //одна или две опечатки, пропущена буква или две
        if(name.length()>4) {
            for (int i = 0; i < name.length(); i++) {
                for (int j = i; j < name.length(); j++) {
                    char[] _chars = name.toCharArray();
                    if(!Character.isDigit(_chars[i]))
                        _chars[i] = '_';
                    if(!Character.isDigit(_chars[j]))
                        _chars[j] = '_';
                    if(Character.isDigit(_chars[i]) && Character.isDigit(_chars[j]))
                        continue;
                    String _name = new String(_chars);
                    variants.add(_name);
                }
            }
        }

        //-й -я для числительных
        if(name.matches(".*(\\d+).*")) {
            if(name.matches(".*(-й|-я|-ый|-ой|-ая).*")) {
                variants.add(name.replaceAll("(-й|-я|-ый|-ой|-ая)", ""));
                if(name.matches(".*(-й).*")) {
                    variants.add(name.replaceAll("(\\d+)-й", "$1-ый"));
                    variants.add(name.replaceAll("(\\d+)-й", "$1-ой"));
                } else if (name.matches(".*(-ый|-ой).*")) {
                    variants.add(name.replaceAll("(\\d+)-(-ый|-ой)", "$1-й"));
                } else if (name.matches(".*(-я).*")) {
                    variants.add(name.replaceAll("(\\d+)-я", "$1-ая"));
                } else if (name.matches(".*(-ая).*")) {
                    variants.add(name.replaceAll("(\\d+)-ая", "$1-я"));
                }
            } else {
                variants.add(name.replaceAll("(\\d+)", "$1-й"));
                variants.add(name.replaceAll("(\\d+)", "$1-я"));
                variants.add(name.replaceAll("(\\d+)", "$1-ый"));
                variants.add(name.replaceAll("(\\d+)", "$1-ой"));
                variants.add(name.replaceAll("(\\d+)", "$1-ая"));
            }
        }

        return variants;
    }

    private String constructAddress(List<StandardEntity> entities, String[] components, Integer lastComponent) {
        List<String> str = new ArrayList<>();
        entities.forEach(e -> {
            List<String> addrAdm = new ArrayList<>();
            List<String> addrMun = new ArrayList<>();
            StandardEntity eAdm = e;
            StandardEntity eMun = e;
            do {
                Long garId = eAdm.getValue("garId");
                eAdm = dataManager.load(eAdm.getClass()).view("parent").query("e.garId=?1", garId).one();
                if(eAdm.getClass().equals(House.class)) {
                    addrAdm.add(0, "дом "+((House) eAdm).getHousenum());
                } else if (eAdm.getClass().equals(Stead.class)) {
                    addrAdm.add(0, "уч. "+((Stead) eAdm).getNumber());
                } else {
                    addrAdm.add(0, eAdm.getValue("shortname")+" "+eAdm.getValue("offname"));
                }
                eAdm = eAdm.getValue("parentAdm");
            } while (eAdm!=null);
            do {
                Long garId = eMun.getValue("garId");
                eMun = dataManager.load(eMun.getClass()).view("parent").query("e.garId=?1", garId).one();
                if(eMun.getClass().equals(House.class)) {
                    addrMun.add(0, "дом "+((House) eMun).getHousenum());
                } else if (eMun.getClass().equals(Stead.class)) {
                    addrMun.add(0, "уч. "+((Stead) eMun).getNumber());
                } else {
                    addrMun.add(0, eMun.getValue("shortname")+" "+eMun.getValue("offname"));
                }
                eMun = eMun.getValue("parentMun");
            } while(eMun!=null);
//            for(int c=lastComponent+1;c<components.length;c++) {
//                addrAdm.add(components[c].trim());
//                addrMun.add(components[c].trim());
//            }
            str.add(String.join(", ", addrAdm));
            str.add(String.join(", ", addrMun));
        });
        return String.join("\n", str);
    }


}