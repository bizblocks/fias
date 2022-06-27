alter table FIAS_ADDRESS add constraint FK_FIAS_ADDRESS_HOUSE foreign key (HOUSE_ID) references FIAS_HOUSE(ID);
create unique index IDX_FIAS_ADDRESS_UK_SRC_ADDRESS on FIAS_ADDRESS (SRC_ADDRESS) where DELETE_TS is null ;
create index IDX_FIAS_ADDRESS_HOUSE on FIAS_ADDRESS (HOUSE_ID);
