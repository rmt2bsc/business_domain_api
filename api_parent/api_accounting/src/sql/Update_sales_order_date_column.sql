/**
 * Update the effective date of all sales order records where the 
 * sales order date is included in the transaction reason column
 */
update sales_order
  set effective_date = c.so_date_derived
from
   sales_order so inner join 
       (select xact_id,
	       so_id,
	       reason,
	       so_date,
	       date(so_date_year + '/' + so_date_month + '/' + so_date_day) so_date_derived
	  from (select a.xact_id,
		       a.so_id,
		       a.reason,
		       a.so_date,
		       substring(a.so_date, 0, locate(a.so_date, '/', 1)) so_date_month,
		       substring(a.so_date, 
				 locate(a.so_date, '/', 1) + 1, 
				 (locate(a.so_date, '/',  locate(a.so_date, '/', 1) + 1)) - (locate(a.so_date, '/', 1) + 1) ) so_date_day,
		       substring(a.so_date, 
				  locate(a.so_date, '/',  locate(a.so_date, '/', 1) + 1) + 1 ) so_date_year
		  from (select xact.xact_id xact_id, 
			       so_id,
			       reason, 
			       regexp_substr(reason, '(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)') so_date
			  from xact, 
			       sales_invoice 
			 where reason regexp '.*(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d).*' 
			   and sales_invoice.xact_id = xact.xact_id 
			 order by 
			       xact.xact_id
			) a
		) b
	) c
on so.so_id = c.so_id;

/**
 * Update the effective date of the remaining sales order records to 
 * equal the date_created column where the effective date is initially null.
 */
update 
       sales_order 
   set 
       effective_date = date_created
 where 
       effective_date is null 
   and date_created is not null
   and so_id not in (select 
		            so_id
		       from 
		            xact, 
		            sales_invoice 
		      where 
		            reason regexp '.*(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d).*' 
		        and sales_invoice.xact_id = xact.xact_id 
		   order by 
		            so_id);



