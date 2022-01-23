package org.dto.adapter.orm.account.subsidiary;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dao.mapping.orm.rmt2.Creditor;
import org.dao.mapping.orm.rmt2.CreditorType;
import org.dao.mapping.orm.rmt2.Customer;
import org.dao.mapping.orm.rmt2.VwCreditorXactHist;
import org.dao.mapping.orm.rmt2.VwCustomerXactHist;
import org.dto.BusinessContactDto;
import org.dto.ContactDto;
import org.dto.CreditorDto;
import org.dto.CreditorTypeDto;
import org.dto.CreditorXactHistoryDto;
import org.dto.CustomerDto;
import org.dto.CustomerXactHistoryDto;
import org.dto.SubsidiaryContactInfoDto;
import org.rmt2.jaxb.BusinessType;

import com.RMT2Base;

/**
 * A factory containing several adapters which function to create various
 * subsidiary related objects.
 * <p>
 * An exampleof a subsidiary type would be creditors and customers.
 * 
 * @author Roy Terrell.
 * 
 */
public class Rmt2SubsidiaryDtoFactory extends RMT2Base {

    /**
     * Create an instance of <i>CreditorDto</i>.
     * <p>
     * A brand new instance of CreditorDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of CreditorDto.
     * 
     * @param ormBean
     *            an instance of {@link Creditor}
     * @param jaxbContact
     *            an instance of {@link BusinessType}
     * @return an instance of {@link CreditorDto}.
     */
    public static final CreditorDto createCreditorInstance(Creditor ormBean, BusinessType jaxbContact) {
        return new CreditorRmt2OrmAdapter(ormBean, jaxbContact);
    }

