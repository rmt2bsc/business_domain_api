--
-- This command file reloads a database that was unloaded using "dbunload".
--
-- ( Version :  10.0.0.2465)
--



-------------------------------------------------
--   Reload column statistics
-------------------------------------------------


-------------------------------------------------
--   Reload data
-------------------------------------------------

INPUT INTO "DBA"."PURCHASE_ORDER_STATUS" 
    FROM 'C:\\tmp\\purchase_order_status.dat'
    FORMAT ASCII
    ESCAPE CHARACTER '\\'
    BY ORDER
    ENCODING 'windows-1252'
go

commit work
go
