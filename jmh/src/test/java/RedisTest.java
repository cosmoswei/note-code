import com.wei.redis.RedisMng;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.annotation.Resource;

@Slf4j
class RedisTest {
    @Resource
    RedisMng redisMng;

    @Test
    void redisTest() {
        Object bucket = redisMng.getKey("city");
        System.out.println(bucket);
    }
}
