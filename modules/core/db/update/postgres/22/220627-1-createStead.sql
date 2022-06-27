create table FIAS_STEAD (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    GAR_ID bigint not null,
    NUMBER_ varchar(250),
    PARENT_ADM_ID uuid,
    PARENT_MUN_ID uuid,
    STARTDATE date,
    ENDDATE date,
    UPDATEDATE date,
    ISACTUAL boolean,
    ISACTIVE boolean,
    --
    primary key (ID)
);