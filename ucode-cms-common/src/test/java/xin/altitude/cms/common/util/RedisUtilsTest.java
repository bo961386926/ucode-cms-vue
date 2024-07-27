package xin.altitude.cms.common.util;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RedisUtilsTest {

    @Test
    void save() {
        RedisUtils.save(null, 1, "123", null);
    }

    @Test
    void save2() {
        RedisUtils.save(null, null, "123", null);
    }
}
