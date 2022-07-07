update fias_address set housecounter=0;
update fias_address set housecounter=hc from (select address_id, count(house_id) as hc from fias_address_house_link group by address_id) as a where id=address_id;
update fias_address set steadcounter=0;
update fias_address set steadcounter=sc from (select address_id, count(stead_id) as sc from fias_address_stead_link group by address_id) as a where id=address_id;