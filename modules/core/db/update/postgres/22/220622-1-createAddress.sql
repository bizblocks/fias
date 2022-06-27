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
    HOUSE_ID uuid,
    --
    primary key (ID)
);