package xin.altitude.cms.common.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import xin.altitude.cms.common.util.JacksonUtils;

@SpringBootTest
@Log4j2
@TestPropertySource(locations = "classpath:application.properties")
class SensitiveDataFilterTest {

    @Test
    void decide() {
        User user = new User("123", "13231695622");
        log.info(user);
        log.info("user = {}", JacksonUtils.writeValueAsString(null,user));
    }

    @Test
    public void someMethod() {
        log.info("User phone number is 12345678901");
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        String userId;
        String phone;
    }

}
