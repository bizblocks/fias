alter table FIAS_ADDRESS rename column house_id to house_id__u24462 ;
alter table FIAS_ADDRESS drop constraint FK_FIAS_ADDRESS_HOUSE ;
drop index IDX_FIAS_ADDRESS_HOUSE ;
