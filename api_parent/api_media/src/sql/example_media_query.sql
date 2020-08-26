select a.ARTIST_ID, a.NAME, p.PROJECT_ID, p.TITLE, t.TRACK_NUMBER, t.TRACK_TITLE, t.TRACK_HOURS, t.TRACK_MINUTES, t.TRACK_SECONDS
    from AV_ARTIST a, AV_PROJECT p, AV_TRACKS t 
   where 
         a.ARTIST_ID = p.ARTIST_ID 
     and p.PROJECT_ID = t.PROJECT_ID
     and p.TITLE like 'alive%'
 order by
         a.name,
         p.TITLE,
         t.TRACK_NUMBER