package org.dao.subsidiary;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorActivity;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.CustomerActivity;
import org.dto.CreditorDto;
import org.dto.CustomerDto;
import org.dto.adapter.orm.account.subsidiary.CustomerExt;

import com.RMT2Base;
import com.api.persistence.DaoClient;

/**
 * Factory class for creating subsidiary DAO related objects.
 * 
 * @author Roy Terrell
 * 
 */
public class SubsidiaryDaoFactory extends RMT2Base {

    /**
     * Default constructory
     */
    public SubsidiaryDaoFactory() {
        return;
    }

    /**
     * Creates an instance of <i>SubsidiaryDao</i> using the RMT2 subsidiary
     * contact ORM implementation.
     * 
     * @return an instance of {@link SubsidiaryDao}
     */
    public SubsidiaryDao createRmt2OrmSubsidiaryDao() {
        SubsidiaryDao dao = new DefaultRmt2SubsidiaryContactDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>SubsidiaryDao</i> using the RMT2 subsidiary
     * contact ORM implementation.
     * 
     * @return an instance of {@link SubsidiaryDao}
     */
    public SubsidiaryDao createRmt2OrmSubsidiaryDao(String appName) {
        SubsidiaryDao dao = new DefaultRmt2SubsidiaryContactDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>CustomerDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @return an instance of {@link CustomerDao}
     */
    public CustomerDao createRmt2OrmCustomerDao() {
        CustomerDao dao = new Rmt2OrmCustomerDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>CreditorDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @return an instance of {@link CreditorDao}
     */
    public CreditorDao createRmt2OrmCreditorDao() {
        CreditorDao dao = new Rmt2OrmCreditorDaoImpl();
        return dao;
    }

    /**
     * Creates an instance of <i>CustomerDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     * @return an instance of {@link CustomerDao}
     */
    public CustomerDao createRmt2OrmCustomerDao(String appName) {
        CustomerDao dao = new Rmt2OrmCustomerDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>CreditorDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param appName
     * @return an instance of {@link CreditorDao}
     */
    public CreditorDao createRmt2OrmCreditorDao(String appName) {
        CreditorDao dao = new Rmt2OrmCreditorDaoImpl(appName);
        return dao;
    }

    /**
     * Creates an instance of <i>CustomerDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link CustomerDao}
     */
    public CustomerDao createRmt2OrmCustomerDao(DaoClient dao) {
        CustomerDao d = new Rmt2OrmCustomerDaoImpl(dao.getClient());
        return d;
    }

    /**
     * Creates an instance of <i>CreditorDao</i> using the RMT2 ORM basic
     * maintenance implementation.
     * 
     * @param client
     *            an instnace of {@link PersistenceClient}
     * 
     * @return an instance of {@link CreditorDao}
     */
    public CreditorDao createRmt2OrmCreditorDao(DaoClient dao) {
        CreditorDao d = new Rmt2OrmCreditorDaoImpl(dao.getClient());
        return d;
    }

    /**
     * Creates a Customer object from an CustomerDto.
     * 
     * @param dto
     *            an instance of {@link CustomerDto}
     * @return an instance of {@link Customer} or null if <i>dto</i> is null.
     */
    public static final Customer createRmt2OrmCustomerBean(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        Customer c = new Customer();
        c.setAccountNo(dto.getAccountNo());
        c.setAcctId(dto.getAcctId());
        c.setActive(dto.getActive());
        c.setBusinessId(dto.getContactId());
        c.setCreditLimit(dto.getCreditLimit());
        c.setCustomerId(dto.getCustomerId());
        c.setDateCreated(dto.getDateCreated());
        c.setDateUpdated(dto.getDateUpdated());
        c.setUserId(dto.getUpdateUserId());
        c.setIpCreated(dto.getIpCreated());
        c.setIpUpdated(dto.getIpUpdated());
        return c;
    }

    /**
     * Creates a CustomerExt object from an CustomerDto.
     * 
     * @param dto
     *            an instance of {@link CustomerDto}
     * @return an instance of {@link CustomerExt} or null if <i>dto</i> is null.
     */
    public static final CustomerExt createCustomerExtBean(CustomerDto dto) {
        if (dto == null) {
            return null;
        }
        CustomerExt c = new CustomerExt();
        c.setAccountNo(dto.getAccountNo());
        c.setActive(dto.getActive());
        c.setBusinessId(dto.getContactId());
        c.setCreditLimit(dto.getCreditLimit());
        c.setCustomerId(dto.getCustomerId());
        c.setGlAccountId(dto.getAcctId());
        c.setBusinessId(dto.getContactId());
        c.setName(dto.getContactName());
        c.setShortname(dto.getShortName());
        c.setServType(dto.getServTypeId());
        c.setBusType(dto.getEntityTypeId());
        c.setContactFirstname(dto.getContactFirstname());
        c.setContactLastname(dto.getContactLastname());
        c.setContactPhone(dto.getContactPhone());
        c.setContactExt(dto.getContactExt());
        c.setContactEmail(dto.getContactEmail());
        c.setTaxId(dto.getTaxId());
        c.setWebsite(dto.getWebsite());
        c.setBalance(0);
        c.setAddrId(dto.getAddrId());
        c.setAddrPhoneCell(dto.getPhoneCell());
        c.setAddrPhoneExt(dto.getPhoneExt());
        c.setAddrPhoneFax(dto.getPhoneFax());
        c.setAddrPhoneHome(dto.getPhoneHome());
        c.setAddrPhoneMain(dto.getPhoneCompany());
        c.setAddrPhonePager(dto.getPhonePager());
        c.setAddrPhoneWork(dto.getPhoneWork());
        c.setAddrZip(dto.getZip());
        c.setAddrZipext(dto.getZipext());
        c.setAddr1(dto.getAddr1());
        c.setAddr2(dto.getAddr2());
        c.setAddr3(dto.getAddr3());
        c.setAddr4(dto.getAddr4());
        c.setZipCity(dto.getCity());
        c.setZipState(dto.getState());
        c.setDateCreated(dto.getDateCreated());
        c.setDateUpdated(dto.getDateUpdated());
        c.setUserId(dto.getUpdateUserId());
        return c;
    }

    /**
     * Creates a Creditor object from an CreditorDto.
     * 
     * @param dto
     *            an instance of {@link CreditorDto}
     * @return an instance of {@link Creditor} or null if <i>dto</i> is null.
     */
    public static final Creditor createRmt2OrmCreditorBean(CreditorDto dto) {
        if (dto == null) {
            return null;
        }
        Creditor c = new Creditor();
        c.setAccountNumber(dto.getAccountNo());
        c.setAcctId(dto.getAcctId());
        c.setActive(dto.getActive());
        c.setBusinessId(dto.getContactId());
        c.setCreditLimit(dto.getCreditLimit());
        c.setCreditorId(dto.getCreditorId());
        c.setCreditorTypeId(dto.getCreditorTypeId());
        c.setApr(dto.getApr());
        c.setExtAccountNumber(dto.getExtAccountNumber());
        c.setDateCreated(dto.getDateCreated());
        c.setDateUpdated(dto.getDateUpdated());
        c.setUserId(dto.getUpdateUserId());
        c.setIpCreated(dto.getIpCreated());
        c.setIpUpdated(dto.getIpUpdated());
        return c;
    }

    public static CustomerActivity createCustomerActivity() {
        CustomerActivity obj = null;
        try {
            obj = new CustomerActivity();
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public static CustomerActivity createCustomerActivity(int customerId,
            int xactId, double amount) {
        CustomerActivity obj = null;
        obj = SubsidiaryDaoFactory.createCustomerActivity();
        obj.setCustomerId(customerId);
        obj.setXactId(xactId);
        obj.setAmount(amount);
        return obj;
    }

    public static CreditorActivity createCreditorActivity() {
        CreditorActivity obj = null;
        try {
            obj = new CreditorActivity();
            return obj;
        } catch (Exception e) {
            return null;
        }
    }

    public static CreditorActivity createCreditorActivity(int customerId,
            int xactId, double amount) {
        CreditorActivity obj = null;
        obj = SubsidiaryDaoFactory.createCreditorActivity();
        obj.setCreditorId(customerId);
        obj.setXactId(xactId);
        obj.setAmount(amount);
        return obj;
    }

}
