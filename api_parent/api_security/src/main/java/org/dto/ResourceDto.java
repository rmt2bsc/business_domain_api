package org.dto;

import com.api.foundation.TransactionDto;

/**
 * Data Transfer Object (DTO) contract that consolidates information pertaining
 * to the resource, resource type, and the resource sub type entities.
 * <p>
 * An instance of this class represents one related record. If the desire arises
 * to manage a List of these instances, then redundant data could exist.
 * 
 * 
 */
public interface ResourceDto extends TransactionDto {
    /**
     * Sets the unique identifier representing the resource.
     * 
     * 
     */
    public void setUid(int value);

    /**
     * Gets the unique identifier representing the resource.
     * 
     * 
     */
    public int getUid();

    /**
     * Sets the name of the resource
     * 
     */
    public void setName(String value);

    /**
     * Gets the name of the resource
     */
    public String getName();

    // /**
    // * Sets the URL of the resource
    // */
    // public void setRequestUrl(String value);
    //
    // /**
    // * Gets the URL of the resource
    // *
    // *
    // */
    // public String getRequestUrl();

    /**
     * Sets the description of the resource
     * 
     * @param value
     *            the description
     */
    public void setDescription(String value);

    /**
     * Gets the description of the resource
     * 
     * @return String
     */
    public String getDescription();

    // /**
    // * Sets the secured flag
    // *
    // * @param value
    // * the secured flag
    // */
    // public void setSecured(int value);
    //
    // /**
    // * Gets the secured flag
    // *
    // * @return secured flag
    // */
    // public int getSecured();
    //
    // /**
    // * Get the host name where resource can be located
    // *
    // * @return
    // * the host name
    // */
    // String getHost();
    //
    // /**
    // * Set the host name where resource can be located
    // *
    // * @param hostName
    // * the host name
    // */
    // void setHost(String hostName);
    //
    // /**
    // * Get the router type.
    // *
    // * @return
    // * router type
    // */
    // String getRouterType();
    //
    // /**
    // * Set the router type.
    // *
    // * @param routerType
    // * the router type
    // */
    // void setRouterType(String routerType);

    /**
     * Sets the id of the resource type
     * 
     * @param value
     *            the type id
     */
    public void setTypeId(int value);

    /**
     * Gets the id of the resource type
     * 
     * @return int
     */
    public int getTypeId();

    /**
     * Set the description of the resource type
     * 
     * @param value
     *            the description
     */
    public void setTypeDescription(String value);

    /**
     * Get the description of the resource type
     * 
     * @return String
     */
    public String getTypeDescription();

    /**
     * Sets the id of the resource sub type
     * 
     * @param value
     *            the id
     */
    public void setSubTypeId(int value);

    /**
     * Gets the id of the resource sub type
     * 
     * @return int
     */
    public int getSubTypeId();

    /**
     * Sets the resource sub type's Description
     * 
     * @param value
     *            the description
     */
    public void setSubTypeDescription(String value);

    /**
     * Gets the resource sub type's Description
     * 
     * @return String
     */
    public String getSubTypeDescription();

    /**
     * Sets the resource sub type's name
     * 
     * @param value
     *            the name
     */
    public void setSubTypeName(String value);

    /**
     * Gets the resource sub type's name
     * 
     * @return name of sub type
     */
    public String getSubTypeName();

    // /**
    // * @return the querySecureFlag
    // */
    // public boolean isQuerySecureFlag();
    //
    // /**
    // * @param querySecureFlag the querySecureFlag to set
    // */
    // public void setQuerySecureFlag(boolean querySecureFlag);
    //
    //
    // /**
    // * Set the message id for the reply
    // *
    // * @param value
    // * String
    // */
    // void setReplyMsgId(String value);
    //
    // /**
    // * Get the message id for the reply
    // *
    // * @return String
    // */
    // String getReplyMsgId();
}