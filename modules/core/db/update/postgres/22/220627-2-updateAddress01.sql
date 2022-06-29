alter table FIAS_ADDRESS add constraint FK_FIAS_ADDRESS_STEAD foreign key (STEAD_ID) references FIAS_STEAD(ID);
create index IDX_FIAS_ADDRESS_STEAD on FIAS_ADDRESS (STEAD_ID);
