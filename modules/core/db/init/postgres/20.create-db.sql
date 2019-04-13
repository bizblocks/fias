-- begin FIAS_FIAS_ENTITY
alter table FIAS_FIAS_ENTITY add constraint FK_FIAS_ENTITY_ON_PARENT foreign key (PARENT_ID) references FIAS_FIAS_ENTITY(ID)^
create index IDX_FIAS_ENTITY_ON_PARENT on FIAS_FIAS_ENTITY (PARENT_ID)^
-- end FIAS_FIAS_ENTITY
-- begin FIAS_HOUSE
alter table FIAS_HOUSE add constraint FK_FIAS_HOUSE_ON_PARENT foreign key (PARENT_ID) references FIAS_FIAS_ENTITY(ID)^
create index IDX_FIAS_HOUSE_ON_PARENT on FIAS_HOUSE (PARENT_ID)^
-- end FIAS_HOUSE