    /**
     * Create an instance of <i>CreditorTypeDto</i>.
     * <p>
     * A brand new instance of CreditorTypeDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of
     * CreditorTypeDto.
     * 
     * @param ormBean
     *            an instance of {@link CreditorType}
     * @return an instance of {@link CreditorTypeDto}.
     */
    public static final CreditorTypeDto createCreditorTypeInstance(CreditorType ormBean) {
        return new CreditorTypeRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>CreditorXactHistoryDto</i>.
     * <p>
     * A brand new instance of CreditorXactHistoryDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of CreditorXactHistoryDto.
     * 
     * @param ormBean
     *            an instance of {@link VwCreditorXactHist}
     * @return an instance of {@link CreditorXactHistoryDto}.
     */
    public static final CreditorXactHistoryDto createCreditorTransactionInstance(
            VwCreditorXactHist ormBean) {
        return new CreditorXactHistoryRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>CustomerDto</i>.
     * <p>
     * A brand new instance of CustomerDto is created when <i>ormBean</i> is
     * null. Otherwise, <i>ormBean</i> is adapted to an instance of CustomerDto.
     * 
     * @param ormBean
     *            an instance of {@link Customer}
     * @param jaxbContact
     *            an instance of {@link BusinessType}
     * @return an instance of {@link CustomerDto}.
     */
    public static final CustomerDto createCustomerInstance(Customer ormBean,
            BusinessType jaxbContact) {
        return new CustomerRmt2OrmAdapter(ormBean, jaxbContact);
    }

    /**
     * Create an instance of <i>CustomerXactHistoryDto</i>.
     * <p>
     * A brand new instance of CustomerXactHistoryDto is created when
     * <i>ormBean</i> is null. Otherwise, <i>ormBean</i> is adapted to an
     * instance of CustomerXactHistoryDto.
     * 
     * @param ormBean
     *            an instance of {@link VwCustomerXactHist}
     * @return an instance of {@link CustomerXactHistoryDto}.
     */
    public static final CustomerXactHistoryDto createCustomerTransactionInstance(
            VwCustomerXactHist ormBean) {
        return new CustomerXactHistoryRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>SubsidiaryContactInfoDto</i>.
     * <p>
     * A brand new instance of SubsidiaryContactInfoDto is created when
     * <i>jaxbContact</i> is null. Otherwise, <i>jaxbContact</i> is adapted to
     * an instance of SubsidiaryContactInfoDto.
     * 
     * @param jaxbContact
     *            an instance of {@link BusinessType}
     * @return an instance of {@link SubsidiaryContactInfoDto}.
     */
    public static final SubsidiaryContactInfoDto createSubsidiaryInstance(
            BusinessType jaxbContact) {
        return new BusinessContactJaxbAdapter(jaxbContact);
    }

    /**
     * Creates a List of CustomerBean objects from a List of BusinessType
     * objects.
     * 
     * @param contacts
     *            List of {@link BusinessType} objects
     * @return List {@link SubsidiaryContactInfoDto} or null if <i>contacts</i>
     *         is null.
     */
    public static final List<SubsidiaryContactInfoDto> createBusinessContact(
            List<BusinessType> contacts) {
        if (contacts == null) {
            return null;
        }
        List<SubsidiaryContactInfoDto> list = new ArrayList<SubsidiaryContactInfoDto>();
        for (BusinessType btItem : contacts) {
            SubsidiaryContactInfoDto contact = null;
            if (btItem.getBusinessId() == null) {
                contact = Rmt2SubsidiaryDtoFactory
                        .createSubsidiaryInstance(null);
            }
            else {
                contact = Rmt2SubsidiaryDtoFactory
                        .createSubsidiaryInstance(btItem);
            }
            list.add(contact);
        }
        return list;
    }

    /**
     * Creates a Map instance of SubsidiaryContactInfoDto objects where each
     * element is keyed by business id.
     * 
     * @param contacts
     *            a List of BusinessType instances.
     * @return Map {@link Integer}, {@link SubsidiaryContactInfoDto}
     */
    public static final Map<Integer, SubsidiaryContactInfoDto> createBusinessContactMap(
            List<BusinessType> contacts) {
        Map<Integer, SubsidiaryContactInfoDto> map = new LinkedHashMap<Integer, SubsidiaryContactInfoDto>();
        for (BusinessType item : contacts) {
            SubsidiaryContactInfoDto dto = createSubsidiaryInstance(item);
            map.put(item.getBusinessId().intValue(), dto);
        }
        return map;
    }
    
    /**
     * Creates a Map instance of SubsidiaryContactInfoDto objects where each
     * element is keyed by business id.
     * 
     * @param contacts
     *            a List of ContactDto instances.
     * @return Map {@link Integer}, {@link SubsidiaryContactInfoDto}
     */
    public static final Map<Integer, SubsidiaryContactInfoDto> createContactMap(List<ContactDto> contacts) {
        Map<Integer, SubsidiaryContactInfoDto> map = new LinkedHashMap<Integer, SubsidiaryContactInfoDto>();
        if (contacts == null) {
            return null;
        }
        for (ContactDto contact : contacts) {
            BusinessContactDto item = null;
            if (contact instanceof BusinessContactDto) {
                item = (BusinessContactDto) contact;
                SubsidiaryContactInfoDto dto = Rmt2SubsidiaryDtoFactory.createSubsidiaryInstance(null);
                dto.setContactId(item.getContactId());
                dto.setAddr1(item.getAddr1());
                dto.setAddr2(item.getAddr2());
                dto.setAddr3(item.getAddr3());
                dto.setAddr4(item.getAddr4());
                dto.setCity(item.getCity());
                dto.setState(item.getState());
                dto.setZip(item.getZip());
                dto.setZipext(item.getZipext());
                dto.setContactFirstname(item.getContactFirstname());
                dto.setContactLastname(item.getContactLastname());
                dto.setContactName(item.getContactName());
                dto.setContactEmail(item.getContactEmail());
                dto.setContactExt(item.getContactExt());
                dto.setContactType(item.getContactType());
                dto.setContactPhone(item.getContactPhone());
                dto.setPhoneCell(item.getPhoneCell());
                dto.setPhoneCompany(item.getPhoneCompany());
                dto.setPhoneExt(item.getPhoneExt());
                dto.setPhoneFax(item.getPhoneFax());
                dto.setPhoneHome(item.getPhoneHome());
                dto.setPhonePager(item.getPhonePager());
                dto.setPhoneWork(item.getPhoneWork());
                dto.setEntityTypeId(item.getEntityTypeId());
                dto.setServTypeId(item.getServTypeId());
                map.put(dto.getContactId(), dto);
            }
        }
        return map;
    }
}
