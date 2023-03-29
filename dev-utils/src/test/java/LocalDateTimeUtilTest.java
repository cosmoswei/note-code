import com.wei.util.StreamUtils;
import com.wei.util.ThreadUtils;
import com.wei.util.TimeUtils;
import com.wei.util.mock.People;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class LocalDateTimeUtilTest {
    public static void main(String[] args) {
        LocalDateTime of = TimeUtils.of(new Date());
        System.out.println(of);
    }


    @Test
    public void test() {
        ThreadUtils.threadLog("黄旭伟线程日志打印");
        ThreadUtils.safeSleep(20000L);

        List<People> people = new ArrayList<>();

        List<People> people1 = StreamUtils.dropWhile(people, new Predicate<People>() {
            @Override
            public boolean test(People people) {
                return false;
            }
        });
    }
}
