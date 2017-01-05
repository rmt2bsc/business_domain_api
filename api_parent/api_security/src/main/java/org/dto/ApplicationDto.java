package org.dto;

import com.api.foundation.TransactionDto;

/**
 * A Data Transfer Object (DTO) contract that consolidates information
 * pertaining to the Application entity.
 * 
 */
public interface ApplicationDto extends TransactionDto {

    /**
     * Sets the value of member variable applicationId
     */
    void setApplicationId(int value);

    /**
     * Gets the value of member variable applicationId
     */
    int getApplicationId();

    /**
     * Sets the value of member variable appName
     */
    void setAppName(String value);

    /**
     * Gets the value of member variable appName
     */
    String getAppName();

    /**
     * Sets the description of the application
     */
    void setAppDescription(String value);

    /**
     * Gets description of the application
     */
    String getAppDescription();

    /**
     * Set the applicaton code
     */
    void setAppCode(String value);

    /**
     * Get the applicaton code
     */
    String getAppCode();

    /**
     * Set the applicaton title
     */
    void setAppTitle(String value);

    /**
     * Get the applicaton title
     */
    String getAppTitle();

    /**
     * Set the applicaton web context
     */
    void setWebContext(String value);

    /**
     * Get the applicaton web context
     */
    String getWebContext();

    /**
     * Set the applicaton directory
     */
    void setAppDir(String value);

    /**
     * Get the applicaton directory
     */
    String getAppDir();

    /**
     * Set the applicaton's logger package
     */
    void setLogConfig(String value);

    /**
     * Get the applicaton's logger package
     */
    String getLogConfig();

    /**
     * Set the applicaton's navigation rules package
     */
    void setNavigationRules(String value);

    /**
     * Get the applicaton's navigation rules package
     */
    String getNavigationRules();

    /**
     * Set the applicaton's output directory
     */
    void setOutputDir(String value);

    /**
     * Get the applicaton's output directory
     */
    String getOutputDir();

    /**
     * Set the applicaton's security authenticator
     */
    void setAuthenticator(String value);

    /**
     * Get the applicaton's security authenticator
     */
    String getAuthenticator();

    /**
     * Set the applicaton's database configuration name
     */
    void setDbConfigName(String value);

    /**
     * Get the applicaton's database configuration name
     */
    String getDbConfigName();

    /**
     * Set the applicaton's database connection factory class name
     */
    void setDbConnectionFactory(String value);

    /**
     * Get the applicaton's database connection factory class name
     */
    String getDbConnectionFactory();

    /**
     * Set the applicaton's database owner
     */
    void setDbOwner(String value);

    /**
     * Get the applicaton's database owner
     */
    String getDbOwner();

    /**
     * Set the applicaton's ORM bean package
     */
    void setOrmBeanPackage(String value);

    /**
     * Get the applicaton's ORM bean package
     */
    String getOrmBeanPackage();

    /**
     * Set the applicaton's ORM directory for generated output
     */
    void setOrmGeneratedOutputDir(String value);

    /**
     * Get the applicaton's ORM directory for generated output
     */
    String getOrmGeneratedOutputDir();

    /**
     * Set the applicaton's ORM XML directory
     */
    void setOrmXmlDir(String value);

    /**
     * Get the applicaton's ORM XML directory
     */
    String getOrmXmlDir();

    /**
     * Set the applicaton's active flag
     */
    void setActive(String value);

    /**
     * Get the applicaton's active flag
     */
    String getActive();

}