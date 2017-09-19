package com.ronglexie.license.exception;

/**
 * 证书安装异常
 * @author wxt.linx
 * @version 2017-7-12.
 */
public class InstallLicenseException extends InvaildLicenseException {

    public InstallLicenseException(){
        super("证书安装失败",null);
        setStackTrace(null);
    }

    public InstallLicenseException(Throwable e){
        super(e.getLocalizedMessage(),e);
        //setStackTrace(null);
    }
}
