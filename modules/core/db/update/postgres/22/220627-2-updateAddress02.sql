alter table FIAS_ADDRESS rename column stead_id to stead_id__u95069 ;
alter table FIAS_ADDRESS drop constraint FK_FIAS_ADDRESS_STEAD ;
drop index IDX_FIAS_ADDRESS_STEAD ;
