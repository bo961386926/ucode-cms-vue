package xin.altitude.cms.common.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@Log4j2
@TestPropertySource(locations = "classpath:application.properties")
class SensitiveDataFilterTest {

    @Test
    void decide() {
        User user = new User("13811112222", "13231695622");
        log.error(user);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User{
        String userId;
        String phone;
    }

}
