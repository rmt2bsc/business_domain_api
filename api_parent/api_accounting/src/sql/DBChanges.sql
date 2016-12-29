ALTER VIEW "DBA"."VW_CREDITOR_XACT_HIST"  as
select
   xact.xact_id as xact_id,
   creditor.creditor_id as creditor_id,
   creditor.account_number as account_number,
   creditor.creditor_type_id as creditor_type_id,
   creditor.active as active,
   creditor.apr as apr,
   creditor.credit_limit as credit_limit,
   creditor_type.description as creditor_type_description,
   xact.xact_amount as xact_amount,
   xact.xact_date as xact_date,
   xact.xact_type_id as xact_type_id,
   xact.xact_subtype_id as xact_subtype_id,
   xact.reason as reason,
   xact_type.description as xact_type_name,
   xact.date_created,
   xact.user_id,
   xact.posted_date,
   xact.confirm_no,
   xact.neg_instr_no,
   xact.tender_id,
   xact.document_id,
   creditor_activity.creditor_actv_id as creditor_activity_id,
   creditor_activity.amount as creditor_activity_amount
from 
   xact inner join creditor_activity on creditor_activity.xact_id = xact.xact_id
        right outer join xact_type on xact.xact_type_id = xact_type.xact_type_id
        inner join creditor on creditor_activity.creditor_id = creditor.creditor_id
        inner join gl_accounts on creditor.acct_id = gl_accounts.acct_id
        inner join creditor_type on creditor_type.creditor_type_id = creditor.creditor_type_id;
        
        
ALTER VIEW "DBA"."vw_item_master"  as
select 
       im.item_id id,
       im.creditor_id vendor_id,
       im.item_type_id,
       im.description description,
       im.vendor_item_no,
       im.item_serial_no,
       im.qty_on_hand,
       im.unit_cost,
       im.markup,
       im.retail_price,
       im.override_retail,
       im.active,
       im.date_created item_create_date,
       im.date_updated item_update_date,
       im.user_id update_userid,
       im.ip_created ip_created,
       im.ip_updated ip_updated,
       imt.description item_type_description,
       ims.description item_status,
       ufn_get_current_item_status(im.item_id) item_status_id
  from
       item_master im,
       item_master_type imt,
       item_master_status ims
 where
       im.item_type_id = imt.item_type_id
   and ims.item_status_id = ufn_get_current_item_status(im.item_id);
   
   
ALTER VIEW "DBA"."vw_item_associations"  as
select
       so_id assoc_id,
       so_item_id assoc_item_id,
       item_id,
       init_unit_cost item_cost,
       order_qty order_qty,
       cast('so' as varchar(5)) assoc_type
  from 
       sales_order_items

union

select 
       po_id,
       po_item_id,
       item_id,
       unit_cost,
       qty,
       cast('po' as varchar(5)) assoc_type
  from
       purchase_order_items;   
   
   
drop index GL_ACCOUNT_TYPES_PK;
create unique index GL_ACCOUNT_TYPES_PK on GL_ACCOUNT_TYPES (description ASC);
   
        
/*==============================================================*/
/* Index: GL_ACCTCTG_DESCR_NDX                                  */
/*==============================================================*/
create unique index GL_ACCTCTG_DESCR_NDX on GL_ACCOUNT_CATEGORY (description ASC);

comment on index GL_ACCOUNT_CATEGORY.GL_ACCTCTG_DESCR_NDX is 
'There can be no two descriptions alike.';

        