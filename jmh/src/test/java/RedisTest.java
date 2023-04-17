import com.wei.JMHAppRun;
import com.wei.redis.RedisMng;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = JMHAppRun.class)
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
