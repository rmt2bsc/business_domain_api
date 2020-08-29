 select 1 result_type,
           'artist' result_type_description,
           a.ARTIST_ID artist_id, 
           a.NAME artist_name, 
           null project_id, 
           null project_title, 
           null track_number, 
           null track_name, 
           null track_hours, 
           null track_minutes, 
           null track_seconds
        from AV_ARTIST a
       where 
             (a.NAME like 'xxxxxxx%' or a.NAME like 'The xxxxxxx%')  
   UNION 
 
   select 2 result_type,
           'project' result_type_description,
           a.ARTIST_ID, 
           a.NAME artist_name, 
           p.PROJECT_ID, 
           p.TITLE project_name, 
           null track_number, 
           null track_name, 
           null track_hours, 
           null track_minutes, 
           null track_seconds
        from AV_ARTIST a, AV_PROJECT p
       where 
             a.ARTIST_ID = p.ARTIST_ID 
         and (p.TITLE like 'xxxxxxx%' or p.TITLE like 'The xxxxxxx%')  
    
    UNION
    
    select 3 result_type,
           'track' result_type_description,
           a.ARTIST_ID, 
           a.NAME artist_name, 
           p.PROJECT_ID, 
           p.TITLE project_name, 
           t.TRACK_NUMBER, 
           t.TRACK_TITLE track_name, 
           t.TRACK_HOURS, 
           t.TRACK_MINUTES, 
           t.TRACK_SECONDS
        from AV_ARTIST a, AV_PROJECT p, AV_TRACKS t 
       where 
             a.ARTIST_ID = p.ARTIST_ID 
         and p.PROJECT_ID = t.PROJECT_ID
         and (t.TRACK_TITLE like 'xxxxxxx%' 
          or t.TRACK_TITLE like 'The xxxxxxx%' 
          or t.COMMENTS like '%xxxxxxx%' 
          or t.TRACK_COMPOSER like '%xxxxxxx%' 
          or t.TRACK_PRODUCER like '%xxxxxxx%')  
     
     order by 1, 4, 6, 8 