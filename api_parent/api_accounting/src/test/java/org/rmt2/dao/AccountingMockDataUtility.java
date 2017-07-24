package org.rmt2.dao;

import java.math.BigInteger;
import java.util.Date;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorType;
import org.dao.mapping.orm.rmt2.GlAccountCategory;
import org.dao.mapping.orm.rmt2.GlAccountTypes;
import org.dao.mapping.orm.rmt2.GlAccounts;
import org.dao.mapping.orm.rmt2.ItemMaster;
import org.dao.mapping.orm.rmt2.ItemMasterStatus;
import org.dao.mapping.orm.rmt2.ItemMasterStatusHist;
import org.dao.mapping.orm.rmt2.ItemMasterType;
import org.dao.mapping.orm.rmt2.VwBusinessAddress;
import org.dao.mapping.orm.rmt2.VwCommonContact;
import org.dao.mapping.orm.rmt2.VwCreditorXactHist;
import org.dao.mapping.orm.rmt2.VwItemAssociations;
import org.dao.mapping.orm.rmt2.VwVendorItems;
import org.dto.AccountDto;
import org.dto.adapter.orm.account.generalledger.Rmt2AccountDtoFactory;
import org.rmt2.jaxb.BusinessType;
import org.rmt2.jaxb.CodeDetailType;
import org.rmt2.jaxb.ObjectFactory;

import com.SystemException;
import com.util.RMT2Date;

public class AccountingMockDataUtility {

    public static final GlAccounts createMockOrmGlAccounts(int acctId, int acctTypeId, int acctCatgId, int acctSeq,
            String acctNo, String acctName, String acctCode, String acctDescription, int acctBalTypeId) {
        GlAccounts orm = new GlAccounts();
        orm.setAcctId(acctId);
        orm.setAcctTypeId(acctTypeId);
        orm.setAcctBaltypeId(acctBalTypeId);
        orm.setAcctCatgId(acctCatgId);
        orm.setAcctNo(acctNo);
        orm.setAcctSeq(acctSeq);
        orm.setCode(acctCode);
        orm.setDescription(acctDescription);
        orm.setName(acctName);
        return orm;
    }

    public static final AccountDto createMockDtoGlAccounts(int acctId, int acctTypeId, int acctCatgId, int acctSeq,
            String acctNo, String acctName, String acctCode, String acctDescription, int acctBalTypeId) {
        GlAccounts orm = AccountingMockDataUtility.createMockOrmGlAccounts(acctId, acctTypeId, acctCatgId, acctSeq,
                acctNo, acctName, acctCode, acctDescription, acctBalTypeId);
        AccountDto dto = Rmt2AccountDtoFactory.createAccountInstance(orm);
        return dto;
    }

    /**
     * 
     * @param id
     * @param acctBalTypeId
     * @return
     */
    public static final GlAccountTypes createMockOrmGlAccountTypes(int id, int acctBalTypeId, String description) {
        GlAccountTypes orm = new GlAccountTypes();
        orm.setAcctTypeId(id);
        orm.setAcctBaltypeId(acctBalTypeId);
        orm.setDescription(description);
        return orm;
    }

    /**
     * 
     * @param id
     * @param acctTypeId
     * @param description
     * @return
     */
    public static final GlAccountCategory createMockOrmGlAccountCategory(int id, int acctTypeId, String description) {
        GlAccountCategory orm = new GlAccountCategory();
        orm.setAcctCatgId(id);
        orm.setAcctTypeId(acctTypeId);
        orm.setDescription(description);
        return orm;
    }

    /**
     * 
     * @param id
     * @param itemTypeId
     * @param serialNo
     * @param vendorItemNo
     * @param creditorId
     * @param description
     * @param qty
     * @param unitCost
     * @param reatilPrice
     * @param active
     * @return
     */
    public static final ItemMaster createMockOrmItemMaster(int id, int itemTypeId, String serialNo, String vendorItemNo,
            int creditorId, String description, int qty, double unitCost, boolean active) {
        ItemMaster i = new ItemMaster();
        i.setItemId(id);
        i.setItemTypeId(itemTypeId);
        i.setItemSerialNo(serialNo);
        i.setVendorItemNo(vendorItemNo);
        i.setCreditorId(creditorId);
        i.setDescription(description);
        i.setQtyOnHand(qty);
        i.setUnitCost(unitCost);
        i.setActive(active ? 1 : 0);
        i.setOverrideRetail(0);
        i.setMarkup(3);
        i.setRetailPrice((qty * unitCost) * i.getMarkup());
        return i;
    }

    /**
     * 
     * @param id
     * @param description
     * @return
     */
    public static final ItemMasterType createMockOrmItemMasterType(int id, String description) {
        ItemMasterType i = new ItemMasterType();
        i.setItemTypeId(id);
        i.setDescription(description);
        return i;
    }

