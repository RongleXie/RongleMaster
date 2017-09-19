package com.ronglexie.license.exception;

/**
 * @author wxt.linx
 * @version 2017-7-13.
 */
public class VerifyLicenseException extends InvaildLicenseException {

    public VerifyLicenseException() {
        super("证书验证失败", null);
        setStackTrace(null);
    }

    public VerifyLicenseException(Throwable e) {
        super(e.getLocalizedMessage(), e);
        //setStackTrace(null);
    }
}
