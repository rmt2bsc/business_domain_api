package org.dto;

/**
 * Data Transfer Object (DTO) contract for managing information pertaining to
 * common computer resources.
 * 
 * @author Roy Terrell
 * 
 */
public interface ComputerDto extends ResourceDto {

    /**
     * Return the environment computer resides.
     * 
     * @return String
     */
    String getEnv();

    /**
     * Set the environment computer resides.
     * 
     * @param env
     *            a String such as test, prod, or dev
     */
    void setEnv(String env);

    /**
     * Return the host name
     * 
     * @return String
     */
    String getHostName();

    /**
     * Set the host name
     * 
     * @param hostName
     *            String
     */
    void setHostName(String hostName);

    /**
     * Return the port number
     * 
     * @return String
     */
    String getPort();

    /**
     * Set the port number
     * 
     * @param port
     *            String
     */
    void setPort(String port);

    /**
     * Return flag indicating whether or not the computer possesses a static IP
     * address
     * 
     * @return true when IP address is static or false when IP address is
     *         dynamic.
     */
    boolean isStaticIp();

    /**
     * Set flag indicating whether or not the computer possesses a static IP
     * address
     * 
     * @param flag
     */
    void setStaticIp(boolean flag);

    /**
     * Return protocol information
     * 
     * @return String
     */
    String getProtocolInfo();

    /**
     * Set protocol information
     * 
     * @param info
     *            String
     */
    void setProtocolInfo(String info);

    /**
     * Return host number
     * 
     * @return String
     */
    String getIpHostNumber();

    /**
     * Set the host number
     * 
     * @param hostNo
     *            String
     */
    void setIpHostNumber(String hostNo);

    /**
     * Return the computer's MAC address.
     * 
     * @return String
     */
    String getMacAddress();

    /**
     * Set the computer's MAC address.
     * 
     * @param addr
     *            String
     */
    void setMacAddress(String addr);

    /**
     * Return the category of the computer's resource sub type.
     * 
     * @return String
     */
    String getCatg();

    /**
     * Set the category of the computer's resource sub type.
     * 
     * @param catg
     *            String
     */
    void setCatg(String catg);

    /**
     * Return the computer's category box description
     * 
     * @return String
     */
    String getCatgBoxDescr();

    /**
     * Set the computer's category box description
     * 
     * @param descr
     *            String
     */
    void setCatgBoxDescr(String descr);
}