    /**
     * 
     * @param id
     * @param description
     * @return
     */
    public static final ItemMasterStatus createMockOrmItemMasterStatus(int id, String description) {
        ItemMasterStatus i = new ItemMasterStatus();
        i.setItemStatusId(id);
        i.setDescription(description);
        return i;
    }

    /**
     * 
     * @param id
     * @param itemId
     * @param statusId
     * @param unitCost
     * @param markup
     * @param effDate
     * @param endDate
     * @param reason
     * @return
     */
    public static final ItemMasterStatusHist createMockOrmItemMasterStatusHistory(int id, int itemId, int statusId,
            double unitCost, double markup, String effDate, String endDate, String reason) {
        ItemMasterStatusHist i = new ItemMasterStatusHist();
        i.setItemStatusHistId(id);
        i.setItemId(itemId);
        i.setItemStatusId(statusId);
        i.setUnitCost(unitCost);
        i.setMarkup(markup);
        try {
            i.setEffectiveDate(RMT2Date.stringToDate(effDate));
        } catch (SystemException e) {
            i.setEffectiveDate(new Date());
        }
        try {
            i.setEndDate(RMT2Date.stringToDate(endDate));
        } catch (SystemException e) {
            i.setEndDate(new Date());
        }
        i.setReason(reason);
        return i;
    }

    /**
     * 
     * @param id
     * @param serialNo
     * @param vendorItemNo
     * @param creditorId
     * @param description
     * @param qty
     * @param unitCost
     * @param active
     * @return
     */
    public static final VwVendorItems createMockOrmVwVendorItems(int id, String serialNo, String vendorItemNo,
            int creditorId, String description, int qty, double unitCost) {
        VwVendorItems i = new VwVendorItems();
        i.setItemId(id);
        i.setItemSerialNo(serialNo);
        i.setVendorItemNo(vendorItemNo);
        i.setCreditorId(creditorId);
        i.setDescription(description);
        i.setQtyOnHand(qty);
        i.setUnitCost(unitCost);
        i.setOverrideRetail(0);
        i.setMarkup(3);
        return i;
    }

    /**
     * 
     * @param id
     * @param businessId
     * @param acctId
     * @param acctNo
     * @param extAcctNo
     * @param creditorTypeId
     * @return
     */
    public static final Creditor createMockOrmCreditor(int id, int businessId, int acctId, String acctNo,
            String extAcctNo, int creditorTypeId) {
        Creditor o = new Creditor();
        o.setCreditorId(id);
        o.setBusinessId(businessId);
        o.setAcctId(acctId);
        o.setAccountNumber(acctNo);
        o.setExtAccountNumber(extAcctNo);
        o.setCreditorTypeId(creditorTypeId);
        o.setCreditLimit(10000);
        o.setApr(12.5);
        o.setActive(1);
        o.setDateCreated(new Date());
        o.setDateUpdated(o.getDateCreated());
        o.setUserId("testuser");
        o.setIpCreated("111.222.101.100");
        o.setIpUpdated(o.getIpCreated());
        return o;
    }

    /**
     * 
     * @param businessId
     * @param longName
     * @param contactFirstName
     * @param contactLastName
     * @param contactPhone
     * @param contactEmail
     * @param taxId
     * @param website
     * @return
     */
    public static final BusinessType createMockJaxbBusiness(int businessId, String longName, String contactFirstName,
            String contactLastName, String contactPhone, String contactEmail, String taxId, String website) {
        ObjectFactory f = new ObjectFactory();
        BusinessType b = f.createBusinessType();
        b.setBusinessId(BigInteger.valueOf(businessId));
        b.setLongName(longName);
        b.setContactFirstname(contactFirstName);
        b.setContactLastname(contactLastName);
        b.setContactPhone(contactPhone);
        b.setContactEmail(contactEmail);
        CodeDetailType cdt1 = f.createCodeDetailType();
        cdt1.setCodeId(BigInteger.valueOf(130));
        CodeDetailType cdt2 = f.createCodeDetailType();
        cdt2.setCodeId(BigInteger.valueOf(100));
        b.setServiceType(cdt1);
        b.setEntityType(cdt2);
        b.setTaxId(taxId);
        b.setWebsite(website);
        return b;
    }

    /**
     * 
     * @param businessId
     * @param longName
     * @param contactFirstName
     * @param contactLastName
     * @param contactPhone
     * @param contactEmail
     * @param taxId
     * @param website
     * @return
     */
    public static final VwBusinessAddress createMockOrmBusinessAddress(int businessId, String longName,
            String contactFirstName, String contactLastName, String contactPhone, String contactEmail, String taxId,
            String website) {
        VwBusinessAddress b = new VwBusinessAddress();
        b.setBusinessId(businessId);
        b.setBusLongname(longName);
        b.setBusContactFirstname(contactFirstName);
        b.setBusContactLastname(contactLastName);
        b.setBusContactPhone(contactPhone);
        b.setContactEmail(contactEmail);
        b.setBusServTypeId(130);
        b.setBusEntityTypeId(100);
        b.setBusTaxId(taxId);
        b.setBusWebsite(website);

        b.setAddrId(2222);
        b.setAddrBusinessId(1351);
        b.setAddr1("94393 Hall Ave.");
        b.setAddr2("Suite 948");
        b.setAddr3("P.O. Box 84763");
        b.setZipCity("dallas");
        b.setZipState("TX");
        b.setAddrZip(75028);
        b.setAddrZipext(1234);
        return b;
    }

