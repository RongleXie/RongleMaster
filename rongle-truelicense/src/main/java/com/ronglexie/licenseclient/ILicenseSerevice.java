package com.ronglexie.licenseclient;

import java.util.Map;

/**
 * license 服务类接口
 * Created by lmc on 2017/7/8.
 */
public interface ILicenseSerevice {

    /**
     * 验证license
     * @param
     * @return  通过返回tru，未通过抛出异常或返回false
     * @author wxt.lmc
     * @version 2017/7/8
     */
    boolean verifyLicense();

    /**
     * 验证license
     * @param customParams 自定义参数
     * @return 通过返回tru，未通过抛出异常或返回false
     * @author wxt.lmc
     * @version 2017/7/8
     */
    boolean verifyLicense(Map<String, Object> customParams) throws Exception;

    /**
     * 显示错误信息
     * @param message
     */
    void showMessage(String message);
}
