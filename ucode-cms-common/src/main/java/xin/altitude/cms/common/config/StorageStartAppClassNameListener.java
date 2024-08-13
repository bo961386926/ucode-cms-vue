package xin.altitude.cms.common.config;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * 获取启动类包名
 */
@Configuration
public class StorageStartAppClassNameListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    public static final String APP_PACKAGE_NAME = "AppPackageName";

    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        String AppPackageName = event.getSpringApplication().getMainApplicationClass().getPackageName();
        System.setProperty(APP_PACKAGE_NAME, AppPackageName);
        System.out.println(System.getProperty(APP_PACKAGE_NAME));
    }

}
