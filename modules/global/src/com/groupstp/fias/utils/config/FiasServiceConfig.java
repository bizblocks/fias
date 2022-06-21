package com.groupstp.fias.utils.config;

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.config.defaults.DefaultInt;
import com.haulmont.cuba.core.config.defaults.DefaultLong;

@Source(type = SourceType.DATABASE)
public interface FiasServiceConfig extends Config {

    @Property("fias.fias-service.path")
    @Default("D:/Work/Files/fias/fias_xml")
    String getPath();

    @Property("fias.fias-service.batchSize")
    @DefaultInt(1000)
    int getBatchSize();

    @Property("fias.fias-service.import-process.progressRegion")
    @DefaultLong(0)
    long getProgressRegion();
    void setProgressRegion(long progress);

    @Property("fias.fias-service.import-process.progressAdmRegion")
    @DefaultLong(0)
    long getProgressAdmRegion();
    void setProgressAdmRegion(long progress);

    @Property("fias.fias-service.import-process.progressMunRegion")
    @DefaultLong(0)
    long getProgressMunRegion();
    void setProgressMunRegion(long progress);

    @Property("fias.fias-service.import-process.progressSettlement")
    @DefaultLong(0)
    long getProgressSettlement();
    void setProgressSettlement(long progress);

    @Property("fias.fias-service.import-process.progressLocality")
    @DefaultLong(0)
    long getProgressLocality();
    void setProgressLocality(long progress);

    @Property("fias.fias-service.import-process.progressPlanningStructure")
    @DefaultLong(0)
    long getProgressPlanningStructure();
    void setProgressPlanningStructure(long progress);

    @Property("fias.fias-service.import-process.progressAutonomy")
    @DefaultLong(0)
    long getProgressAutonomy();
    void setProgressAutonomy(long progress);

    @Property("fias.fias-service.import-process.progressArea")
    @DefaultLong(0)
    long getProgressArea();
    void setProgressArea(long progress);

    @Property("fias.fias-service.import-process.progressCity")
    @DefaultLong(0)
    long getProgressCity();
    void setProgressCity(long progress);

    @Property("fias.fias-service.import-process.progressCommunity")
    @DefaultLong(0)
    long getProgressCommunity();
    void setProgressCommunity(long progress);

    @Property("fias.fias-service.import-process.progressLocation")
    @DefaultLong(0)
    long getProgressLocation();
    void setProgressLocation(long progress);

    @Property("fias.fias-service.import-process.progressStreet")
    @DefaultLong(0)
    long getProgressStreet();
    void setProgressStreet(long progress);

    @Property("fias.fias-service.import-process.progressHouse")
    @DefaultLong(0)
    long getProgressHouse();
    void setProgressHouse(long progress);
}
