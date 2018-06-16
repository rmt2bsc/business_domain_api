package org.dao.postal;

import java.util.Comparator;

import org.dto.RegionDto;

/**
 * Provides functionality to compare two RegionDto instances by either the State
 * Name, State Id, or State Code properties.
 * 
 * 
 * @author Roy Terrell
 * 
 */
public class RegionDtoComparator implements Comparator<RegionDto> {

    public static final String SORT_NAME = "Name";

    public static final String SORT_ID = "Id";

    public static final String SORT_CODE = "Code";

    private String sortPropName;

    /**
     * Constructs a RegionDtoComparator object which the default sort property
     * is "Name".
     */
    public RegionDtoComparator() {
        this.sortPropName = RegionDtoComparator.SORT_NAME;
        return;
    }

    /**
     * Constructs a RegionDtoComparator object by assigning the sort property.
     * 
     * @param propName
     *            The name of the column to order by.
     */
    public RegionDtoComparator(String propName) {
        this.sortPropName = propName;
        return;
    }

    /**
     * Orders <i>region1</i> and <i>region2</i> based on the column set forth as
     * either "Name", "Id", or "Code".
     * 
     * @param region1
     *            The first region object to compare
     * @param region2
     *            The second region object to compare.
     * @return 0 if the strings are equal or if region1 and/or region2 are not
     *         instances of RegionDto, a negative integer if the target String
     *         is less than the specified String, or a positive integer if the
     *         target String is greater than the specified String
     */
    public int compare(RegionDto region1, RegionDto region2) {
        if (this.sortPropName.equals(RegionDtoComparator.SORT_NAME)) {
            return region1.getStateName().toLowerCase().compareTo(region2.getStateName().toLowerCase());
        }
        else if (this.sortPropName.equals(RegionDtoComparator.SORT_ID)) {
            Integer value1 = new Integer(region1.getStateId());
            Integer value2 = new Integer(region2.getStateId());
            return value1.compareTo(value2);
        }
        else if (this.sortPropName.equals(RegionDtoComparator.SORT_CODE)) {
            return region1.getStateCode().toLowerCase().compareTo(region2.getStateCode().toLowerCase());
        }
        return 0;
    }

}
