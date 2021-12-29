package xin.ucode.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动程序
 *
 * @author ucode
 */
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class CmsApplication {
    public static void main(String[] args) {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(CmsApplication.class, args);
        // System.out.println(SpringUtils.getBean(CmsConfig.class));
        System.out.println("项目启动成功");
    }
}
