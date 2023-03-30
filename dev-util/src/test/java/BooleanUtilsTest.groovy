import com.wei.util.BooleanUtils
import spock.lang.Specification

class BooleanUtilsTest extends Specification {

    def "handler测试"() {
        expect:
        BooleanUtils.isTure(bool).handler(() -> {
            println("布尔为假")
        })
        println(1)
        where:
        bool  | x
        false | 0
        true  | 0
    }

    def "异常测试"() {

        when:
        BooleanUtils.orThrow(bool).throwMessage(message)

        then:
        def exception = thrown(expectedException)
        errorMessage == exception.getMessage()
        where:
        bool  | message | expectedException | errorMessage
        false | "1"     | RuntimeException  | "1"
        false | "1"     | RuntimeException  | "1"
        false | "2"     | RuntimeException  | "2"
        true  | "2"     | null              | null
    }

    def "不同异常测试"() {

        when:
        BooleanUtils.orThrow(bool, OutOfMemoryError::new).message(message)

        then:
        def exception = thrown(expectedException)
        errorMessage == exception.getMessage()
        where:
        bool  | message | expectedException | errorMessage
        false | "1"     | OutOfMemoryError  | "1"
        false | "1"     | OutOfMemoryError  | "1"
        false | "2"     | RuntimeException  | "2"
        true  | "2"     | null              | null
    }
}
