package com.ronglexie.license.exception;

/**
 * 证书异常
 * @author wxt.linx
 * @version 2017-12-12.
 */
public class InvaildLicenseException extends RuntimeException {

    public InvaildLicenseException(){
        super("",null);
    }

    public InvaildLicenseException(String msg, Throwable e){
        super(msg,e);
    }
    public InvaildLicenseException(String msg){
        super(msg,null);
    }
}
