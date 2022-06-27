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
    ADDRESS_LEVEL integer,
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

-- begin FIAS_ADDRESS
create table FIAS_ADDRESS (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    SRC_ADDRESS varchar(1024) not null,
    NORM_ADDRESS varchar(1024),
    --
    primary key (ID)
)^
-- end FIAS_ADDRESS
-- begin FIAS_DIVISION_TAG
create table FIAS_DIVISION_TAG (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    TAG varchar(255),
    TYPE_ varchar(255),
    --
    primary key (ID)
)^
-- end FIAS_DIVISION_TAG
-- begin FIAS_ADDRESS_HOUSE_LINK
create table FIAS_ADDRESS_HOUSE_LINK (
    ADDRESS_ID uuid,
    HOUSE_ID uuid,
    primary key (ADDRESS_ID, HOUSE_ID)
)^
-- end FIAS_ADDRESS_HOUSE_LINK
-- begin FIAS_ADDRESS_FIAS_ENTITY_LINK
create table FIAS_ADDRESS_FIAS_ENTITY_LINK (
    FIAS_ENTITY_ID uuid,
    ADDRESS_ID uuid,
    primary key (FIAS_ENTITY_ID, ADDRESS_ID)
)^
-- end FIAS_ADDRESS_FIAS_ENTITY_LINK
-- begin FIAS_STEAD
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
    ADDRESS_LEVEL integer,
    --
    primary key (ID)
)^
-- end FIAS_STEAD
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
)^
-- end FIAS_HOUSE
