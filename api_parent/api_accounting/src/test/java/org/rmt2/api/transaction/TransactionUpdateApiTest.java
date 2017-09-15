package org.rmt2.api.transaction;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.dao.mapping.orm.rmt2.VwXactList;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.mapping.orm.rmt2.XactTypeItemActivity;
import org.dao.transaction.XactDaoException;
import org.dto.XactDto;
import org.dto.XactTypeItemActivityDto;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modules.transaction.XactApi;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;

/**
 * Tests general transaction update Api functionality.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class TransactionUpdateApiTest extends TransactionApiTestData {

    private XactDto mockXactDto;
    private List<XactTypeItemActivityDto> mockXactItemsDto;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0);
        mockXactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);
        mockXactDto.setXactId(0);

        mockXactItemsDto = new ArrayList<>();
        List<XactTypeItemActivity> items = this.mockXactTypeItemActivityFetchAllResponse;
        for (XactTypeItemActivity ormItem : items) {
            XactTypeItemActivityDto item = Rmt2XactDtoFactory
                    .createXactTypeItemActivityInstance(ormItem);
            item.setXactId(0);
            item.setXactTypeItemActvId(0);
            mockXactItemsDto.add(item);
        }
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
        super.tearDown();
        return;
    }

    private Xact buildXactOrm(XactDto dto) {
        Xact mockXactOrm = new Xact();
        mockXactOrm.setXactId(dto.getXactId());
        mockXactOrm.setBankTransInd(dto.getXactBankTransInd());
        mockXactOrm.setConfirmNo(dto.getXactConfirmNo());
        mockXactOrm.setDocumentId(dto.getDocumentId());
        mockXactOrm.setEntityRefNo(dto.getXactEntityRefNo());
        mockXactOrm.setNegInstrNo(dto.getXactNegInstrNo());
        mockXactOrm.setPostedDate(dto.getXactPostedDate());
        mockXactOrm.setReason(dto.getXactReason());
        mockXactOrm.setTenderId(dto.getXactTenderId());
        mockXactOrm.setXactAmount(dto.getXactAmount());
        mockXactOrm.setXactDate(dto.getXactDate());
        mockXactOrm.setXactSubtypeId(dto.getXactSubtypeId());
        mockXactOrm.setXactTypeId(dto.getXactTypeId());
        return mockXactOrm;
    }

    @Test
    public void testSuccess() {
        try {
            when(this.mockPersistenceClient.insertRow(
                    any(XactTypeItemActivity.class), any(Boolean.class)))
                            .thenReturn(500, 501, 502, 503, 504);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction item activity update case failed");
        }

        Xact xact = this.buildXactOrm(this.mockXactDto);
        try {
            when(this.mockPersistenceClient.insertRow(eq(xact), eq(true)))
                    .thenReturn(1234567);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction update case failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        int results = 0;
        try {
            results = api.update(this.mockXactDto, this.mockXactItemsDto);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1234567, results);
    }

    @Test
    public void testSuccessWithNullXactItemList() {
        Xact xact = this.buildXactOrm(this.mockXactDto);
        try {
            when(this.mockPersistenceClient.insertRow(eq(xact), eq(true)))
                    .thenReturn(1234567);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction update case failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        int results = 0;
        try {
            results = api.update(this.mockXactDto, null);
        } catch (XactApiException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(1234567, results);
    }
    
    @Test
    public void testFetchWithExceptionDuringXactUpdate() {
        try {
            when(this.mockPersistenceClient.insertRow(any(XactTypeItemActivity.class), 
                    any(Boolean.class))).thenReturn(500, 501, 502, 503, 504);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction item activity update case failed");
        }

        Xact xact = this.buildXactOrm(this.mockXactDto);
        try {
            when(this.mockPersistenceClient.insertRow(eq(xact), eq(true)))
                    .thenThrow(Exception.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction update case failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, this.mockXactItemsDto);
            Assert.fail("Expected exception to be thrown for Xact update");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof XactDaoException);
            e.printStackTrace();
        }
    }

    @Test
    public void testFetchWithExceptionDuringXactItemUpdate() {
        try {
            when(this.mockPersistenceClient.insertRow(any(XactTypeItemActivity.class), eq(true)))
                  .thenThrow(Exception.class);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction item activity update case failed");
        }
        
        Xact xact = this.buildXactOrm(this.mockXactDto);
        try {
            when(this.mockPersistenceClient.insertRow(eq(xact), eq(true))).thenReturn(1234567);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction update case failed");
        }

        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, this.mockXactItemsDto);
            Assert.fail("Expected exception to be thrown for Xact detail item update");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof XactDaoException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_NullXact() {
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(null, this.mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to null Xact");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NullXactItem() {
        mockXactItemsDto.add(null);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to null Item contained in Xact List object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NegativeXactId_InBase() {
        this.mockXactDto.setXactId(-123);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to negative xactId in base Xact object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NegativeXactTypeId_InBase() {
        this.mockXactDto.setXactTypeId(-123);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to negative transaction type id in base Xact object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NoDecimalInXactAmount_InBase() {
        this.mockXactDto.setXactAmount(123);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transaction amount does not have decimal place in base xact");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_OneDecimalDigitInXactAmount_InBase() {
        this.mockXactDto.setXactAmount(123.1);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transaction amount has only one digit behind decimal in base xact");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NegativeXactItemId_InXactItem() {
        this.mockXactItemsDto.get(0).setXactItemId(-123);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to negative xact item id in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_ZeroXactItemId_InXactItem() {
        this.mockXactItemsDto.get(0).setXactItemId(0);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to zero xact item id in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NullXactTypeItemActvName_InXactItem() {
        this.mockXactItemsDto.get(0).setXactTypeItemActvName(null);
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to null xact type item activity name in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_EmptyXactTypeItemActvName_InXactItem() {
        this.mockXactItemsDto.get(0).setXactTypeItemActvName("");
        XactApiFactory f = new XactApiFactory();
        XactApi api = f.createDefaultXactApi(mockDaoClient);
        try {
            api.update(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to empty xact type item activity name in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof XactApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
}