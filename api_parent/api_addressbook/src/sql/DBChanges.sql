alter table person
  alter shortname not null;

alter table business
  alter longname not null;

alter table business
  alter entity_type_id null;

alter table business
  alter serv_type_id null;
  
Create FUNCTION "DBA"."ufn_get_ip_block_count"(IN loc_id integer)
RETURNS INTEGER
DETERMINISTIC
BEGIN
	DECLARE block_count INTEGER;
	select count(*)
       into block_count
       from ip_block 
       where ip_loc = loc_id;

	RETURN block_count;
END  
