import com.wei.util.OrderUtils
import com.wei.util.SplitUtils
import com.wei.util.mock.MockClass
import com.wei.util.mock.People
import spock.lang.Specification

import java.util.stream.Stream

class OrderTest extends Specification {

    def "排序测试"() {
        when:
        List<People> peopleList = MockClass.mockManyPeople(100000);
        List<Integer> str = MockClass.mockManyPeopleStr(100000);
        Collections.reverse(str)
        def split = SplitUtils.split("5,4,3,1", ",")
        def list = Stream.of(8, 7, 6, 5, 4).toList()
        OrderUtils.orderBySeq(split, peopleList, "code")
        then:
        println(list)
    }
}
