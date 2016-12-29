--
-- This command file reloads a database that was unloaded using "dbunload".
--
-- (Version:  11.0.0.1264)
--



-------------------------------------------------
--   Reload column statistics
-------------------------------------------------


-------------------------------------------------
--   Reload data
-------------------------------------------------
INPUT INTO "DBA"."IP_BLOCK" 
    FROM 'C:\\tmp\\ip_block.dat'
    FORMAT ASCII
    ESCAPE CHARACTER '\\'
    BY ORDER
    ENCODING 'windows-1252'
go

commit work
go

