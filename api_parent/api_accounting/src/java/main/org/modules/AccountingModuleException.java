package org.modules;

import com.RMT2Exception;

/**
 * The base exception for all modules of the Accounting API.
 * 
 * @author Roy Terrell
 * 
 */
public class AccountingModuleException extends RMT2Exception {
    private static final long serialVersionUID = 2969536074770899864L;

    public AccountingModuleException() {
        super();
    }

    public AccountingModuleException(String msg) {
        super(msg);
    }

    public AccountingModuleException(Exception e) {
        super(e);
    }

    public AccountingModuleException(String msg, Throwable e) {
        super(msg, e);
    }
}
