import com.wei.util.StreamUtils
import com.wei.util.mock.MockClass
import com.wei.util.mock.People
import spock.lang.Specification

import java.util.function.Predicate
import java.util.stream.Collectors

class StreamUtilsTest extends Specification {

    def "去重测试"() {
        when:
        List<People> peopleList = MockClass.mockPeopleList();
        List<People> collect = peopleList.stream()
                .filter(StreamUtils.distinctByAttr(People::getName))
                .collect(Collectors.toList());
        then:
        peopleList.size() == 8
        collect.size() == 7
    }

    def "Spock where"() {
        expect:
        c == Math.max(a, b)

        where:
        c | a | b
        2 | 1 | 3
        3 | 1 | 3
        7 | 3 | 7
        8 | 3 | 7


    }

    def "截断测试 takeWhile"() {
        expect:
        List<People> peopleList = MockClass.mockPeopleList()
        List<People> afterPeopleList = StreamUtils.takeWhile(peopleList, new Predicate<People>() {
            @Override
            boolean test(People people) {
                return people.getId() < 4
            }
        })
        afterPeopleList.size() == size
        where:
        size | num
        8    | 3
    }

    def "截断测试 dropWhile"() {
        expect:
        List<People> peopleList = MockClass.mockPeopleList()
        List<People> afterPeopleList = StreamUtils.dropWhile(peopleList, new Predicate<People>() {
            @Override
            boolean test(People people) {
                return people.getId() > 7
            }
        })
        afterPeopleList.size() == size
        where:
        size | num
        1    | 3
    }
}
