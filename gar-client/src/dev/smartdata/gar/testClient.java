package dev.smartdata.gar;

import org.meridor.fias.beans.Region;
import org.meridor.fias.enums.FiasFile;
import org.meridor.fias.loader.XMLLoader;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class testClient {
    public static void main(String[] args) throws FileNotFoundException {

        Gar gar = Gar.withXMLDirectory(Paths.get("/home/lobo/projects/fias/xml/gar_xml"));

        //regions.forEach(r-> System.out.println(r.getName()));
        XMLLoader loader = new XMLLoader(Paths.get("/home/lobo/projects/fias/xml/gar_xml"));
        try {
            loader.loadReferenceTable(FiasFile.MUN_HIERARCHY, dev.smartdata.gar.MUNHIERARCHY.ITEMS.class).getITEM();
//            HOUSES houses = loader.loadReferenceTable(FiasFile.HOUSE, HOUSES.class);
//            Map<Long, HOUSES.HOUSE> houseMap = houses.getHOUSE().stream().collect(Collectors.toMap(HOUSES.HOUSE::getOBJECTID, house -> house, (t, t2) -> t));
//            PARAMS params = loader.loadReferenceTable(FiasFile.HOUSES_PARAMS, PARAMS.class);
//
//            Map<Long, Map<String, String>> csv = new HashMap<>();
//
//            System.out.println(String.join(";", "fias", "kn", "post"));
//
//            params.getPARAM().forEach(p -> {
//                if(!csv.containsKey(p.objectid)) {
//                    Map<String, String> line = new HashMap<>();
//                    HOUSES.HOUSE house = houseMap.get(p.objectid);
//                    String fias = house==null ? "none" : house.objectguid;
//                    line.put("fias", fias);
//                    csv.put(p.objectid, line);
//                }
//                if(p.typeid.longValue()==8) {
//                    csv.get(p.objectid).put("kn", p.value);
//                }
//                if(p.typeid.longValue()==5) {
//                    csv.get(p.objectid).put("post", p.value);
//                }
//            });
//
//            csv.forEach((l,v)->
//                    System.out.println(String.join(";", v.get("fias"), v.get("kn"), v.get("post")))
//            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
