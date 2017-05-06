package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;
import com.util.assistants.EqualityAssistant;
import com.util.assistants.HashCodeAssistant;
import com.api.persistence.db.orm.OrmBean;
import com.SystemException;


/**
 * Peer object that maps to the vw_timesheet_summary database table/view.
 *
 * @author auto generated.
 */
public class VwTimesheetSummary extends OrmBean {




	// Property name constants that belong to respective DataSource, VwTimesheetSummaryView

/** The property name constant equivalent to property, TimesheetId, of respective DataSource view. */
  public static final String PROP_TIMESHEETID = "TimesheetId";
/** The property name constant equivalent to property, Shortname, of respective DataSource view. */
  public static final String PROP_SHORTNAME = "Shortname";
/** The property name constant equivalent to property, DisplayValue, of respective DataSource view. */
  public static final String PROP_DISPLAYVALUE = "DisplayValue";
/** The property name constant equivalent to property, EndPeriod, of respective DataSource view. */
  public static final String PROP_ENDPERIOD = "EndPeriod";
/** The property name constant equivalent to property, DocumentId, of respective DataSource view. */
  public static final String PROP_DOCUMENTID = "DocumentId";
/** The property name constant equivalent to property, Day1Hrs, of respective DataSource view. */
  public static final String PROP_DAY1HRS = "Day1Hrs";
/** The property name constant equivalent to property, Day2Hrs, of respective DataSource view. */
  public static final String PROP_DAY2HRS = "Day2Hrs";
/** The property name constant equivalent to property, Day3Hrs, of respective DataSource view. */
  public static final String PROP_DAY3HRS = "Day3Hrs";
/** The property name constant equivalent to property, Day4Hrs, of respective DataSource view. */
  public static final String PROP_DAY4HRS = "Day4Hrs";
/** The property name constant equivalent to property, Day5Hrs, of respective DataSource view. */
  public static final String PROP_DAY5HRS = "Day5Hrs";
/** The property name constant equivalent to property, Day6Hrs, of respective DataSource view. */
  public static final String PROP_DAY6HRS = "Day6Hrs";
/** The property name constant equivalent to property, Day7Hrs, of respective DataSource view. */
  public static final String PROP_DAY7HRS = "Day7Hrs";
/** The property name constant equivalent to property, TotalHrs, of respective DataSource view. */
  public static final String PROP_TOTALHRS = "TotalHrs";



