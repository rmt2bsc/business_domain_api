package com;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import testcases.StandAloneApiTest;

/**
 * Common test class for all accounting test cases
 * 
 * @author Roy Terrell
 * 
 */
public class CommonAccountingTest extends StandAloneApiTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        return;
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    protected void createData() {
        super.createData();
        return;
    }

    protected void deleteData() {
        super.deleteData();
        return;
    }

    @Test
    public void test() {
        fail("Not yet implemented");
    }
}
