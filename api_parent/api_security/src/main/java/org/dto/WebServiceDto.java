package org.dto;

/**
 * Data Transfer Object (DTO) contract that represents a web service resource.
 * 
 * @author Roy Terrell
 * 
 */
public interface WebServiceDto extends ResourceDto {

    /**
     * Sets the URL of the resource
     */
    void setRequestUrl(String value);

    /**
     * Gets the URL of the resource
     * 
     * 
     */
    String getRequestUrl();

    /**
     * Get the host name where resource can be located
     * 
     * @return the host name
     */
    String getHost();

    /**
     * Set the host name where resource can be located
     * 
     * @param hostName
     *            the host name
     */
    void setHost(String hostName);

    /**
     * Get the router type.
     * 
     * @return router type
     */
    String getRouterType();

    /**
     * Set the router type.
     * 
     * @param routerType
     *            the router type
     */
    void setRouterType(String routerType);

    /**
     * @return the querySecureFlag
     */
    boolean isQuerySecureFlag();

    /**
     * @param querySecureFlag
     *            the querySecureFlag to set
     */
    void setQuerySecureFlag(boolean querySecureFlag);

    /**
     * Set the message id for the reply
     * 
     * @param value
     *            String
     */
    void setReplyMsgId(String value);

    /**
     * Get the message id for the reply
     * 
     * @return String
     */
    String getReplyMsgId();

}
