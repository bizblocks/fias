copy
(select src_address as address,  house_id as fias, postalcode as postalcode from fias_address_house_link
join fias_house h on house_id=h.id
right join fias_address a on address_id=a.id
group by postalcode, address_id, house_id, src_address
having count(address_id)=1
order by src_address)
to '/tmp/address_house_fias.csv' csv header delimiter ';';

copy
(select src_address as address,  stead_id as fias from fias_address_stead_link
 right join fias_address a on address_id=a.id
 group by address_id, stead_id, src_address
 having count(address_id)=1
 order by src_address)
to '/tmp/address_stead_fias.csv' csv header delimiter ';';