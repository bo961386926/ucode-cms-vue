package xin.altitude.cms.common.support;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssertTest {

    @Test
    void isTrue() {
        Assert.isTrue(false,"aaa");
    }
}
