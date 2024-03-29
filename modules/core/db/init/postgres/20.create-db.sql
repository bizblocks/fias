-- begin FIAS_FIAS_ENTITY
alter table FIAS_FIAS_ENTITY add constraint FK_FIAS_ENTITY_PARENT foreign key (PARENT_ID) references FIAS_FIAS_ENTITY(ID)^
alter table FIAS_FIAS_ENTITY add constraint FK_FIAS_ENTITY_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID)^
create unique index IDX_FIAS_ENTITY_UK_CODE on FIAS_FIAS_ENTITY (CODE) where DELETE_TS is null ^
create index IDX_FIAS_ENTITY_PARENT on FIAS_FIAS_ENTITY (PARENT_ID)^
create index IDX_FIAS_ENTITY_PARENT_MUN on FIAS_FIAS_ENTITY (PARENT_MUN_ID)^
create index IDX_FIAS_FIAS_ENTITY_NAME on FIAS_FIAS_ENTITY (NAME)^
create index IDX_FIAS_FIAS_ENTITY_CODE on FIAS_FIAS_ENTITY (CODE)^
create index IDX_FIAS_FIAS_ENTITY_NAME_PARENT_ADM on FIAS_FIAS_ENTITY (NAME, PARENT_ID)^
create index IDX_FIAS_FIAS_ENTITY_NAME_PARENT_MUN on FIAS_FIAS_ENTITY (NAME, PARENT_MUN_ID)^
-- end FIAS_FIAS_ENTITY

-- begin FIAS_ADDRESS
create unique index IDX_FIAS_ADDRESS_UK_SRC_ADDRESS on FIAS_ADDRESS (SRC_ADDRESS) where DELETE_TS is null ^
create index IDX_FIAS_ADDRESS_SRC_ADDRESS on FIAS_ADDRESS (SRC_ADDRESS)^
-- end FIAS_ADDRESS
-- begin FIAS_DIVISION_TAG
create index IDX_FIAS_DIVISION_TAG on FIAS_DIVISION_TAG (TAG)^
-- end FIAS_DIVISION_TAG
-- begin FIAS_ADDRESS_HOUSE_LINK
alter table FIAS_ADDRESS_HOUSE_LINK add constraint FK_ADDHOU_ADDRESS foreign key (ADDRESS_ID) references FIAS_ADDRESS(ID)^
alter table FIAS_ADDRESS_HOUSE_LINK add constraint FK_ADDHOU_HOUSE foreign key (HOUSE_ID) references FIAS_HOUSE(ID)^
-- end FIAS_ADDRESS_HOUSE_LINK
-- begin FIAS_ADDRESS_FIAS_ENTITY_LINK
alter table FIAS_ADDRESS_FIAS_ENTITY_LINK add constraint FK_ADDFIAENT_FIAS_ENTITY foreign key (FIAS_ENTITY_ID) references FIAS_FIAS_ENTITY(ID)^
alter table FIAS_ADDRESS_FIAS_ENTITY_LINK add constraint FK_ADDFIAENT_ADDRESS foreign key (ADDRESS_ID) references FIAS_ADDRESS(ID)^
-- end FIAS_ADDRESS_FIAS_ENTITY_LINK
-- begin FIAS_STEAD
alter table FIAS_STEAD add constraint FK_FIAS_STEAD_PARENT_ADM foreign key (PARENT_ADM_ID) references FIAS_FIAS_ENTITY(ID)^
alter table FIAS_STEAD add constraint FK_FIAS_STEAD_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID)^
create unique index IDX_FIAS_STEAD_UK_GAR_ID on FIAS_STEAD (GAR_ID) where DELETE_TS is null ^
create index IDX_FIAS_STEAD_PARENT_ADM on FIAS_STEAD (PARENT_ADM_ID)^
create index IDX_FIAS_STEAD_PARENT_MUN on FIAS_STEAD (PARENT_MUN_ID)^
create index IDX_FIAS_STEAD_NUMBER_PARENT_MUN on FIAS_STEAD (NUMBER_, PARENT_MUN_ID)^
create index IDX_FIAS_STEAD_NUMBER_PARENT_ADM on FIAS_STEAD (NUMBER_, PARENT_ADM_ID)^
-- end FIAS_STEAD
-- begin FIAS_HOUSE
alter table FIAS_HOUSE add constraint FK_FIAS_HOUSE_PARENT_ADM foreign key (PARENT_ADM_ID) references FIAS_FIAS_ENTITY(ID)^
alter table FIAS_HOUSE add constraint FK_FIAS_HOUSE_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID)^
create unique index IDX_FIAS_HOUSE_UK_GAR_ID on FIAS_HOUSE (GAR_ID) where DELETE_TS is null ^
create index IDX_FIAS_HOUSE_PARENT_ADM on FIAS_HOUSE (PARENT_ADM_ID)^
create index IDX_FIAS_HOUSE_PARENT_MUN on FIAS_HOUSE (PARENT_MUN_ID)^
create index IDX_FIAS_HOUSE_HOUENUM on FIAS_HOUSE (HOUSENUM)^
create index IDX_FIAS_HOUSE_HOUSENUM_PARENT_MUN on FIAS_HOUSE (HOUSENUM, PARENT_MUN_ID)^
create index IDX_FIAS_HOUSE_HOUSENUM_PARENT_ADM on FIAS_HOUSE (HOUSENUM, PARENT_ADM_ID)^
-- end FIAS_HOUSE
-- begin FIAS_ADDRESS_STEAD_LINK
alter table FIAS_ADDRESS_STEAD_LINK add constraint FK_ADDSTE_STEAD foreign key (STEAD_ID) references FIAS_STEAD(ID)^
alter table FIAS_ADDRESS_STEAD_LINK add constraint FK_ADDSTE_ADDRESS foreign key (ADDRESS_ID) references FIAS_ADDRESS(ID)^
-- end FIAS_ADDRESS_STEAD_LINK
