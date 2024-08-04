package xin.altitude.cms.common.util;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xin.altitude.cms.common.model.KVModel;

@SpringBootTest
@Log4j2
class RedissonUtilsTest {
    @Test
    void save() {
        RedissonUtils.save(null, 1, "123", null);
    }

    //KVModel

    @Test
    void save2() {
        KVModel<String, Integer> kvModel = new KVModel<>("abc", 123);
        RedissonUtils.save(null, 2, kvModel, null);

        KVModel model = RedissonUtils.getObject(null, 2, KVModel.class);
        log.info("model = {}", model);
    }
}
