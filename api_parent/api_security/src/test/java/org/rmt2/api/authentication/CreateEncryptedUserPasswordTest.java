package org.rmt2.api.authentication;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.authentication.CryptoUtils;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.SecurityMockData;

import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests various user authentication functioanality that occurs post login in the Security API.
 * <p>
 * Test single sign on, authentication checks, and logging out of system. 
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class })
public class CreateEncryptedUserPasswordTest extends SecurityMockData {

    private static final String TEST_UID = "testuser";
    private static final String TEST_PASSWORD = "drum7777";

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

    
    private String createPassword(String userName, String password) throws Exception {
        String calcPassword = null;
        if (userName != null && password != null) {
            calcPassword = userName + password;
        }
        else {
            return null;
        }
        String hashPassPw = null;
        try {
            byte[] pwHash = CryptoUtils.computeHash(calcPassword);
            hashPassPw = CryptoUtils.byteArrayToHexString(pwHash);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("Unable to encrypt password while maintaining user profile", e);
        }
        return hashPassPw;
    }
    
    
    @Test
    public void testCreatePassword() throws Exception {
        String encryptPw = this.createPassword(TEST_UID, TEST_PASSWORD);
        System.out.print(encryptPw);
    }
}