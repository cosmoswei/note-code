import com.wei.util.StreamUtils;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class StreamUtilsTest {

    @Test
    public void test() {
        List<People> peopleList = MockClass.mockPeopleList();
        List<People> collect = peopleList.stream()
                .filter(StreamUtils.distinctByAttr(People::getName))
                .collect(Collectors.toList());
        System.out.println(peopleList);
        System.out.println(collect);

    }
}
