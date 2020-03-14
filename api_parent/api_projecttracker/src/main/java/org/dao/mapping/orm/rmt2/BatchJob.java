package org.dao.mapping.orm.rmt2;


import java.util.Date;
import java.io.*;

import com.api.persistence.db.orm.OrmBean;
import com.api.util.assistants.EqualityAssistant;
import com.api.util.assistants.HashCodeAssistant;
import com.SystemException;


/**
 * Peer object that maps to the batch_job database table/view.
 *
 * @author auto generated.
 */
public class BatchJob extends OrmBean {




	// Property name constants that belong to respective DataSource, BatchJobView

/** The property name constant equivalent to property, Id, of respective DataSource view. */
  public static final String PROP_ID = "Id";
/** The property name constant equivalent to property, Description, of respective DataSource view. */
  public static final String PROP_DESCRIPTION = "Description";



	/** The javabean property equivalent of database column batch_job.id */
  private String id;
/** The javabean property equivalent of database column batch_job.description */
  private String description;



	// Getter/Setter Methods

/**
 * Default constructor.
 */
  public BatchJob() throws SystemException {
	super();
 }
/**
 * Sets the value of member variable id
 */
  public void setId(String value) {
    this.id = value;
  }
/**
 * Gets the value of member variable id
 */
  public String getId() {
    return this.id;
  }
/**
 * Sets the value of member variable description
 */
  public void setDescription(String value) {
    this.description = value;
  }
/**
 * Gets the value of member variable description
 */
  public String getDescription() {
    return this.description;
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
   final BatchJob other = (BatchJob) obj; 
   if (EqualityAssistant.notEqual(this.id, other.id)) {
      return false;
   }
   if (EqualityAssistant.notEqual(this.description, other.description)) {
      return false;
   }
   return true; 
} 

@Override
public int hashCode() {
   return HashCodeAssistant.combineHashCodes(HashCodeAssistant.hashObject(this.id),
               HashCodeAssistant.hashObject(this.description));
} 

@Override
public String toString() {
   return "BatchJob [id=" + id + 
          ", description=" + description  + "]";
}

/**
 * Stubbed initialization method designed to implemented by developer.

 */
  public void initBean() throws SystemException {}
}