import com.wei.util.ThreadUtils;
import com.wei.util.TimeUtils;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Date;

public class LocalDateTimeUtilTest {
    public static void main(String[] args) {
        LocalDateTime of = TimeUtils.of(new Date());
        System.out.println(of);
    }


    @Test
    public void test() {
        ThreadUtils.threadLog("黄旭伟线程日志打印");
        ThreadUtils.safeSleep(20000L);
    }
}
