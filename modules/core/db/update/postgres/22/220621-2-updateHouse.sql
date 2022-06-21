-- alter table FIAS_HOUSE add column PARENT_MUN_ID uuid ^
-- update FIAS_HOUSE set PARENT_MUN_ID = <default_value> ;
-- alter table FIAS_HOUSE alter column PARENT_MUN_ID set not null ;
alter table FIAS_HOUSE add column PARENT_MUN_ID uuid not null ;
alter table FIAS_HOUSE add column CADASTRAL_NUMBER varchar(255) ;
