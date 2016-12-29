if exists(select 1 from sys.sysforeignkey where role='FK_AV_PROJE_CONTENT_A_CONTENT') then
    alter table AV_PROJECT
       delete foreign key FK_AV_PROJE_CONTENT_A_CONTENT
end if;

alter table AV_PROJECT
  alter column content_id integer null;
  
alter table AV_PROJECT
   add constraint FK_AV_PROJE_CONTENT_A_CONTENT foreign key (CONTENT_ID)
      references CONTENT (CONTENT_ID)
      on update restrict
      on delete restrict;
      
alter table av_tracks
   add track_artist varchar(100) null;

comment on column AV_TRACKS.TRACK_ARTIST is 
'This is the artist of the track in the event the track comes from a "Various Artists" type album.';

alter table av_tracks
   alter comments varchar(5000) null;
      