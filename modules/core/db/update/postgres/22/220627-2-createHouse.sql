alter table FIAS_HOUSE add constraint FK_FIAS_HOUSE_PARENT_ADM foreign key (PARENT_ADM_ID) references FIAS_FIAS_ENTITY(ID);
alter table FIAS_HOUSE add constraint FK_FIAS_HOUSE_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID);
create unique index IDX_FIAS_HOUSE_UK_GAR_ID on FIAS_HOUSE (GAR_ID) where DELETE_TS is null ;
create index IDX_FIAS_HOUSE_PARENT_ADM on FIAS_HOUSE (PARENT_ADM_ID);
create index IDX_FIAS_HOUSE_PARENT_MUN on FIAS_HOUSE (PARENT_MUN_ID);
