-- begin FIAS_FIAS_ENTITY
create table FIAS_FIAS_ENTITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(255),
    PARENT_ID uuid,
    PARENT_MUN_ID uuid,
    POSTAL_CODE varchar(6),
    OFFNAME varchar(255),
    FORMALNAME varchar(255),
    POSSIBLE_NAMES text,
    CODE bigint,
    SHORTNAME varchar(50),
    UPDATEDATE date,
    ACTSTATUS integer,
    OPERSTATUS integer,
    STARTDATE date,
    ENDDATE date,
    PREV_ID bigint,
    --
    primary key (ID)
)^
-- end FIAS_FIAS_ENTITY
-- begin FIAS_HOUSE
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
    POSTALCODE varchar(6),
    CADASTRAL_NUMBER varchar(255),
    IFNSFL varchar(4),
    TERRIFNSFL varchar(4),
    IFNSUL varchar(4),
    TERRIFNSUL varchar(4),
    OKATO varchar(11),
    OKTMO varchar(11),
    HOUSENUM varchar(20),
    ESTSTATUS integer,
    BUILDNUM varchar(10),
    STRUCNUM varchar(10),
    STRSTATUS integer,
    STARTDATE timestamp,
    ENDDATE date,
    PARENT_ID uuid,
    PARENT_MUN_ID uuid,
    --
    primary key (ID)
)^
-- end FIAS_HOUSE
