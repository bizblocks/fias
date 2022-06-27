create table FIAS_HOUSE (
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
    POSTALCODE varchar(6),
    HOUSENUM varchar(20),
    BUILDNUM varchar(10),
    STRUCNUM varchar(10),
    PARENT_ADM_ID uuid,
    PARENT_MUN_ID uuid,
    CADASTRAL_NUMBER varchar(255),
    IFNSFL varchar(4),
    TERRIFNSFL varchar(4),
    IFNSUL varchar(4),
    TERRIFNSUL varchar(4),
    OKATO varchar(11),
    OKTMO varchar(11),
    ISACTUAL boolean,
    ISACTIVE boolean,
    --
    primary key (ID)
);