select count(*) from ip_location where country = 'bg'

select distinct country, count(*) from ip_location group by country order by country
select distinct country, region, count(*) from ip_location group by country, region order by country
select distinct country, region, city, count(*) from ip_location where country = 'us' and region = 'al' group by country, region, city order by country