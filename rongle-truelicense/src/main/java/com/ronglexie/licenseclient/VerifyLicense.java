package com.ronglexie.licenseclient;


import com.ronglexie.license.exception.InstallLicenseException;
import com.ronglexie.license.exception.VerifyLicenseException;
import de.schlichtherle.license.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.prefs.Preferences;


/**
 * VerifyLicense
 *
 * @author ronglexie
 */
public class VerifyLicense {
    //common param
    private static String PUBLICALIAS = "";
    private static String STOREPWD = "";
    private static String SUBJECT = "";
    private static String licPath = "";
    private static String pubPath = "";

    public void setParam(String propertiesPath) {
        // 获取参数
        Properties prop = new Properties();
        InputStream in = getClass().getClassLoader().getResourceAsStream(propertiesPath);
        try {
            prop.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        PUBLICALIAS = prop.getProperty("PUBLICALIAS");
        STOREPWD = prop.getProperty("STOREPWD");
        SUBJECT = prop.getProperty("SUBJECT");
        licPath = prop.getProperty("licPath");
        pubPath = prop.getProperty("pubPath");
    }

    public boolean verify() {
        /************** 证书使用者端执行 ******************/

        LicenseManager licenseManager = new LicenseManager(initLicenseParams());
        // 安装证书
        try {
            ClassLoader clsLdr = Thread.currentThread().getContextClassLoader();
            String path = clsLdr.getResource("").getPath();
            path = URLDecoder.decode(path,"utf-8");
            LicenseContent content = licenseManager.install(new File(path + licPath));
        } catch (Exception e) {
            throw new InstallLicenseException(e);
        }
        // 验证证书
        try {
            licenseManager.verify();
        } catch (Exception e) {
            throw new VerifyLicenseException(e);
        }
        return true;
    }

    // 返回验证证书需要的参数
    private static LicenseParam initLicenseParams() {
        Preferences preference = Preferences
                .userNodeForPackage(VerifyLicense.class);
        CipherParam cipherParam = new DefaultCipherParam(STOREPWD);

        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(
                VerifyLicense.class, pubPath, PUBLICALIAS, STOREPWD, null);
        LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT,
                preference, privateStoreParam, cipherParam);
        return licenseParams;
    }
}
