package xin.altitude.cms.common.support;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomAssertTest {

    @Test
    void isTrue() {
        CustomAssert.isTrue(false,"aaa");
    }
}
