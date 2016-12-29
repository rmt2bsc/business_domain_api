/**
 * 
 */
package config;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.modules.AddressBookConfigurator;

/**
 * @author appdev
 *
 */
public class TestConfigurator {

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() {
        AddressBookConfigurator c = new AddressBookConfigurator();
        c.start();
    }

}
