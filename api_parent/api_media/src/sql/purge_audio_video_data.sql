/**
delete FROM AV_TRACKS;
delete from AV_PROJECT;
delete from AV_ARTIST;
commit;
*/

select name, count(*) from AV_ARTIST group by name having count(*) > 1 
union
select 'artists', count(*) from av_artist
union
select 'projects', count(*) from AV_PROJECT
union
select 'tracks', count(*) from AV_TRACKS
 order by 1