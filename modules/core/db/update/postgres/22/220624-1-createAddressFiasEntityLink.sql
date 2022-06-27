create table FIAS_ADDRESS_FIAS_ENTITY_LINK (
    FIAS_ENTITY_ID uuid,
    ADDRESS_ID uuid,
    primary key (FIAS_ENTITY_ID, ADDRESS_ID)
);
