import com.wei.utils.TimeUtils;

import java.time.LocalDateTime;
import java.util.Date;

public class LocalDateTimeUtilTest {
    public static void main(String[] args) {
        LocalDateTime of = TimeUtils.of(new Date());
        System.out.println(of);
    }
}
