package dev.smartdata.gar;

import org.meridor.fias.beans.Region;
import org.meridor.fias.enums.AddressLevel;

import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class Gar {

    private final GarAware GarAware;

    private Gar(GarAware GarAware) {
        this.GarAware = GarAware;
    }
    
    public static Gar withXMLDirectory(Path xmlDirectory) throws FileNotFoundException {
        return new Gar(new GarClient(xmlDirectory));
    }

    public List<Region> getRegions() {
        List<ADDRESSOBJECTS.OBJECT> rawRegions = loadByAddressLevel(AddressLevel.REGION);
        return rawRegions.stream().map(o -> new Region(
                getName(o),
                String.valueOf(o.getID()),
                null // TODO getFiscalCodes(o)
        )).collect(Collectors.toList());
    }

//    public List<Area> getAreas(Region region) {
//        List<ADDRESSOBJECTS.OBJECT> rawAreas = loadByAddressLevelAndParentCode(AddressLevel.AREA, region.getCode());
//        return rawAreas.stream().map(o -> new Area (
//                getName(o),
//                o.getAREACODE(),
//                region,
//                getFiscalCodes(o)
//        )).collect(Collectors.toList());
//    }
//
//    public List<City> getCities(Area area) {
//        List<ADDRESSOBJECTS.OBJECT> rawCities = loadByAddressLevelAndParentCode(AddressLevel.CITY, area.getCode());
//        return rawCities.stream().map(o -> new City (
//                getName(o),
//                o.getCITYCODE(),
//                area,
//                getFiscalCodes(o)
//        )).collect(Collectors.toList());
//    }
//
    private static String getName(ADDRESSOBJECTS.OBJECT object) {
        return object.getNAME() + " " + object.getTYPENAME();
    }

//    private static FiscalCodes getFiscalCodes(ADDRESSOBJECTS.OBJECT object) {
//        return new FiscalCodes(
//                object.getPOSTALCODE(),
//                object.getIFNSFL(),
//                object.getIFNSUL(),
//                object.getOKATO(),
//                object.getOKTMO()
//        );
//    }

    public List<ADDRESSOBJECTS.OBJECT> loadByAddressLevel(AddressLevel addressLevel) {
        return GarAware.load(getPredicateByAddressLevelAndParentCode(addressLevel, null));
    }
    
    public List<ADDRESSOBJECTS.OBJECT> loadByAddressLevelAndParentCode(AddressLevel addressLevel, String parentCode) {
        return GarAware.load(getPredicateByAddressLevelAndParentCode(addressLevel, parentCode));
    }
    
    private static Predicate<ADDRESSOBJECTS.OBJECT> getPredicateByAddressLevelAndParentCode(AddressLevel addressLevel, String parentCode) {
        String addressLevelVal = addressLevel.getAddressLevel();
        switch (addressLevel) {
            default:
            case REGION: return o -> o.getLEVEL().equals(addressLevelVal);
//            case AUTONOMY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getREGIONCODE().equals(parentCode);
//            case AREA: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getREGIONCODE().equals(parentCode);
//            case CITY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getAREACODE().equals(parentCode);
//            case COMMUNITY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getCITYCODE().equals(parentCode);
//            case LOCATION: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getCTARCODE().equals(parentCode);
//            case STREET: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getPLACECODE().equals(parentCode);
//            case SUPPLEMENTARY_TERRITORY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getSTREETCODE().equals(parentCode);
//            case CHILD_SUPPLEMENTARY_TERRITORY: return o -> o.getAOLEVEL().equals(addressLevelVal) && o.getEXTRCODE().equals(parentCode);
        }
    }

}
