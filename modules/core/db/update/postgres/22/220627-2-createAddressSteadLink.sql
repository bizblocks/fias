alter table FIAS_ADDRESS_STEAD_LINK add constraint FK_ADDSTE_STEAD foreign key (STEAD_ID) references FIAS_STEAD(ID);
alter table FIAS_ADDRESS_STEAD_LINK add constraint FK_ADDSTE_ADDRESS foreign key (ADDRESS_ID) references FIAS_ADDRESS(ID);