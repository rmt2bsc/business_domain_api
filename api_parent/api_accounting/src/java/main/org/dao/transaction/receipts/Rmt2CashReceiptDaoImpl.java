package org.dao.transaction.receipts;

import java.util.List;

import org.dao.mapping.orm.rmt2.SalesOrder;
import org.dao.mapping.orm.rmt2.Xact;
import org.dao.subsidiary.CustomerDao;
import org.dao.subsidiary.SubsidiaryDaoException;
import org.dao.subsidiary.SubsidiaryDaoFactory;
import org.dao.transaction.Rmt2XactDaoImpl;
import org.dao.transaction.XactDaoException;
import org.dao.transaction.XactDaoFactory;
import org.dao.transaction.sales.SalesOrderDao;
import org.dao.transaction.sales.SalesOrderDaoException;
import org.dao.transaction.sales.SalesOrderDaoFactory;
import org.dto.CustomerDto;
import org.dto.SalesOrderDto;
import org.dto.XactDto;
import org.dto.adapter.orm.account.subsidiary.CustomerExt;
import org.dto.adapter.orm.account.subsidiary.Rmt2SubsidiaryDtoFactory;
import org.dto.adapter.orm.transaction.Rmt2XactDtoFactory;
import org.dto.adapter.orm.transaction.sales.Rmt2SalesOrderDtoFactory;

import com.api.persistence.PersistenceClient;

/**
 * An implementation of {@link CashReceiptDao}. It provides functionality that
 * creates, updates, deletes, and queries cash receipts related data.
 * 
 * @author Roy Terrell
 * 
 */
public class Rmt2CashReceiptDaoImpl extends Rmt2XactDaoImpl implements
        CashReceiptDao {

    /**
     * Creates a Rmt2CashReceiptDaoImpl object with its own persistent client.
     */
    Rmt2CashReceiptDaoImpl() {
        super();
    }

    /**
     * Creates a Rmt2CashReceiptDaoImpl object with its own persistent client.
     * 
     * @param appName
     */
    Rmt2CashReceiptDaoImpl(String appName) {
        super(appName);
    }

    /**
     * Creates a Rmt2CashReceiptDaoImpl object with a shared persistent client.
     * 
     * @param client
     */
    Rmt2CashReceiptDaoImpl(PersistenceClient client) {
        super(client);
    }

    /**
     * Creates customer payment confirmation message.
     * 
     * @param salesOrderId
     * @param xactId
     * @return
     * @throws CashReceiptDaoException
     */
    @Override
    public String buildPaymentConfirmation(int salesOrderId, int xactId)
            throws CashReceiptDaoException {
        Xact xact = null;
        XactDto criteria = Rmt2XactDtoFactory.createXactInstance((Xact) null);
        criteria.setXactId(xactId);
        try {
            List<XactDto> xactDto = this.fetchXact(criteria);
            if (xactDto != null && xactDto.size() == 1) {
                xact = XactDaoFactory.createXact(xactDto.get(0));
            }
            else {
                this.msg = "Transaction was not found: " + xactId;
                throw new CashReceiptDaoException(this.msg);
            }
        } catch (XactDaoException e) {
            throw new CashReceiptDaoException(e);
        }

        SalesOrder so = null;
        SalesOrderDaoFactory soDaoFact = new SalesOrderDaoFactory();
        SalesOrderDao soDao = soDaoFact.createRmt2OrmDao();
        SalesOrderDto soCriteria = Rmt2SalesOrderDtoFactory
                .createSalesOrderInstance(null);
        soCriteria.setSalesOrderId(salesOrderId);
        try {
            List<SalesOrderDto> soDto = soDao.fetchSalesOrder(soCriteria);
            if (soDto != null && soDto.size() == 1) {
                so = SalesOrderDaoFactory.createOrmSalesOrder(soDto.get(0));
            }
            else {
                this.msg = "Sales order was not found: " + salesOrderId;
                throw new CashReceiptDaoException(this.msg);
            }
        } catch (SalesOrderDaoException e) {
            throw new CashReceiptDaoException(e);
        }

        CustomerExt cust = null;
        SubsidiaryDaoFactory subDaoFact = new SubsidiaryDaoFactory();
        CustomerDao custDao = subDaoFact.createRmt2OrmCustomerDao();
        CustomerDto custCriteria = Rmt2SubsidiaryDtoFactory
                .createCustomerInstance(null, null);
        custCriteria.setCustomerId(so.getCustomerId());
        try {
            List<CustomerDto> custDto = custDao.fetch(custCriteria);
            if (custDto != null && custDto.size() == 1) {
                cust = SubsidiaryDaoFactory.createCustomerExtBean(custDto
                        .get(0));
                double bal = custDao.calculateBalance(cust.getCustomerId());
                cust.setBalance(bal);
            }
            else {
                this.msg = "Sales order was not found: " + salesOrderId;
                throw new CashReceiptDaoException(this.msg);
            }
        } catch (SubsidiaryDaoException e) {
            throw new CashReceiptDaoException(e);
        }

        StringBuffer xmlBuf = new StringBuffer();
        xmlBuf.append(cust.toXml());
        xmlBuf.append(so.toXml());
        xmlBuf.append(xact.toXml());
        return xmlBuf.toString();
    }

}
