package org.rmt2.api.transaction.cashdisbursements;

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
import org.modules.transaction.TransactionAmountsUnbalancedException;
import org.modules.transaction.XactApiException;
import org.modules.transaction.XactConst;
import org.modules.transaction.disbursements.DisbursementsApi;
import org.modules.transaction.disbursements.DisbursementsApiException;
import org.modules.transaction.disbursements.DisbursementsApiFactory;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.rmt2.api.transaction.TransactionApiTestData;

import com.InvalidDataException;
import com.api.persistence.AbstractDaoClientImpl;
import com.api.persistence.db.orm.Rmt2OrmClientFactory;
import com.util.RMT2Date;

/**
 * Tests cash disbursement transaction update Api functionality.
 * 
 * @author rterrell
 * 
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ AbstractDaoClientImpl.class, Rmt2OrmClientFactory.class,
        ResultSet.class })
public class CashDisbursementsUpdateApiTest extends TransactionApiTestData {

    private XactDto mockXactDto;
    private List<XactTypeItemActivityDto> mockXactItemsDto;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        super.setUp();

        VwXactList vwXact = this.mockXactFetchSingleResponse.get(0);
        vwXact.setId(0);
        vwXact.setXactTypeId(XactConst.XACT_TYPE_CASHDISBEXP);
        mockXactDto = Rmt2XactDtoFactory.createXactInstance(vwXact);

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
        mockXactOrm.setXactAmount(dto.getXactAmount() * -1);
        mockXactOrm.setXactDate(dto.getXactDate());
        mockXactOrm.setXactSubtypeId(dto.getXactSubtypeId());
        mockXactOrm.setXactTypeId(dto.getXactTypeId());
        return mockXactOrm;
    }

    @Test
    public void testSuccess() {
        Xact xact = this.buildXactOrm(this.mockXactDto);
        try {
            when(this.mockPersistenceClient.insertRow(
                    any(XactTypeItemActivity.class), any(Boolean.class)))
                            .thenReturn(500, 501, 502, 503, 504);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up cash disbursement transaction item activity update case failed");
        }
        try {
            when(this.mockPersistenceClient.insertRow(eq(xact), eq(true)))
                    .thenReturn(1234567);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up cash disbursement base transaction update case failed");
        }
        
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        int results = 0;
        try {
            results = api.updateTrans(this.mockXactDto, this.mockXactItemsDto);
        } catch (DisbursementsApiException e) {
            Assert.fail("An unexpected exception occurred");
            e.printStackTrace();
        }
        Assert.assertEquals(1234567, results);
    }

    @Test
    public void testValidation_NullXactItemList() {
        Xact xact = this.buildXactOrm(this.mockXactDto);
        try {
            when(this.mockPersistenceClient.insertRow(eq(xact), eq(true)))
                    .thenReturn(1234567);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Setting up general base transaction update case failed");
        }

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, null);
            Assert.fail("Expected exception to be thrown due to null xact item list");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
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

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, this.mockXactItemsDto);
            Assert.fail("Expected exception to be thrown for Xact update");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof XactApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof XactDaoException);
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

        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, this.mockXactItemsDto);
            Assert.fail("Expected exception to be thrown for Xact detail item update");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof XactApiException);
            Assert.assertTrue(e.getCause().getCause() instanceof XactDaoException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_NullXact() {
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(null, this.mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to null Xact");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NullXactItem() {
        mockXactItemsDto.add(null);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to null Item contained in Xact List object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NegativeXactId_InBase() {
        this.mockXactDto.setXactId(-123);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to negative xactId in base Xact object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NegativeXactTypeId_InBase() {
        this.mockXactDto.setXactTypeId(-123);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to negative transaction type id in base Xact object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NoDecimalInXactAmount_InBase() {
        this.mockXactDto.setXactAmount(123);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transaction amount does not have decimal place in base xact");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_OneDecimalDigitInXactAmount_InBase() {
        this.mockXactDto.setXactAmount(123.1);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transaction amount has only one digit behind decimal in base xact");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NegativeXactItemId_InXactItem() {
        this.mockXactItemsDto.get(0).setXactItemId(-123);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to negative xact item id in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_ZeroXactItemId_InXactItem() {
        this.mockXactItemsDto.get(0).setXactItemId(0);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to zero xact item id in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_NullXactTypeItemActvName_InXactItem() {
        this.mockXactItemsDto.get(0).setXactTypeItemActvName(null);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to null xact type item activity name in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_EmptyXactTypeItemActvName_InXactItem() {
        this.mockXactItemsDto.get(0).setXactTypeItemActvName("");
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to empty xact type item activity name in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
    
    @Test
    public void testValidation_TransactonOutOfBalance() {
        this.mockXactDto.setXactAmount(1000.00);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to empty xact type item activity name in transaction detail item object");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof TransactionAmountsUnbalancedException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_TransactonDateNull() {
        this.mockXactDto.setXactDate(null);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transaction date is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_TransactonDatePastCurrentDate() {
        this.mockXactDto.setXactDate(RMT2Date.stringToDate("2050-12-31"));
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transaction date is is past current date");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_TenderIdIsNegative() {
        this.mockXactDto.setXactTenderId(-234);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to tender id is negative");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_TenderIdIsZero() {
        this.mockXactDto.setXactTenderId(0);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to tender id is zero");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_NegotiableInstrumentNumberNull() {
        this.mockXactDto.setXactTenderId(XactConst.TENDER_CREDITCARD);
        this.mockXactDto.setXactNegInstrNo(null);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transacction negotialble instrument number is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }

    @Test
    public void testValidation_ReasonIsNull() {
        this.mockXactDto.setXactReason(null);
        DisbursementsApiFactory f = new DisbursementsApiFactory();
        DisbursementsApi api = f.createApi(mockDaoClient);
        try {
            api.updateTrans(this.mockXactDto, mockXactItemsDto);
            Assert.fail("Expected exception to be thrown due to transacction reason is null");
        } catch (Exception e) {
            Assert.assertTrue(e instanceof DisbursementsApiException);
            Assert.assertTrue(e.getCause() instanceof InvalidDataException);
            e.printStackTrace();
        }
    }
}