    /**
     * 
     * @param contactId
     * @param contactName
     * @param contactType
     * @param addressId
     * @param addr1
     * @param addr2
     * @param addr3
     * @param addr4
     * @param city
     * @param state
     * @param zip
     * @return
     */
    public static final VwCommonContact createMockOrmCommonContact(int contactId, String contactName,
            String contactType, int addressId, String addr1, String addr2, String addr3, String addr4, String city,
            String state, int zip) {
        VwCommonContact o = new VwCommonContact();
        o.setContactId(contactId);
        o.setContactName(contactName);
        o.setContactType(contactType);

        o.setAddrId(addressId);
        o.setContactId(contactId);
        o.setAddr1(addr1);
        o.setAddr2(addr2);
        o.setAddr3(addr3);
        o.setAddr4(addr4);
        o.setZipCity(city);
        o.setZipState(state);
        o.setAddrZip(zip);
        o.setAddrZipext(7001);
        return o;
    }

    /**
     * 
     * @param businessId
     * @param contactName
     * @param addressId
     * @param addr1
     * @param addr2
     * @param addr3
     * @param addr4
     * @param city
     * @param state
     * @param zip
     * @return
     */
    public static final VwBusinessAddress createMockOrmBusinessContact(
            int businessId, String contactName, int addressId, String addr1,
            String addr2, String addr3, String addr4, String city, String state,
            int zip) {
        VwBusinessAddress o = new VwBusinessAddress();
        o.setBusinessId(businessId);
        o.setBusLongname(contactName);
        o.setBusEntityTypeId(1);
        o.setBusServTypeId(2);
        o.setAddrId(addressId);
        o.setAddr1(addr1);
        o.setAddr2(addr2);
        o.setAddr3(addr3);
        o.setAddr4(addr4);
        o.setZipCity(city);
        o.setZipState(state);
        o.setAddrZip(zip);
        o.setAddrZipext(7001);
        return o;
    }

    /**
     * 
     * @param assocId
     * @param assocItemId
     * @param itemMasterId
     * @param assocType
     * @param qty
     * @param cost
     * @return
     */
    public static final VwItemAssociations createMockOrmVwItemAssociations(int assocId, int assocItemId,
            int itemMasterId, String assocType, double qty, double cost) {
        VwItemAssociations o = new VwItemAssociations();
        o.setAssocId(assocId);
        o.setAssocItemId(assocItemId);
        o.setItemId(itemMasterId);
        o.setAssocType(assocType);
        o.setOrderQty(qty);
        o.setItemCost(cost);
        return o;
    }
    
    /**
     * 
     * @param id
     * @param description
     * @return
     */
    public static final CreditorType createMockOrmCreditorType(int id, String description) {
        CreditorType o = new CreditorType();
        o.setCreditorTypeId(id);
        o.setDescription(description);
        o.setDateCreated(new Date());
        o.setDateUpdated(o.getDateCreated());
        o.setUserId("testuser");
        return o;
    }

    /**
     * 
     * @param xactId
     * @param creditorId
     * @param acctNo
     * @param xactAmt
     * @param xactDate
     * @param xactTypeId
     * @return
     */
    public static final VwCreditorXactHist createMockOrmCreditorXactHistory(
            int xactId, int creditorId, String acctNo, double xactAmt,
            Date xactDate, int xactTypeId) {
        VwCreditorXactHist o = new VwCreditorXactHist();
        o.setXactId(xactId);
        o.setCreditorTypeId(creditorId);
        o.setAccountNumber(acctNo);
        o.setXactAmount(xactAmt);
        o.setXactDate(xactDate);
        o.setXactTypeId(xactTypeId);
        o.setReason("Transaction History for creditor, " + creditorId);
        o.setDateCreated(new Date());
        o.setUserId("testuser");
        o.setActive(1);
        o.setApr(1.56);
        o.setCreditLimit(5000.00);
        o.setCreditorTypeDescription("Creditor type description for creditor, " + creditorId);
        o.setXactSubtypeId(1);
        o.setXactTypeName("Xact Type Name" + xactId);
        o.setConfirmNo(String.valueOf(o.getDateCreated().getTime()));
        o.setTenderId(35);
        o.setDocumentId(xactId + creditorId);
        o.setCreditorActivityId(xactId * o.getTenderId());
        o.setCreditorActivityAmount(xactAmt);
        return o;
    }
}
