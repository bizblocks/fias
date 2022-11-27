package com.groupstp.fias.web.screens;

import com.groupstp.fias.entity.enums.FiasEntityStatus;
import com.groupstp.fias.service.FiasReadService;
import com.groupstp.fias.utils.client.AddressObjectFork;
import com.groupstp.fias.utils.client.GarClientFork;
import com.groupstp.fias.utils.client.PartialUnmarshallerFork;
import com.groupstp.fias.utils.config.FiasServiceConfig;
import com.groupstp.fias.entity.*;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.Configuration;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.PersistenceHelper;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.executors.BackgroundTask;
import com.haulmont.cuba.gui.executors.BackgroundTaskHandler;
import com.haulmont.cuba.gui.executors.BackgroundWorker;
import com.haulmont.cuba.gui.executors.TaskLifeCycle;
import dev.smartdata.gar.ADDRESSOBJECTS;
import dev.smartdata.gar.ADMHIERARCHY.ITEMS;
import dev.smartdata.gar.HOUSES;
import dev.smartdata.gar.PARAMS;
import dev.smartdata.gar.STEADS;
import org.meridor.fias.enums.AddressLevel;
import org.meridor.fias.enums.FiasFile;
import org.meridor.fias.loader.XMLLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.Predicate;

import static org.meridor.fias.enums.FiasFile.*;

public class Fiasclientv2 extends AbstractWindow {
    private static final Logger log = LoggerFactory.getLogger("FiasClient");
    @Inject
    private BackgroundWorker backgroundWorker;
    @Inject
    private FiasReadService fiasReadService;

    @Inject
    private CheckBox regionCheckField;
    @Inject
    private CheckBox admRegionCheckField;
    @Inject
    private CheckBox munRegionCheckField;
    @Inject
    private CheckBox cityCheckField;
    @Inject
    private CheckBox localityCheckField;
    @Inject
    private CheckBox planStructCheckField;
    @Inject
    private CheckBox streetCheckField;
    @Inject
    private CheckBox houseCheckField;
//    @Inject
//    private LookupField regionField;
//    @Inject
//    private LookupField cityField;
    @Inject
    private ProgressBar progressBar;
    @Inject
    private Label progressLabel;
    @Inject
    private Button loadDataBtn;
    @Inject
    private Button pauseLoadingDataBtn;
    @Inject
    private Button resetProgressButton;

    private BackgroundTaskHandler taskHandler;

    @Inject
    private DataManager dataManager;
    @Inject
    private Configuration configuration;

    private GarClientFork garClient;
    private Path xmlDirectory;

    private long progress;

    private boolean taskWasStarted = false;
    private Class lastClassWorked;
    @Inject
    private CheckBox settlementCheckField;

    Map<Long, Long> admParents;
    Map<Long, Long> munParents;
    Map<Long, Map<String, Object>> parameters;
    @Inject
    private CheckBox steadCheckField;


    @Override
    public void init(Map<String, Object> params) {
//        regionField.setOptionsMap(getMapOfRegionsFromFias());
        setupCloseWindowListeners();
        setupChangeRegionLkf();
    }

    public void onBtnClick() {
        HashMap<Object, Object> options = new HashMap<>();
        options.put(AddressLevel.REGION, regionCheckField.getValue());
        options.put(AddressLevel.ADMINISTRATIVE_REGION, admRegionCheckField.getValue());
        options.put(AddressLevel.MUNICIPAL_REGION, munRegionCheckField.getValue());
        options.put(AddressLevel.SETTLEMENT, settlementCheckField.getValue());
        options.put(AddressLevel.CITY, cityCheckField.getValue());
        options.put(AddressLevel.LOCALITY, localityCheckField.getValue());
        options.put(AddressLevel.PLANNING_STRUCTURE, planStructCheckField.getValue());
        options.put(AddressLevel.STREET, streetCheckField.getValue());
        options.put(AddressLevel.STEAD, steadCheckField.getValue());
        options.put("needLoadHouses", houseCheckField.getValue());
//        if (regionField.getValue() != null)
//            options.put("regionId", (regionField.getValue()));
//        if (cityField.getValue() != null)
//            options.put("cityId", (cityField.getValue()));

        taskHandler = backgroundWorker.handle(createBackgroundTask(options));
        taskHandler.execute();
        setupControlButtons(false);
    }

