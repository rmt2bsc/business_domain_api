package org.dao.subsidiary;

import java.util.Comparator;

import org.dto.SubsidiaryContactInfoDto;

/**
 * Provides functionality to compare the either the ContactName or ContactId
 * properties of two {@link org.dto.BusinessSubsidiaryContactDto} instances.
 * 
 * 
 * @author RTerrell
 * 
 */
@SuppressWarnings("rawtypes")
public class SubsidiaryComparator implements Comparator {
    public static final String SORT_NAME = "Name";

    public static final String SORT_ID = "Id";

    private String sortPropName;

    /**
     * Constructs a SubsidiaryComparator object which the default sort property
     * is "Name".
     */
    public SubsidiaryComparator() {
        this.sortPropName = SubsidiaryComparator.SORT_NAME;
        return;
    }

    /**
     * Constructs a SubsidiaryComparator object by assigning the sort property.
     * 
     * @param propName
     *            The name of the column to order by.
     */
    public SubsidiaryComparator(String propName) {
        this.sortPropName = propName;
        return;
    }

    /**
     * Compares two SubsidiaryContactInfoDto objects based on a specific
     * property.
     * 
     * @param obj1
     *            The target SubsidiaryContactInfoDto object.
     * @param obj2
     *            The SubsidiaryContactInfoDto object to compare.
     * @return 0 if the strings are equal or if obj1 and/or obj2 are not
     *         instances of SubsidiaryContactInfoDto, a negative integer if the
     *         target String is less than the specified String, or a positive
     *         integer if the target String is greater than the specified String
     */
    public int compare(Object obj1, Object obj2) {
        SubsidiaryContactInfoDto contact1;
        SubsidiaryContactInfoDto contact2;
        if (obj1 instanceof SubsidiaryContactInfoDto
                && obj2 instanceof SubsidiaryContactInfoDto) {
            contact1 = (SubsidiaryContactInfoDto) obj1;
            contact2 = (SubsidiaryContactInfoDto) obj2;
        }
        else {
            return 0;
        }

        return this.sort(contact1, contact2);
    }

    /**
     * Orders <i>contact1</i> and <i>contact2</i> based on the column set forth
     * as either "Name" or "Id".
     * 
     * @param contact1
     *            The first common subsidiary object
     * @param contact2
     *            The second common subsidiary object.
     * @return a negative, zero, or a positive integer as <i>contact1</i>
     *         argument is less than, equal to, or greater than <i>contac2</i>,
     *         respectively.
     */
    private int sort(SubsidiaryContactInfoDto contact1,
            SubsidiaryContactInfoDto contact2) {
        if (this.sortPropName.equals(SubsidiaryComparator.SORT_NAME)) {
            return contact1.getContactName().toLowerCase()
                    .compareTo(contact2.getContactName().toLowerCase());
        }
        else if (this.sortPropName.equals(SubsidiaryComparator.SORT_ID)) {
            Integer value1 = new Integer(contact1.getContactId());
            Integer value2 = new Integer(contact2.getContactId());
            return value1.compareTo(value2);
        }
        return 0;
    }
}
