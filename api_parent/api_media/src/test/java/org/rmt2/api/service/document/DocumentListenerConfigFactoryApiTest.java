package org.rmt2.api.service.document;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.util.reflection.Whitebox;
import org.modules.services.document.directory.ApplicationModuleBean;
import org.modules.services.document.directory.DirectoryListenerConfigBean;
import org.modules.services.document.directory.DirectoryListenerConfigFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.document.DocumentMediaMockData;

import com.SystemException;
import com.api.config.ConfigConstants;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.api.util.RMT2File;

/**
 * Test the Document Listener Configuration Factory functionality of the Media API service module.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class, RMT2File.class })
public class DocumentListenerConfigFactoryApiTest extends DocumentMediaMockData {
    
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

   
    @Test
    public void testSuccess_Load_From_Default_Prop_File() {
        // Ensure that System property is set to null
        DirectoryListenerConfigFactory f = new DirectoryListenerConfigFactory();
        Whitebox.setInternalState(f, "ENV", "TEST");
        
        DirectoryListenerConfigBean config = DirectoryListenerConfigFactory.getDocumentListenerConfigBeanInstance();
        
        Assert.assertNotNull(config);
        Assert.assertEquals("john.smith@gte.net", config.getEmailRecipients());
        Assert.assertTrue(config.isEmailResults());
        Assert.assertEquals("/AppServer/test/mime/in/", config.getInboundDir());
        Assert.assertEquals("/AppServer/test/mime/out/", config.getOutboundDir());
        Assert.assertEquals("/AppServer/test/mime/archive/", config.getArchiveDir());
        Assert.assertEquals(10000, config.getPollFreq());
        Assert.assertEquals(2, config.getModuleCount());
        
        ApplicationModuleBean a = config.getModules().get(0);
        Assert.assertEquals("cd1", a.getModuleCode());
        Assert.assertEquals("acct*.*", a.getFilePattern().trim());
        Assert.assertEquals("projectName1", a.getProjectName());
        Assert.assertEquals("moduleName1", a.getModuleName());
        Assert.assertEquals("entityUid1", a.getEntityUid());
        
        a = config.getModules().get(1);
        Assert.assertEquals("cd2", a.getModuleCode());
        Assert.assertEquals("proj*.*", a.getFilePattern().trim());
        Assert.assertEquals("projectName2", a.getProjectName());
        Assert.assertEquals("moduleName2", a.getModuleName());
        Assert.assertEquals("entityUid2", a.getEntityUid());

        // Ensure that the System prpoerty, ENV, is set back to "TEST"
        System.setProperty(ConfigConstants.PROPNAME_ENV, ConfigConstants.ENVTYPE_TEST);

    }
    
    @Test
    public void testSuccess_Load_From_Test_Prop_File() {
        // Ensure that System property is set to ConfigConstants.ENVTYPE_TEST
        DirectoryListenerConfigFactory f = new DirectoryListenerConfigFactory();
        Whitebox.setInternalState(f, "ENV", ConfigConstants.ENVTYPE_TEST);
        
        DirectoryListenerConfigBean config = DirectoryListenerConfigFactory.getDocumentListenerConfigBeanInstance();
        
        Assert.assertNotNull(config);
        Assert.assertEquals("john.smith@gte.net", config.getEmailRecipients());
        Assert.assertTrue(config.isEmailResults());
        Assert.assertEquals("/AppServer/test/mime/in/", config.getInboundDir());
        Assert.assertEquals("/AppServer/test/mime/out/", config.getOutboundDir());
        Assert.assertEquals("/AppServer/test/mime/archive/", config.getArchiveDir());
        Assert.assertEquals(10000, config.getPollFreq());
        Assert.assertEquals(2, config.getModuleCount());
        
        for (int ndx = 0; ndx < config.getModuleCount(); ndx++) {
            ApplicationModuleBean a = config.getModules().get(ndx);
            Assert.assertEquals("cd" + (ndx + 1), a.getModuleCode());
            Assert.assertTrue(a.getFilePattern().contains("*.*"));
            Assert.assertEquals("projectName" + (ndx + 1), a.getProjectName());
            Assert.assertEquals("moduleName" + (ndx + 1), a.getModuleName());
            Assert.assertEquals("entityUid" + (ndx + 1), a.getEntityUid());
        }
   }
    
    @Test
    public void testError_NonExisting_Prop_File() {
        try {
            DirectoryListenerConfigFactory.getDocumentListenerConfigBeanInstance("MimeConfig_NotExist");    
            Assert.fail("Expected an exception to be thrown");
        }
        catch (Exception e) {
            Assert.assertTrue(e instanceof SystemException);
        }
  }
}