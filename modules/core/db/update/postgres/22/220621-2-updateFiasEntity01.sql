alter table FIAS_FIAS_ENTITY add constraint FK_FIAS_ENTITY_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID);
create index IDX_FIAS_ENTITY_PARENT_MUN on FIAS_FIAS_ENTITY (PARENT_MUN_ID);