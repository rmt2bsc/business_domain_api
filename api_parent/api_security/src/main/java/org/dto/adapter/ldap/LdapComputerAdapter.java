package org.dto.adapter.ldap;

import java.util.ArrayList;

import org.dao.mapping.orm.ldap.LdapCommonComputer;
import org.dto.ComputerDto;

import com.RMT2Constants;
import com.api.foundation.TransactionDtoImpl;

/**
 * Adapts a LDAP <i>LdapCommonComputer</i> object to an <i>ComputerDto</i>.
 * 
 * @author Roy Terrell
 * 
 */
class LdapComputerAdapter extends TransactionDtoImpl implements ComputerDto {

    private LdapCommonComputer c;

    /**
     * Constuct a LdapComputerAdapter that is initialized with a common computer
     * resource object.
     * 
     * @param comp
     *            an instance of {@link LdapCommonComputer}
     */
    protected LdapComputerAdapter(LdapCommonComputer comp) {
        if (comp == null) {
            comp = new LdapCommonComputer();
        }
        this.c = comp;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setName(java.lang.String)
     */
    @Override
    public void setName(String value) {
        if (this.c.getCn() == null || this.c.getCn().size() > 1) {
            this.c.setCn(new ArrayList<String>());
        }
        this.c.getCn().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getName()
     */
    @Override
    public String getName() {
        if (this.c.getCn() != null || this.c.getCn().size() > 0) {
            return this.c.getCn().get(0);
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setDescription(java.lang.String)
     */
    @Override
    public void setDescription(String value) {
        this.c.setDescription(new ArrayList<String>());
        this.c.getDescription().add(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getDescription()
     */
    @Override
    public String getDescription() {
        if (this.c.getDescription() == null) {
            return null;
        }
        return this.c.getDescription().get(0);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setTypeDescription(java.lang.String)
     */
    @Override
    public void setTypeDescription(String value) {
        this.c.setRsrcTypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getTypeDescription()
     */
    @Override
    public String getTypeDescription() {
        return this.c.getRsrcTypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setSubTypeName(java.lang.String)
     */
    @Override
    public void setSubTypeName(String value) {
        this.c.setRsrcSubTypeName(value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getSubTypeName()
     */
    @Override
    public String getSubTypeName() {
        return this.c.getRsrcSubTypeName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getEnv()
     */
    @Override
    public String getEnv() {
        return this.c.getEnv();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setEnv(java.lang.String)
     */
    @Override
    public void setEnv(String env) {
        this.c.setEnv(env);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getHostName()
     */
    @Override
    public String getHostName() {
        return this.c.getHostName();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setHostName(java.lang.String)
     */
    @Override
    public void setHostName(String hostName) {
        this.c.setHostName(hostName);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getPort()
     */
    @Override
    public String getPort() {
        return this.c.getIpServicePort();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setPort(java.lang.String)
     */
    @Override
    public void setPort(String port) {
        this.c.setIpServicePort(port);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#isStaticIp()
     */
    @Override
    public boolean isStaticIp() {
        return Boolean.parseBoolean(this.c.getIsStaticIp());
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setStaticIp(boolean)
     */
    @Override
    public void setStaticIp(boolean flag) {
        this.c.setIsStaticIp(Boolean.toString(flag));
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getProtocolInfo()
     */
    @Override
    public String getProtocolInfo() {
        return this.c.getProtocolInformation();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setProtocolInfo(java.lang.String)
     */
    @Override
    public void setProtocolInfo(String info) {
        this.c.setProtocolInformation(info);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getIpHostNumber()
     */
    @Override
    public String getIpHostNumber() {
        return this.c.getIpHostNumber();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setIpHostNumber(java.lang.String)
     */
    @Override
    public void setIpHostNumber(String hostNo) {
        this.c.setIpHostNumber(hostNo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getMacAddress()
     */
    @Override
    public String getMacAddress() {
        return this.c.getMacAddress();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setMacAddress(java.lang.String)
     */
    @Override
    public void setMacAddress(String addr) {
        this.c.setMacAddress(addr);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getCatg()
     */
    @Override
    public String getCatg() {
        return this.c.getComputerTypeCatg();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setCatg(java.lang.String)
     */
    @Override
    public void setCatg(String catg) {
        this.c.setComputerTypeCatg(catg);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#getCatgBoxDescr()
     */
    @Override
    public String getCatgBoxDescr() {
        return this.c.getComputerTypeCatgBoxDescr();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ComputerDto#setCatgBoxDescr(java.lang.String)
     */
    @Override
    public void setCatgBoxDescr(String descr) {
        this.c.setComputerTypeCatgBoxDescr(descr);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setUid(int)
     */
    @Override
    public void setUid(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getUid()
     */
    @Override
    public int getUid() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setTypeId(int)
     */
    @Override
    public void setTypeId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getTypeId()
     */
    @Override
    public int getTypeId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setSubTypeId(int)
     */
    @Override
    public void setSubTypeId(int value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getSubTypeId()
     */
    @Override
    public int getSubTypeId() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#setSubTypeDescription(java.lang.String)
     */
    @Override
    public void setSubTypeDescription(String value) {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.dto.ResourceDto#getSubTypeDescription()
     */
    @Override
    public String getSubTypeDescription() {
        throw new UnsupportedOperationException(
                RMT2Constants.MSG_METHOD_NOT_SUPPORTED);
    }

    @Override
    public void setSecured(Boolean value) {
    }

    @Override
    public Boolean isSecured() {
        return false;
    }

}
