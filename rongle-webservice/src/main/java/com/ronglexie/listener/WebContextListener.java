package com.ronglexie.listener;

import com.ronglexie.license.exception.InvaildLicenseException;
import com.ronglexie.licenseclient.LicenseSereviceImpl;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.context.ContextLoaderListener;
import javax.servlet.ServletContextEvent;

/**
 * @author wxt.xqr
 * @version 2017-8-31
 */
public class WebContextListener extends ContextLoaderListener implements InitializingBean,DisposableBean {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            LicenseSereviceImpl licenseSerevice = new LicenseSereviceImpl();
            try {
                boolean bl = licenseSerevice.verifyLicense();
                if (!bl) {
                    throw new InvaildLicenseException("授权证书验证失败，请联系服务提供商！");
                }
            } catch (Exception e) {
                if (e instanceof InvaildLicenseException) {
                    licenseSerevice.showMessage(e.getLocalizedMessage());
                }
                throw new InvaildLicenseException(e.getMessage(), e);
            }
        } catch (Exception e) {
            System.exit(1);
        }
        super.contextInitialized(event);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        super.contextDestroyed(event);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void destroy() throws Exception {

    }
}