	/** The javabean property equivalent of database column vw_timesheet_summary.timesheet_id */
  private int timesheetId;
/** The javabean property equivalent of database column vw_timesheet_summary.shortname */
  private String shortname;
/** The javabean property equivalent of database column vw_timesheet_summary.display_value */
  private String displayValue;
/** The javabean property equivalent of database column vw_timesheet_summary.end_period */
  private java.util.Date endPeriod;
/** The javabean property equivalent of database column vw_timesheet_summary.document_id */
  private int documentId;
/** The javabean property equivalent of database column vw_timesheet_summary.day1_hrs */
  private double day1Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.day2_hrs */
  private double day2Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.day3_hrs */
  private double day3Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.day4_hrs */
  private double day4Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.day5_hrs */
  private double day5Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.day6_hrs */
  private double day6Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.day7_hrs */
  private double day7Hrs;
/** The javabean property equivalent of database column vw_timesheet_summary.total_hrs */
  private double totalHrs;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public VwTimesheetSummary() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable timesheetId
 */
  public void setTimesheetId(int value) {
    this.timesheetId = value;
  }
/**
 * Gets the value of member variable timesheetId
 */
  public int getTimesheetId() {
    return this.timesheetId;
  }
/**
 * Sets the value of member variable shortname
 */
  public void setShortname(String value) {
    this.shortname = value;
  }
/**
 * Gets the value of member variable shortname
 */
  public String getShortname() {
    return this.shortname;
  }
/**
 * Sets the value of member variable displayValue
 */
  public void setDisplayValue(String value) {
    this.displayValue = value;
  }
/**
 * Gets the value of member variable displayValue
 */
  public String getDisplayValue() {
    return this.displayValue;
  }
/**
 * Sets the value of member variable endPeriod
 */
  public void setEndPeriod(java.util.Date value) {
    this.endPeriod = value;
  }
/**
 * Gets the value of member variable endPeriod
 */
  public java.util.Date getEndPeriod() {
    return this.endPeriod;
  }
/**
 * Sets the value of member variable documentId
 */
  public void setDocumentId(int value) {
    this.documentId = value;
  }
/**
 * Gets the value of member variable documentId
 */
  public int getDocumentId() {
    return this.documentId;
  }
/**
 * Sets the value of member variable day1Hrs
 */
  public void setDay1Hrs(double value) {
    this.day1Hrs = value;
  }
/**
 * Gets the value of member variable day1Hrs
 */
  public double getDay1Hrs() {
    return this.day1Hrs;
  }
/**
 * Sets the value of member variable day2Hrs
 */
  public void setDay2Hrs(double value) {
    this.day2Hrs = value;
  }
/**
 * Gets the value of member variable day2Hrs
 */
  public double getDay2Hrs() {
    return this.day2Hrs;
  }
/**
 * Sets the value of member variable day3Hrs
 */
  public void setDay3Hrs(double value) {
    this.day3Hrs = value;
  }
/**
 * Gets the value of member variable day3Hrs
 */
  public double getDay3Hrs() {
    return this.day3Hrs;
  }
/**
 * Sets the value of member variable day4Hrs
 */
  public void setDay4Hrs(double value) {
    this.day4Hrs = value;
  }
/**
 * Gets the value of member variable day4Hrs
 */
  public double getDay4Hrs() {
    return this.day4Hrs;
  }
/**
 * Sets the value of member variable day5Hrs
 */
  public void setDay5Hrs(double value) {
    this.day5Hrs = value;
  }
/**
 * Gets the value of member variable day5Hrs
 */
  public double getDay5Hrs() {
    return this.day5Hrs;
  }
/**
 * Sets the value of member variable day6Hrs
 */
  public void setDay6Hrs(double value) {
    this.day6Hrs = value;
  }
/**
 * Gets the value of member variable day6Hrs
 */
  public double getDay6Hrs() {
    return this.day6Hrs;
  }
/**
 * Sets the value of member variable day7Hrs
 */
  public void setDay7Hrs(double value) {
    this.day7Hrs = value;
  }
/**
 * Gets the value of member variable day7Hrs
 */
  public double getDay7Hrs() {
    return this.day7Hrs;
  }
/**
 * Sets the value of member variable totalHrs
 */
  public void setTotalHrs(double value) {
    this.totalHrs = value;
  }
/**
 * Gets the value of member variable totalHrs
 */
  public double getTotalHrs() {
    return this.totalHrs;
  }

@Override
public boolean equals(Object obj) {
   if (this == obj) {
      return true;
   }
   if (obj == null) {
      return false;
   }
   if (getClass() != obj.getClass()) {
      return false;
   }
   final VwTimesheetSummary other = (VwTimesheetSummary) obj; 
   if (EqualityAssistant.notEqual(this.timesheetId, other.timesheetId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.shortname, other.shortname)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.displayValue, other.displayValue)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.endPeriod, other.endPeriod)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.documentId, other.documentId)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day1Hrs, other.day1Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day2Hrs, other.day2Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day3Hrs, other.day3Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day4Hrs, other.day4Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day5Hrs, other.day5Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day6Hrs, other.day6Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.day7Hrs, other.day7Hrs)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.totalHrs, other.totalHrs)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.timesheetId),
   HashCodeAssistant.hashObject(this.shortname),
   HashCodeAssistant.hashObject(this.displayValue),
   HashCodeAssistant.hashObject(this.endPeriod),
   HashCodeAssistant.hashObject(this.documentId),
   HashCodeAssistant.hashObject(this.day1Hrs),
   HashCodeAssistant.hashObject(this.day2Hrs),
   HashCodeAssistant.hashObject(this.day3Hrs),
   HashCodeAssistant.hashObject(this.day4Hrs),
   HashCodeAssistant.hashObject(this.day5Hrs),
   HashCodeAssistant.hashObject(this.day6Hrs),
   HashCodeAssistant.hashObject(this.day7Hrs),
   HashCodeAssistant.hashObject(this.totalHrs));
} 

@Override
public String toString() {
   return "VwTimesheetSummary [timesheetId=" + timesheetId + 
          ", shortname=" + shortname + 
          ", displayValue=" + displayValue + 
          ", endPeriod=" + endPeriod + 
          ", documentId=" + documentId + 
          ", day1Hrs=" + day1Hrs + 
          ", day2Hrs=" + day2Hrs + 
          ", day3Hrs=" + day3Hrs + 
          ", day4Hrs=" + day4Hrs + 
          ", day5Hrs=" + day5Hrs + 
          ", day6Hrs=" + day6Hrs + 
          ", day7Hrs=" + day7Hrs + 
          ", totalHrs=" + totalHrs  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}