    private BackgroundTask<Integer, Void> createBackgroundTask(Map<Object, Object> options) {
        return new BackgroundTask<Integer, Void>(TimeUnit.HOURS.toSeconds(24), this) {
            int percentValue = 1;

            //Class<? extends FiasEntity> clazz;
            Class clazz;
            Function<ADDRESSOBJECTS.OBJECT, Long> getCodeFunction;
            Predicate<ADDRESSOBJECTS.OBJECT> predicate;

            int batchSize = configuration.getConfig(FiasServiceConfig.class).getBatchSize();


            @Override
            public Void run(TaskLifeCycle<Integer> taskLifeCycle) throws Exception {
                taskWasStarted = true;
                String path = configuration.getConfig(FiasServiceConfig.class).getPath();
                xmlDirectory = Paths.get(path);
                garClient = new GarClientFork(xmlDirectory);
                Path filePath = getPathByPattern(ADDRESS_OBJECTS.getName());

                loadParents();

                //грузим Regions
                if ((boolean) options.getOrDefault(AddressLevel.REGION, true)) {
                    loadObjects(Region.class, AddressLevel.REGION, filePath, taskLifeCycle);
                }

                //грузим Administrative regions
                if ((boolean) options.getOrDefault(AddressLevel.ADMINISTRATIVE_REGION, true)) {
                    loadObjects(AdmRegion.class, AddressLevel.ADMINISTRATIVE_REGION, filePath, taskLifeCycle);
                }

                //грузим Municipal regions
                if ((boolean) options.getOrDefault(AddressLevel.MUNICIPAL_REGION, true)) {
                    loadObjects(MunRegion.class, AddressLevel.MUNICIPAL_REGION, filePath, taskLifeCycle);
                }

                //грузим Settlements
                if ((boolean) options.getOrDefault(AddressLevel.SETTLEMENT, true)) {
                    loadObjects(Settlement.class, AddressLevel.SETTLEMENT, filePath, taskLifeCycle);
                }

                //грузим Cities
                if ((boolean) options.getOrDefault(AddressLevel.CITY, true)) {
                    loadObjects(City.class, AddressLevel.CITY, filePath, taskLifeCycle);
                }

                //грузим Locality
                if ((boolean) options.getOrDefault(AddressLevel.LOCALITY, true)) {
                    loadObjects(Locality.class, AddressLevel.LOCALITY, filePath, taskLifeCycle);
                }

                //грузим PLANNING_STRUCTURE
                if ((boolean) options.getOrDefault(AddressLevel.PLANNING_STRUCTURE, true)) {
                    loadObjects(PlanningStructure.class, AddressLevel.PLANNING_STRUCTURE, filePath, taskLifeCycle);
                }

                //грузим Streets
                if ((boolean) options.getOrDefault(AddressLevel.STREET, true)) {
                    loadObjects(Street.class, AddressLevel.STREET, filePath, taskLifeCycle);
                }

                //грузим Steads
                if ((boolean) options.getOrDefault(AddressLevel.STEAD, true)) {
                    loadSteads(taskLifeCycle);
                }

                //грузим Houses
                if ((boolean) options.getOrDefault("needLoadHouses", true)) {
                    loadHouses(taskLifeCycle);
                }

                return null;
            }

            private void loadSteads(TaskLifeCycle<Integer> taskLifeCycle) throws IOException, InterruptedException {
                clazz = Stead.class;
                lastClassWorked = clazz;
                Path filePathSteads = getPathByPattern(STEAD.getName());
                progress = getConfigProgress(clazz);
                PartialUnmarshallerFork<STEADS.STEAD> pum = garClient.getUnmarshallerFork(STEADS.STEAD.class, STEAD,  progress);
                sendToLog(MessageFormat.format("Start Creating objects of class {0}", clazz.getSimpleName()));
                List<Stead> steads = new ArrayList<>();
                while (pum.hasNext()) {
                    if (taskLifeCycle.isCancelled() || taskLifeCycle.isInterrupted()) {
                        break;
                    } else {
                        final STEADS.STEAD fiasStead = pum.next();
                        Stead stead = getSteadEntity(fiasStead);
                        stead = processSteadEntity(fiasStead, stead);
                        if (stead != null)
                            steads.add(stead);
                    }
                    progress = pum.getInputStream().getProgress();
                    percentValue = (int) (Math.abs((double) progress / (double) Files.size(filePathSteads) * 100));
                    taskLifeCycle.publish(percentValue);
                    if (steads.size() == batchSize) {
                        CommitContext commitContext = new CommitContext(steads);
                        dataManager.commit(commitContext);
                        log.debug(MessageFormat.format("{0} new Steads were processed (class = {1}), reached {2}% of file",
                                steads.size(),
                                clazz.getSimpleName(),
                                percentValue));
                        steads.clear();
                    }
                }
                dataManager.commit(new CommitContext(steads));
                sendToLog(MessageFormat.format("Finished Creating objects of class {0}", clazz.getSimpleName()));
            }
            
            private void loadHouses(TaskLifeCycle<Integer> taskLifeCycle) throws IOException, InterruptedException, JAXBException {
                loadParameters();
                clazz = House.class;
                lastClassWorked = clazz;
                Path filePathHouses = getPathByPattern(HOUSE.getName());
                progress = getConfigProgress(clazz);
                PartialUnmarshallerFork<HOUSES.HOUSE> pum = garClient.getUnmarshallerFork(HOUSES.HOUSE.class, FiasFile.HOUSE, progress);
                sendToLog(MessageFormat.format("Start creating objects of class {0}", clazz.getSimpleName()));
                Map<UUID, House> houses = new HashMap<>();
                while (pum.hasNext()) {
                    if (taskLifeCycle.isCancelled() || taskLifeCycle.isInterrupted()) {
                        break;
                    } else {
                        final HOUSES.HOUSE fiasHouse = pum.next();
                        UUID uuid = UUID.fromString(fiasHouse.getOBJECTGUID());
                        if(houses.containsKey(uuid) ) {
                            continue;
                        }
                        House house = getHouseEntity(fiasHouse);
                        house = processHouseEntity(fiasHouse, house);
                        houses.put(uuid, house);
                    }
                    progress = pum.getInputStream().getProgress();
                    percentValue = (int) (Math.abs((double) progress / (double) Files.size(filePathHouses) * 100));
                    taskLifeCycle.publish(percentValue);
                    if (houses.size() == batchSize) {
                        CommitContext commitContext = new CommitContext(houses.values());
                        dataManager.commit(commitContext);
                        log.debug(MessageFormat.format("{0} new Houses were processed (class = {1}), reached {2}% of file",
                                houses.size(),
                                clazz.getSimpleName(),
                                percentValue));
                        houses.clear();
                    }
                }
                dataManager.commit(new CommitContext(houses.values()));
                sendToLog(MessageFormat.format("Finished Creating objects of class {0}", clazz.getSimpleName()));
            }

            void loadParents() throws JAXBException, IOException {
                if(munParents!=null && admParents!=null)
                    return;
                munParents = new HashMap<>();
                admParents = new HashMap<>();

                XMLLoader loader = new XMLLoader(xmlDirectory);
                loader.loadReferenceTable(ADM_HIERARCHY, ITEMS.class).getITEM().forEach(p -> {
                        admParents.put(p.getOBJECTID(), p.getPARENTOBJID());
                });
                loader.loadReferenceTable(MUN_HIERARCHY, dev.smartdata.gar.MUNHIERARCHY.ITEMS.class).getITEM().forEach(p -> {
                        munParents.put(p.getOBJECTID(), p.getPARENTOBJID());
                });
            }

            void loadParameters() throws JAXBException, IOException {
                if(parameters!=null)
                    return;
                parameters = new HashMap<>();
                XMLLoader loader = new XMLLoader(xmlDirectory);
                loader.loadReferenceTable(HOUSES_PARAMS, PARAMS.class).getPARAM().forEach(param -> {
                    long id = param.getOBJECTID();
                    if (!parameters.containsKey(id))
                        parameters.put(id, new HashMap<>());

                    long typeId = param.getTYPEID().longValue();
                    switch ((int)typeId) {
                        case 1:
                            parameters.get(id).put("ifnsfl", param.getVALUE());
                            break;
                        case 2:
                            parameters.get(id).put("ifnsul", param.getVALUE());
                            break;
                        case 3:
                            parameters.get(id).put("terrifnsfl", param.getVALUE());
                            break;
                        case 4:
                            parameters.get(id).put("terrifnsul", param.getVALUE());
                            break;
                        case 5:
                            parameters.get(id).put("postalcode", param.getVALUE());
                            break;
                        case 6:
                            parameters.get(id).put("okato", param.getVALUE());
                            break;
                        case 7:
                            parameters.get(id).put("oktmo", param.getVALUE());
                            break;
                        case 8:
                            parameters.get(id).put("cadastralNumber", param.getVALUE());
                            break;
                    }
                });
            }

            void loadObjects(Class clazz, AddressLevel level, Path filePath, TaskLifeCycle<Integer> taskLifeCycle) throws Exception {
                this.clazz = clazz;
                int batchSize = configuration.getConfig(FiasServiceConfig.class).getBatchSize();
                lastClassWorked = clazz;
                sendToLog(MessageFormat.format("Start Creating objects of class {0}", clazz.getSimpleName()));
                getCodeFunction = ADDRESSOBJECTS.OBJECT::getOBJECTID;
                predicate = o -> o.getLEVEL() != null && o.getLEVEL().equals(level.getAddressLevel());
                progress = getConfigProgress(clazz);
                percentValue = (int) (Math.abs((double) progress / (double) Files.size(filePath) * 100));
                taskLifeCycle.publish(percentValue);
                while (progress <= Files.size(filePath)) {
                    if (taskLifeCycle.isCancelled() || taskLifeCycle.isInterrupted()) {
                        //updateConfigProgress(clazz, progress);
                        break;
                    } else {
                        CommitContext commitContext = new CommitContext();
                        sendToLog("Searching next batch of objects...");
                        List<AddressObjectFork> addressObjectForks = garClient.loadList(predicate, filePath, progress, batchSize);
                        if (addressObjectForks.size() == 0) {
                            sendToLog(MessageFormat.format("0 new Fias Entities were processed (class = {1}), reached 100% of file",
                                    clazz.getSimpleName()));
                            break;
                        } else {
                            for (AddressObjectFork addressObjectFork : addressObjectForks) {
                                FiasEntity fe = loadFiasEntity(clazz, addressObjectFork.getObject());
                                fe.setGarId(getCodeFunction.apply(addressObjectFork.getObject()));
                                commitContext.addInstanceToCommit(fe);
                                progress = addressObjectFork.getOffset();
                                percentValue = (int) (Math.abs((double) progress / (double) Files.size(filePath) * 100));
                                taskLifeCycle.publish(percentValue);
                            }
                            dataManager.commit(commitContext);
                            sendToLog(MessageFormat.format("{0} new Fias Entities were processed (class = {1}), reached {2}% of file",
                                    addressObjectForks.size(),
                                    clazz.getSimpleName(),
                                    percentValue));
                        }
                    }
                }
                sendToLog(MessageFormat.format("Finished Creating objects of class {0}", clazz.getSimpleName()));
            }

            @Override
            public void done(Void result) {
                showNotification(getMessage("loadDone"));
                if (clazz != null) {
                    try {
                        updateConfigProgress(clazz, 0);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                progressBar.setValue(1d);
                progressLabel.setValue("100%");
                setupControlButtons(true);
                super.done(result);
            }

            @Override
            public void progress(List<Integer> changes) {
                progressBar.setValue((changes.get(changes.size() - 1) / 100d));
                progressLabel.setValue(changes.get(changes.size() - 1) + " %");
            }

            @Override
            public void canceled() {
                try {
                    updateConfigProgress(clazz, progress);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                setupControlButtons(true);
                sendToLog("Task was cancelled");
                showNotification(getMessage("loadCanceled"));
            }
        };
    }

    public void onPauseLoadingDataBtnClick() {
        taskHandler.cancel();
    }

    private long getConfigProgress(Class clazz) {
        long progress = 0;
        try {
            Method method = FiasServiceConfig.class.getMethod("getProgress"+clazz.getSimpleName());
            progress = (Long) method.invoke(configuration.getConfig(FiasServiceConfig.class));
        } catch (Exception e) {
            return 0;
        }
        return progress;
    }

    private void updateConfigProgress(Class clazz, long progress) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = FiasServiceConfig.class.getMethod("setProgress"+clazz.getSimpleName(), long.class);
        method.invoke(configuration.getConfig(FiasServiceConfig.class), progress);
    }

    private void resetConfigProgress() {
        FiasServiceConfig config = configuration.getConfig(FiasServiceConfig.class);
        config.setProgressRegion(0);
        config.setProgressAdmRegion(0);
        config.setProgressMunRegion(0);
        config.setProgressSettlement(0);
        config.setProgressCity(0);
        config.setProgressLocality(0);
        config.setProgressPlanningStructure(0);
        config.setProgressStreet(0);
        config.setProgressHouse(0);
        config.setProgressStead(0);
    }

    private Stead getSteadEntity(STEADS.STEAD fiasStead) {
        final UUID entityId;
        final String steadguid = fiasStead.getOBJECTGUID();
        try {
            entityId = UUID.fromString(steadguid);
        } catch (IllegalArgumentException e) {
            log.warn("Wrong entity ID format (HOUSEGUID) for element {} with id: {}",
                    STEADS.STEAD.class.getSimpleName(), steadguid);
            return null;
        }

        return dataManager.load(Stead.class)
                .id(entityId)
                .view("parent")
                .optional()
                .orElseGet(() -> {
                    final Stead newEntity = dataManager.create(Stead.class);
                    newEntity.setId(entityId);
                    return newEntity;
                });
    }

    private Stead processSteadEntity(STEADS.STEAD fiasStead, Stead entity) {
        long parentIdAdm = admParents.getOrDefault(fiasStead.getOBJECTID().longValue(), 0l);
        long parentIdMun = munParents.getOrDefault(fiasStead.getOBJECTID().longValue(), 0l);

        final FiasEntity parentEntityAdm = dataManager.load(FiasEntity.class)
                .query("e.garId=?1", parentIdAdm)
                .optional()
                .orElse(null);
        final FiasEntity parentEntityMun = dataManager.load(FiasEntity.class)
                .query("e.garId=?1", parentIdMun)
                .optional()
                .orElse(null);

        Stead stead = entity;
        stead.setValue("parentAdm", parentEntityAdm, true);
        stead.setValue("parentMun", parentEntityMun, true);
        stead.setValue("garId", fiasStead.getOBJECTID().longValue(), true);
        stead.setValue("number", fiasStead.getNUMBER(), true);
        stead.setValue("isactive", fiasStead.getISACTIVE().intValue()==1, true);
        stead.setValue("isactual", fiasStead.getISACTUAL().intValue()==1, true);
        stead.setValue("startdate", fiasStead.getSTARTDATE().toGregorianCalendar().getTime(), true);
        stead.setValue("updatedate", fiasStead.getUPDATEDATE().toGregorianCalendar().getTime(), true);
        stead.setValue("enddate", fiasStead.getENDDATE().toGregorianCalendar().getTime(), true);
        stead.setValue("addressLevel", 9);

        return stead;
    }

    private House getHouseEntity(HOUSES.HOUSE fiasHouse) {
        final UUID entityId;
        final String houseguid = fiasHouse.getOBJECTGUID();
        try {
            entityId = UUID.fromString(houseguid);
        } catch (IllegalArgumentException e) {
            log.warn("Wrong entity ID format (HOUSEGUID) for element {} with id: {}",
                    HOUSES.HOUSE.class.getSimpleName(), houseguid);
            return null;
        }

        return dataManager.load(House.class)
                .id(entityId)
                .view("parent")
                .optional()
                .orElseGet(() -> {
                    final House newEntity = dataManager.create(House.class);
                    newEntity.setId(entityId);
                    return newEntity;
                });
    }

    private House processHouseEntity(HOUSES.HOUSE fiasHouse, House entity) {
        long parentIdAdm = admParents.getOrDefault(fiasHouse.getOBJECTID(), 0l);
        long parentIdMun = munParents.getOrDefault(fiasHouse.getOBJECTID(), 0l);

        final FiasEntity parentEntityAdm = dataManager.load(FiasEntity.class)
                .query("e.garId=?1", parentIdAdm)
                .optional()
                .orElse(null);
        final FiasEntity parentEntityMun = dataManager.load(FiasEntity.class)
                .query("e.garId=?1", parentIdMun)
                .optional()
                .orElse(null);

        House house = entity;
        house.setValue("parentAdm", parentEntityAdm, true);
        house.setValue("parentMun", parentEntityMun, true);

        if(parameters.containsKey(fiasHouse.getOBJECTID())) {
            Map<String, Object> values = parameters.get(fiasHouse.getOBJECTID());
            values.forEach((k,v) -> house.setValue(k.toString() ,v, true));
        }
        house.setValue("garId", fiasHouse.getOBJECTID(), true);
        house.setValue("housenum", fiasHouse.getHOUSENUM(), true);
        house.setValue("buildnum", fiasHouse.getADDNUM1(), true);
        house.setValue("strucnum", fiasHouse.getADDNUM2(), true);
        house.setValue("isactive", fiasHouse.getISACTIVE().intValue()==1, true);
        house.setValue("isactual", fiasHouse.getISACTUAL().intValue()==1, true);
        house.setValue("startdate", fiasHouse.getSTARTDATE().toGregorianCalendar().getTime(), true);
        house.setValue("updatedate", fiasHouse.getUPDATEDATE().toGregorianCalendar().getTime(), true);
        house.setValue("enddate", fiasHouse.getENDDATE().toGregorianCalendar().getTime(), true);
        house.setValue("addressLevel", 10);

        return house;
    }


    private boolean testParent(String parentguid, UUID requiredId) {
        if (requiredId == null) return true;
        if (parentguid == null) return false;
        UUID parentId;
        try {
            parentId = UUID.fromString(parentguid);
        } catch (IllegalArgumentException e) {
            log.warn("Wrong parentguid format. Value: {}", parentguid);
            return false;
        }
        if (parentId.equals(requiredId))
            return true;
        else {
            Optional<FiasEntity> entity = dataManager.load(FiasEntity.class)
                    .view("parent")
                    .id(parentId)
                    .optional();
            if (entity.isPresent()) {
                FiasEntity parent = entity.get().getParentAdm();
                if (parent != null)
                    return testParent(parent.getId().toString(), requiredId);
            }
        }
        return false;
    }

    private <T extends FiasEntity> FiasEntity loadFiasEntity(Class<T> clazz, ADDRESSOBJECTS.OBJECT object) {
        boolean isRegionObject = AddressLevel.REGION.getAddressLevel().equals(object.getLEVEL());
        UUID id = UUID.fromString(object.getOBJECTGUID());
        FiasEntity entity = dataManager.load(FiasEntity.class)
                .id(id)
                .view("parent")
                .optional()
                .orElseGet(() -> {
                    T newEntity = dataManager.create(clazz);
                    newEntity.setId(id);
                    return newEntity;
                });
        //entity = persistence.getEntityManager().merge(entity);
        long parentIdMun = 0;
        long parentIdAdm = 0;
        if(!isRegionObject) {
            parentIdMun = munParents.getOrDefault(object.getOBJECTID(), 0l);
            parentIdAdm = admParents.getOrDefault(object.getOBJECTID(), 0l);
        }
        FiasEntity parentAdm = null;
        if (parentIdAdm!=0) {
            parentAdm = dataManager.load(FiasEntity.class)
                    .query("e.garId=?1", parentIdAdm)
                    .optional()
                    .orElse(null);
        }
        FiasEntity parentMun = null;
        if (parentIdMun!=0) {
            parentMun = dataManager.load(FiasEntity.class)
                    .query("e.garId=?1", parentIdMun)
                    .optional()
                    .orElse(null);
        }

        entity.setValue("name", object.getNAME(), true);
        entity.setValue("offname", object.getNAME(), true);
        entity.setValue("shortname", object.getTYPENAME(), true);
        entity.setValue("parentAdm", parentAdm, true);
        entity.setValue("parentMun", parentMun, true);
        //        entity.setValue("formalname", object.getFORMALNAME(), true);
//TODO set postal code
//        entity.setValue("postalCode", object.getPOSTALCODE(), true);
//        List<String> names = Lists.newArrayList(object.getFORMALNAME(), object.getOFFNAME());
//        entity.setValue("possibleNames", String.join(",", names), true);
//
        entity.setValue("updatedate", object.getUPDATEDATE().toGregorianCalendar().getTime(), true);
        entity.setValue("actstatus", FiasEntityStatus.fromId(object.getISACTUAL().intValue()), true);
//        entity.setValue("operstatus", FiasEntityOperationStatus.fromId(object.getOPERSTATUS().intValue()), true);
        entity.setValue("startdate", object.getSTARTDATE().toGregorianCalendar().getTime(), true);
        entity.setValue("enddate", object.getENDDATE().toGregorianCalendar().getTime(), true);
        entity.setValue("prevID", object.getPREVID(), true);
        entity.setValue("garId", object.getOBJECTID(), true);
        entity.setValue("addressLevel", object.getLEVEL());
        return entity;
    }

    public Map<Class, FiasEntity> getAddressComponents(House house) {
        if (!PersistenceHelper.isLoaded(house, "parent"))
            house = dataManager.reload(house, "parent");

        final FiasEntity fiasEntity = house.getParentAdm();

        final HashMap<Class, FiasEntity> entityMap = new HashMap<>();
        findFiasEntityParent(fiasEntity, entityMap);

        return entityMap;
    }

    public Map<Class, FiasEntity> getAddressComponents(UUID houseId) {
        final Optional<House> houseOptional = dataManager.load(House.class)
                .id(houseId)
                .view("parent")
                .optional();
        return houseOptional.map(this::getAddressComponents).orElse(null);
    }

    private void findFiasEntityParent(FiasEntity fiasEntity, HashMap<Class, FiasEntity> entityMap) {
        if (!PersistenceHelper.isLoaded(fiasEntity, "parent"))
            fiasEntity = dataManager.reload(fiasEntity, "parent");
        entityMap.put(fiasEntity.getClass(), fiasEntity);
        if (fiasEntity.getParentAdm() != null) {
            findFiasEntityParent(fiasEntity.getParentAdm(), entityMap);
        }
    }

    private Path getPathByPattern(String startsWith) throws IOException {
        Optional<Path> filePath = Files.list(xmlDirectory)
                .map(xmlDirectory::relativize)
                .filter(path -> path.toString().startsWith(startsWith) && path.toString().toLowerCase().endsWith("xml"))
                .findFirst();
        if (!filePath.isPresent()) {
            throw new FileNotFoundException(String.format("Can't find XML file with name starting with [%s]", startsWith));
        }
        return xmlDirectory.resolve(filePath.get());
    }

    public void onResetProgressButtonClick() {
        resetConfigProgress();
        log.warn("Progress was reseted");
    }

    //слушатели на закрытие окна (для сохранения прогресса)
    private void setupCloseWindowListeners() {
        //слушатель на закрытие окна по кнопке крестик
        this.addBeforeCloseWithCloseButtonListener(e -> {
            if (taskWasStarted && lastClassWorked != null) {
                taskHandler.cancel();
                try {
                    updateConfigProgress(lastClassWorked, progress);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        //слушатель на закрытие окна по shortcut Esc
        this.addBeforeCloseWithShortcutListener(e -> {
            if (taskWasStarted && lastClassWorked != null) {
                taskHandler.cancel();
                try {
                    updateConfigProgress(lastClassWorked, progress);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    //слушатель на изменение региона
    private void setupChangeRegionLkf() {
//        regionField.addValueChangeListener(e ->
//                cityField.setOptionsMap(getMapOfCitiesFromFias(regionField.getValue().toString())));
    }

    //обновляем доступность кнопок
    private void setupControlButtons(boolean taskIsStopped) {
        if (taskIsStopped) {
            loadDataBtn.setEnabled(true);
            resetProgressButton.setEnabled(true);
            pauseLoadingDataBtn.setEnabled(false);
        } else {
            loadDataBtn.setEnabled(false);
            resetProgressButton.setEnabled(false);
            pauseLoadingDataBtn.setEnabled(true);
        }
    }

    private void sendToLog(String text) {
        log.info(text);
    }

    private Map<String, String> getMapOfRegionsFromFias() {
        return fiasReadService.getMapOfRegionsFromFias();
    }

    private Map<String, String> getMapOfCitiesFromFias(String regionId) {
        return fiasReadService.getMapOfCitiesFromFias(regionId);
    }
}