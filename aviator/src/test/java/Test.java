import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

public class Test {

    @org.junit.jupiter.api.Test
    public void test() {
        List<String> strings = new ArrayList<>();
        LongStream.range(0, 100000).parallel().forEach(b -> {
            strings.add(String.valueOf(b));
            System.out.println("神牛-" + b);
        });
        System.out.println(strings.size());
    }
}
