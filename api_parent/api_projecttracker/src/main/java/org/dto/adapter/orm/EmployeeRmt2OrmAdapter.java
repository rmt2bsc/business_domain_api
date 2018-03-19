package org.dto.adapter.orm;

import java.util.Date;

import org.dao.mapping.orm.rmt2.ProjEmployee;
import org.dao.mapping.orm.rmt2.ProjEmployeeTitle;
import org.dao.mapping.orm.rmt2.ProjEmployeeType;
import org.dao.mapping.orm.rmt2.VwEmployeeExt;
import org.dto.EmployeeDto;
import org.dto.EmployeeTitleDto;
import org.dto.EmployeeTypeDto;

import com.api.foundation.TransactionDtoImpl;

/**
 * A DTO implementation that adapts data pertaining to the RMT2 ORM objects
 * {@link ProjEmployee}, {@link ProjEmployeeTitle}, {@link ProjEmployeeType},
 * {@link VwEmployeeExt} .
 * 
 * @author Roy Terrell
 * 
 */
class EmployeeRmt2OrmAdapter extends TransactionDtoImpl implements EmployeeDto,
        EmployeeTitleDto, EmployeeTypeDto {

    private ProjEmployee emp;
    private ProjEmployeeTitle empTitle;
    private ProjEmployeeType empType;
    private VwEmployeeExt empExt;

    /**
     * Creates a EmployeeRmt2OrmAdapter object in which all data members are
     * initialized to null.
     */
    EmployeeRmt2OrmAdapter() {
        this.emp = null;
        this.empTitle = null;
        this.empType = null;
        return;
    }

    /**
     * Creates an instance of EmployeeRmt2OrmAdapter which adapts a
     * {@link ProjEmployee} object.
     * 
     * @param emp
     *            an instance of {@link ProjEmployee}
     */
    protected EmployeeRmt2OrmAdapter(ProjEmployee employee) {
        this();
        if (employee == null) {
            employee = new ProjEmployee();
        }
        this.emp = employee;
        this.setDateCreated(employee.getDateCreated());
        this.setDateUpdated(employee.getDateUpdated());
        this.setUpdateUserId(employee.getUserId());
        return;
    }

    /**
     * Creates an instance of EmployeeRmt2OrmAdapter which adapts a
     * {@link ProjEmployeeTitle} object.
     * 
     * @param employeeTitle
     *            an instance of {@link ProjEmployeeTitle}
     */
    protected EmployeeRmt2OrmAdapter(ProjEmployeeTitle employeeTitle) {
        this();
        if (employeeTitle == null) {
            employeeTitle = new ProjEmployeeTitle();
        }
        this.empTitle = employeeTitle;
        return;
    }

    /**
     * Creates an instance of EmployeeRmt2OrmAdapter which adapts a
     * {@link ProjEmployeeType} object.
     * 
     * @param employeeType
     *            an instance of {@link ProjEmployeeType}
     */
    protected EmployeeRmt2OrmAdapter(ProjEmployeeType employeeType) {
        this();
        if (employeeType == null) {
            employeeType = new ProjEmployeeType();
        }
        this.empType = employeeType;
        return;
    }

    /**
     * Creates an instance of EmployeeRmt2OrmAdapter which adapts a
     * {@link VwEmployeeExt} object.
     * 
     * @param emp
     *            an instance of {@link VwEmployeeExt}
     */
    protected EmployeeRmt2OrmAdapter(VwEmployeeExt employee) {
        this();
        if (employee == null) {
            employee = new VwEmployeeExt();
        }
        this.empExt = employee;
        this.emp = EmployeeObjectFactory.createOrmEmployee(employee);
        return;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeId(int)
     */
    @Override
    public void setEmployeeId(int value) {
        this.emp.setEmpId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeId()
     */
    @Override
    public int getEmployeeId() {
        return this.emp.getEmpId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setLoginId(int)
     */
    @Override
    public void setLoginId(int value) {
        this.emp.setLoginId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getLoginId()
     */
    @Override
    public int getLoginId() {
        return this.emp.getLoginId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setLoginName(java.lang.String)
     */
    @Override
    public void setLoginName(String value) {
        this.emp.setLoginName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getLoginName()
     */
    @Override
    public String getLoginName() {
        return this.emp.getLoginName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setStartDate(java.util.Date)
     */
    @Override
    public void setStartDate(Date value) {
        this.emp.setStartDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getStartDate()
     */
    @Override
    public Date getStartDate() {
        return this.emp.getStartDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setTerminationDate(java.util.Date)
     */
    @Override
    public void setTerminationDate(Date value) {
        this.emp.setTerminationDate(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getTerminationDate()
     */
    @Override
    public Date getTerminationDate() {
        return this.emp.getTerminationDate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setManagerId(int)
     */
    @Override
    public void setManagerId(int value) {
        this.emp.setManagerId(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getManagerId()
     */
    @Override
    public int getManagerId() {
        return this.emp.getManagerId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeFirstname(java.lang.String)
     */
    @Override
    public void setEmployeeFirstname(String value) {
        this.emp.setFirstname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeFirstname()
     */
    @Override
    public String getEmployeeFirstname() {
        return this.emp.getFirstname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeLastname(java.lang.String)
     */
    @Override
    public void setEmployeeLastname(String value) {
        this.emp.setLastname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeLastname()
     */
    @Override
    public String getEmployeeLastname() {
        return this.emp.getLastname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeShortname(java.lang.String)
     */
    @Override
    public void setEmployeeFullname(String value) {
        this.empExt.setShortname(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeShortname()
     */
    @Override
    public String getEmployeeFullname() {
        return this.empExt.getShortname();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeCompanyName(java.lang.String)
     */
    @Override
    public void setEmployeeCompanyName(String value) {
        this.emp.setCompanyName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeCompanyName()
     */
    @Override
    public String getEmployeeCompanyName() {
        return this.emp.getCompanyName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setSsn(java.lang.String)
     */
    @Override
    public void setSsn(String value) {
        this.emp.setSsn(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getSsn()
     */
    @Override
    public String getSsn() {
        return this.emp.getSsn();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setIsManager(int)
     */
    @Override
    public void setIsManager(int value) {
        this.emp.setIsManager(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getIsManager()
     */
    @Override
    public int getIsManager() {
        return this.emp.getIsManager();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeEmail(java.lang.String)
     */
    @Override
    public void setEmployeeEmail(String value) {
        this.emp.setEmail(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeEmail()
     */
    @Override
    public String getEmployeeEmail() {
        return this.emp.getEmail();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeTitleId(int)
     */
    @Override
    public void setEmployeeTitleId(int value) {
        if (this.emp != null) {
            this.emp.setEmpTitleId(value);
        }
        if (this.empTitle != null) {
            this.empTitle.setEmpTitleId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeTitleId()
     */
    @Override
    public int getEmployeeTitleId() {
        if (this.emp != null) {
            return this.emp.getEmpTitleId();
        }
        if (this.empTitle != null) {
            return this.empTitle.getEmpTitleId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeTypeId(int)
     */
    @Override
    public void setEmployeeTypeId(int value) {
        if (this.emp != null) {
            this.emp.setEmpTypeId(value);
        }
        if (this.empType != null) {
            this.empType.setEmpTypeId(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeTypeId()
     */
    @Override
    public int getEmployeeTypeId() {
        if (this.emp != null) {
            return this.emp.getEmpTypeId();
        }
        if (this.empType != null) {
            return this.empType.getEmpTypeId();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeTitle(java.lang.String)
     */
    @Override
    public void setEmployeeTitle(String value) {
        this.empExt.setEmployeeTitle(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeTitle()
     */
    @Override
    public String getEmployeeTitle() {
        return this.empExt.getEmployeeTitle();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#setEmployeeType(java.lang.String)
     */
    @Override
    public void setEmployeeType(String value) {
        this.empExt.setEmployeeType(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeDto#getEmployeeType()
     */
    @Override
    public String getEmployeeType() {
        return this.empExt.getEmployeeType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeTypeDto#setEmployeeTypeDescription(java.lang.String)
     */
    @Override
    public void setEmployeeTypeDescription(String value) {
        if (this.empExt != null) {
            this.empExt.setEmployeeType(value);    
        }
        if (this.empType != null) {
            this.empType.setDescription(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeTypeDto#getEmployeeTypeDescription()
     */
    @Override
    public String getEmployeeTypeDescription() {
        if (this.empExt != null) {
            return this.empExt.getEmployeeType();    
        }
        if (this.empType != null) {
            return this.empType.getDescription();
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.dto.EmployeeTitleDto#setEmployeeTitleDescription(java.lang.String)
     */
    @Override
    public void setEmployeeTitleDescription(String value) {
        if (this.empExt != null) {
            this.empExt.setEmployeeTitle(value);
        }
        if (this.empTitle != null) {
            this.empTitle.setDescription(value);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.EmployeeTitleDto#getEmployeeTitleDescription()
     */
    @Override
    public String getEmployeeTitleDescription() {
        if (this.empExt != null) {
            return this.empExt.getEmployeeTitle();    
        }
        if (this.empTitle != null) {
            return this.empTitle.getDescription();
        }
        return null;
    }

}
