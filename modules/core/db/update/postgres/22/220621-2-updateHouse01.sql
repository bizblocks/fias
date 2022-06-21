alter table FIAS_HOUSE add constraint FK_FIAS_HOUSE_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID);
create index IDX_FIAS_HOUSE_PARENT_MUN on FIAS_HOUSE (PARENT_MUN_ID);
