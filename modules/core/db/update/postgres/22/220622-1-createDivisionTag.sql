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
);