package org.modules.services.directory;

import java.util.HashMap;
import java.util.Map;

import com.RMT2Base;
import com.SystemException;

/**
 * A bean managing the directory listener's application module configurations.
 * 
 * @author appdev
 * 
 */
public class DirectoryListenerConfigBean extends RMT2Base {

    private static final long serialVersionUID = 518549554653952863L;

    private boolean emailResults;
    
    private String emailSender;

    private String emailRecipients;

    private int moduleCount;

    private Map<Integer, ApplicationModuleBean> modules;

    private String inboundDir;

    private String outboundDir;

    private String archiveDir;

    private String fetchDir;

    private int archiveAge;

    private int pollFreq;

    private boolean archiveLocal;

    /**
     * @throws SystemException
     */
    DirectoryListenerConfigBean() throws SystemException {
        super();
        this.modules = new HashMap<Integer, ApplicationModuleBean>();
    }

    /**
     * @return the reportEmail
     */
    public String getEmailRecipients() {
        return emailRecipients;
    }

    /**
     * @param reportEmail
     *            the reportEmail to set
     */
    public void setEmailRecipients(String reportEmail) {
        this.emailRecipients = reportEmail;
    }

    /**
     * @return the moduleCount
     */
    public int getModuleCount() {
        return moduleCount;
    }

    /**
     * @param moduleCount
     *            the moduleCount to set
     */
    public void setModuleCount(int moduleCount) {
        this.moduleCount = moduleCount;
    }

    /**
     * @return the inboundDir
     */
    public String getInboundDir() {
        return inboundDir;
    }

    /**
     * @param inboundDir
     *            the inboundDir to set
     */
    public void setInboundDir(String inboundDir) {
        this.inboundDir = inboundDir;
    }

    /**
     * @return the archiveDir
     */
    public String getArchiveDir() {
        return archiveDir;
    }

    /**
     * @param archiveDir
     *            the archiveDir to set
     */
    public void setArchiveDir(String archiveDir) {
        this.archiveDir = archiveDir;
    }

    /**
     * @return the archiveAge
     */
    public int getArchiveAge() {
        return archiveAge;
    }

    /**
     * @param archiveAge
     *            the archiveAge to set
     */
    public void setArchiveAge(int archiveAge) {
        this.archiveAge = archiveAge;
    }

    /**
     * @return the modules
     */
    public Map<Integer, ApplicationModuleBean> getModules() {
        return modules;
    }

    /**
     * @return the pollFreq
     */
    public int getPollFreq() {
        return pollFreq;
    }

    /**
     * @param pollFreq
     *            the pollFreq to set
     */
    public void setPollFreq(int pollFreq) {
        this.pollFreq = pollFreq;
    }

    /**
     * @return the outboundDir
     */
    public String getOutboundDir() {
        return outboundDir;
    }

    /**
     * @param outboundDir
     *            the outboundDir to set
     */
    public void setOutboundDir(String outboundDir) {
        this.outboundDir = outboundDir;
    }

    /**
     * @return the emailResults
     */
    public boolean isEmailResults() {
        return emailResults;
    }

    /**
     * @param emailResults
     *            the emailResults to set
     */
    public void setEmailResults(boolean emailResults) {
        this.emailResults = emailResults;
    }

    /**
     * @return the archiveLocal
     */
    public boolean isArchiveLocal() {
        return archiveLocal;
    }

    /**
     * @param archiveLocal
     *            the archiveLocal to set
     */
    public void setArchiveLocal(boolean archiveLocal) {
        this.archiveLocal = archiveLocal;
    }

    /**
     * @return the fetchDir
     */
    public String getFetchDir() {
        return fetchDir;
    }

    /**
     * @param fetchDir
     *            the fetchDir to set
     */
    public void setFetchDir(String fetchDir) {
        this.fetchDir = fetchDir;
    }

    public String getEmailSender() {
        return emailSender;
    }

    public void setEmailSender(String emailSender) {
        this.emailSender = emailSender;
    }
}
