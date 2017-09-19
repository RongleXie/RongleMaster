package com.ronglexie.licenseclient;


import de.schlichtherle.license.*;
import org.junit.Test;

import javax.security.auth.x500.X500Principal;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.prefs.Preferences;

/**
 * CreateLicense
 *
 * @author xieqingrong
 */
public class CreateLicense {
    //common param
    private static String PRIVATEALIAS = "";
    private static String KEYPWD = "";
    private static String STOREPWD = "";
    private static String SUBJECT = "";
    private static String licPath = "";
    private static String priPath = "";
    //license content
    private static String issuedTime = "";
    private static String notBefore = "";
    private static String notAfter = "";
    private static String consumerType = "";
    private static int consumerAmount = 0;
    private static String info = "";
    private static Map<String, String> exParam = new HashMap<String, String>();
    // 为了方便直接用的API里的例子
    // X500Princal是一个证书文件的固有格式，详见API
    private static X500Principal DEFAULTHOLDERANDISSUER;
    private static Properties prop;

    static {
        prop = new Properties();
        InputStream in = CreateLicense.class.getClassLoader().getResourceAsStream("crparam.properties");
        try {
            prop.load(in);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static String getPrincipalName() {
        Map<String, String> organizationInfo = new HashMap<String, String>();
        organizationInfo.put("CN", prop.getProperty("CN"));
        organizationInfo.put("L", prop.getProperty("L"));
        organizationInfo.put("ST", prop.getProperty("ST"));
        organizationInfo.put("O", prop.getProperty("O"));
        organizationInfo.put("OU", prop.getProperty("OU"));
        organizationInfo.put("C", prop.getProperty("C"));
        organizationInfo.put("STREET", prop.getProperty("STREET"));
        organizationInfo.put("DC", prop.getProperty("DC"));
        organizationInfo.put("UID", prop.getProperty("UID"));
        String orgInfo = organizationInfo.toString().replaceAll("[{}]", "").replaceAll(",", "、");
        return orgInfo;
    }

    public void setParam(String propertiesPath) {
        // 获取参数
        DEFAULTHOLDERANDISSUER = new X500Principal(getPrincipalName());
        PRIVATEALIAS = prop.getProperty("PRIVATEALIAS");
        KEYPWD = prop.getProperty("KEYPWD");
        STOREPWD = prop.getProperty("STOREPWD");
        SUBJECT = prop.getProperty("SUBJECT");
        licPath = prop.getProperty("licPath");
        priPath = prop.getProperty("priPath");
        //license content
        issuedTime = prop.getProperty("issuedTime");
        notBefore = prop.getProperty("notBefore");
        notAfter = prop.getProperty("notAfter");
        consumerType = prop.getProperty("consumerType");
        consumerAmount = Integer.valueOf(prop.getProperty("consumerAmount"));
        info = prop.getProperty("info");
        // 获取扩展参数
        exParam.put("ip", prop.getProperty("ip"));
        exParam.put("mac", prop.getProperty("mac"));
    }

    public void setCustomParam(String ip, String mac, String expireTime, String path){
        // 获取参数
        DEFAULTHOLDERANDISSUER = new X500Principal(getPrincipalName());
        PRIVATEALIAS = prop.getProperty("PRIVATEALIAS");
        KEYPWD = prop.getProperty("KEYPWD");
        STOREPWD = prop.getProperty("STOREPWD");
        SUBJECT = prop.getProperty("SUBJECT");
        licPath = path;
        priPath = prop.getProperty("priPath");
        //license content
        issuedTime = expireTime;
        notBefore = prop.getProperty("notBefore");
        notAfter = prop.getProperty("notAfter");
        consumerType = prop.getProperty("consumerType");
        consumerAmount = Integer.valueOf(prop.getProperty("consumerAmount"));
        info = prop.getProperty("info");
        // 获取扩展参数
        exParam.put("ip", ip);
        exParam.put("mac", mac);
    }


    @Test
    public void create() {
        try {
            /************** 证书发布者端执行 ******************/
            LicenseParam licenseParam = initLicenseParams0();
            LicenseManager licenseManager = new LicenseManager(licenseParam);
            File file = new File(licPath);
            licenseManager.store((createLicenseContent(exParam)), file);
            System.out.println("服务器端生成证书成功!");
            System.out.println("证书位置：" + file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端证书生成失败!");
        }
    }

    // 返回生成证书时需要的参数
    private static LicenseParam initLicenseParams0() {
        Preferences preference = Preferences.userNodeForPackage(CreateLicense.class);
        // 设置对证书内容加密的对称密码
        CipherParam cipherParam = new DefaultCipherParam(STOREPWD);
        // 参数1,2从哪个Class.getResource()获得密钥库;参数3密钥库的别名;参数4密钥库存储密码;参数5密钥库密码
        KeyStoreParam privateStoreParam = new DefaultKeyStoreParam(CreateLicense.class, priPath, PRIVATEALIAS, STOREPWD, KEYPWD);
        LicenseParam licenseParams = new DefaultLicenseParam(SUBJECT, preference, privateStoreParam, cipherParam);
        return licenseParams;
    }

    // 从外部表单拿到证书的内容
    public final static LicenseContent createLicenseContent(Map<String, String> exParms) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        LicenseContent content = null;
        content = new LicenseContent();
        content.setSubject(SUBJECT);
        content.setHolder(DEFAULTHOLDERANDISSUER);
        content.setIssuer(DEFAULTHOLDERANDISSUER);
        try {
            content.setIssued(format.parse(issuedTime));
            content.setNotBefore(format.parse(notBefore));
            content.setNotAfter(format.parse(notAfter));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        content.setConsumerType(consumerType);
        content.setConsumerAmount(consumerAmount);
        content.setInfo(info);
        // 扩展
        content.setExtra(exParms);
        return content;
    }
}
