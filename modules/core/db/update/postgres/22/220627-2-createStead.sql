alter table FIAS_STEAD add constraint FK_FIAS_STEAD_PARENT_ADM foreign key (PARENT_ADM_ID) references FIAS_FIAS_ENTITY(ID);
alter table FIAS_STEAD add constraint FK_FIAS_STEAD_PARENT_MUN foreign key (PARENT_MUN_ID) references FIAS_FIAS_ENTITY(ID);
create unique index IDX_FIAS_STEAD_UK_GAR_ID on FIAS_STEAD (GAR_ID) where DELETE_TS is null ;
create index IDX_FIAS_STEAD_PARENT_ADM on FIAS_STEAD (PARENT_ADM_ID);
create index IDX_FIAS_STEAD_PARENT_MUN on FIAS_STEAD (PARENT_MUN_ID);
