package com.ronglexie.licenseclient;

import java.util.Map;

/**
 *  license 对外提供服务
 * Created by lmc on 2017/7/8.
 */
public class LicenseSereviceImpl implements  ILicenseSerevice{

    @Override
    public boolean verifyLicense(){
        VerifyLicense vLicense = new VerifyLicense();
        //获取参数
        vLicense.setParam("param.properties");
        //生成证书
        return  vLicense.verify();
    }

    @Override
    public boolean verifyLicense(Map<String, Object> customParams) throws Exception {
        return false;
    }

    public void showMessage(String message){
        StringBuilder sb = new StringBuilder();
        sb.append("************************************************\n");
        sb.append("*\n");
        sb.append("*      "+message+"\n");
        sb.append("*\n");
        sb.append("************************************************\n");
        System.out.print(sb.toString());
    }
}
