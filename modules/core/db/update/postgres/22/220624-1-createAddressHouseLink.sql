create table FIAS_ADDRESS_HOUSE_LINK (
    HOUSE_ID uuid,
    ADDRESS_ID uuid,
    primary key (HOUSE_ID, ADDRESS_ID)
);
