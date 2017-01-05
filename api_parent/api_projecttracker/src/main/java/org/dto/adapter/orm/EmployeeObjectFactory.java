package org.dto.adapter.orm;

import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.VwEmployeeExt;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;

import com.RMT2Base;

/**
 * A factory containing several methods to create adapters and converters for
 * obtaining employee reltated DTO's.
 * 
 * @author Roy Terrell.
 * 
 */
public class EmployeeObjectFactory extends RMT2Base {

    /**
     * Create an instance of <i>EmployeeDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjEmployee}
     * 
     * @return an instance of {@link EmployeeDto}.
     */
    public static final EmployeeDto createEmployeeDtoInstance(
            ProjEmployee ormBean) {
        return new EmployeeRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>EmployeeDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link VwEmployeeExt}
     * 
     * @return an instance of {@link EmployeeDto}.
     */
    public static final EmployeeDto createEmployeeExtendedDtoInstance(
            VwEmployeeExt ormBean) {
        return new EmployeeRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>EmployeeTitleDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjEmployeeTitle}
     * 
     * @return an instance of {@link EmployeeTitleDto}.
     */
    public static final EmployeeTitleDto createEmployeeTitleDtoInstance(
            ProjEmployeeTitle ormBean) {
        return new EmployeeRmt2OrmAdapter(ormBean);
    }

    /**
     * Create an instance of <i>EmployeeTypeDto</i>.
     * 
     * @param ormBean
     *            an instance of {@link ProjEmployeeType}
     * 
     * @return an instance of {@link EmployeeTypeDto}.
     */
    public static final EmployeeTypeDto createEmployeeTypeDtoInstance(
            ProjEmployeeType ormBean) {
        return new EmployeeRmt2OrmAdapter(ormBean);
    }

    /**
     * Create a <i>ProjEmployee</i> object from a <i>VwEmployeeExt</i> object.
     * 
     * @param src
     *            an instnace of {@link VwEmployeeExt}
     * @return
     */
    public static ProjEmployee createOrmEmployee(VwEmployeeExt src) {
        ProjEmployee employee = new ProjEmployee();
        employee.setEmpId(src.getEmployeeId());
        employee.setEmpTypeId(src.getTypeId());
        employee.setEmpTitleId(src.getTitleId());
        employee.setManagerId(src.getManagerId());
        employee.setLoginId(src.getLoginId());
        employee.setStartDate(src.getStartDate());
        employee.setTerminationDate(src.getTerminationDate());
        employee.setFirstname(src.getFirstname());
        employee.setLastname(src.getLastname());
        employee.setSsn(src.getSsn());
        employee.setEmail(src.getEmail());
        employee.setLoginName(src.getLoginName());
        employee.setCompanyName(src.getCompanyName());
        employee.setIsManager(src.getIsManager());
        return employee;
    }
}
