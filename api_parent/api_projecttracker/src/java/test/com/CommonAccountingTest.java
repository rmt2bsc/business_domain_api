package com;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testcases.TestSystemConfiguratorDelegate;

import com.api.config.AppPropertyPool;
import com.api.config.ConfigConstants;
import com.api.config.ConfigException;
import com.api.config.old.AbstractSystemConfigImpl;
import com.api.config.old.ConfigAttributes;
import com.api.config.old.CoreSysConfigFactory;
import com.api.config.old.ResourceConfigurator;
import com.util.RMT2File;

/**
 * Common test class for all accounting test cases
 * 
 * @author Roy Terrell
 * 
 */
public class CommonAccountingTest {
    private static final String TEST_SOAP_HOST = "http://localhost:8080/ServiceDispatch/soapEngine";

    protected String prevSoaphost;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        System.setProperty("env", "TEST");
        TestSystemConfiguratorDelegate cfg = new TestSystemConfiguratorDelegate(
                "config.TestAccountingConfigParms");
        cfg.doConfig();
        this.prevSoaphost = System.setProperty(ConfigConstants.SOAP_HOST,
                CommonAccountingTest.TEST_SOAP_HOST);
        return;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        if (this.prevSoaphost != null) {
            this.prevSoaphost = System.setProperty(ConfigConstants.SOAP_HOST,
                    this.prevSoaphost);
        }
    }

    protected void createData() {
        return;
    }

    protected void deleteData() {
        return;
    }

    @Test
    public void test() {
        List<Integer> myList = new ArrayList<Integer>();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        Integer[] intArray = myList.toArray(new Integer[myList.size()]);
        int len = intArray.length;

        List<Integer> list2 = Arrays.asList(intArray);
        list2.size();
        return;
    }

    @Test
    public void getTextFileContentsFromClassPath() {
        String emailTemplateFile = RMT2File
                .getAppParmProperty("email_template");
        InputStream is = RMT2File.getFileInputStream(emailTemplateFile);
        String content = RMT2File.getStreamStringData(is);
        System.out.println(content);
    }

    @Test
    public void loadAppParmsConfigFile() {
        AppPropertyPool pool = null;
        ResourceConfigurator cfg = CoreSysConfigFactory
                .getPropertiesSystemConfigurator("config.AppParms");
        ((AbstractSystemConfigImpl) cfg).setAppName("TestAPp");
        if (!AppPropertyPool.isAppConfigured()) {
            try {
                cfg.doSetup();
                cfg.doPostSetup(null);
            } catch (ConfigException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ConfigAttributes appConfig = (ConfigAttributes) cfg;
            pool = AppPropertyPool.getInstance();
            pool.addProperties(appConfig);
        }

        String value = pool.getProperty("invoice_item_id");
        return;
    }
}
