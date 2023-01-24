import com.wei.BooleanUtils;
import com.wei.VUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

public class FunctionInterface {

    @Test
    public void test() throws Exception {
        // elseNullThrown, elseFalseThrown
        VUtils.isTure(Boolean.FALSE).throwMessage("new Exception(?\"转义双引号试试\")");

        Optional.ofNullable("").orElseThrow(Exception::new);
        VUtils.isTureOrFalse(false).trueOrFalseHandle(() -> System.out.println("黄旭伟"),
                () -> System.out.println("旭伟黄"));

        VUtils.isBlankOrNoBlank("").presentOrElseHandle(System.out::println, RunnableImpl.getRunnable());
    }

    @Test
    public void booleanUtilsTest() throws Exception {

        BooleanUtils.orElseThrow(true, Exception::new);
    }

    @Test
    public void isNullTest() throws Exception {

        Date date = null;

        BooleanUtils.isNull(date).throwMessage("这是一个空吗？");

        Optional.ofNullable(date).orElseThrow(RuntimeException::new);
    }
}

class RunnableImpl implements Runnable {

    private RunnableImpl() {

    }

    private static final RunnableImpl RUNNABLE = new RunnableImpl();

    @Override
    public void run() {
        System.err.println("试试自己实现一个Runnable！");
    }

    public static RunnableImpl getRunnable() {
        return RUNNABLE;
    }
}
