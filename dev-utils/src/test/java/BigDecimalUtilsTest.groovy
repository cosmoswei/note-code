import com.wei.util.BigDecimalUtils
import spock.lang.Specification

class BigDecimalUtilsTest extends Specification {


    def "BigDecimal-空指针测试"() {
        when:
        result == BigDecimalUtils.eq(var1, var2)
        then:
        def exception = thrown(expectedException)
        errorMessage == exception.getMessage()
        where:
        result | var1                  | var2                | expectedException    | errorMessage
        true   | new BigDecimal("1")   | new BigDecimal("1") | NullPointerException | "对象不可为空"
        true   | new BigDecimal("001") | new BigDecimal("1") | NullPointerException | "对象不可为空"
        false  | new BigDecimal("1.1") | new BigDecimal("1") | NullPointerException | "对象不可为空"
        null   | new BigDecimal("100") | null                | NullPointerException | "对象不可为空"
    }

    def "BigDecimal-等于测试"() {
        when:
        result
        then:
        result == BigDecimalUtils.eq(var1, var2)
        where:
        result | var1                  | var2
        true   | new BigDecimal("1")   | new BigDecimal("1")
        true   | new BigDecimal("001") | new BigDecimal("1")
        false  | new BigDecimal("1.1") | new BigDecimal("1")
    }

    def "BigDecimal-不等于测试"() {
        when:
        result
        then:
        result == BigDecimalUtils.ne(var1, var2)
        where:
        result | var1                  | var2
        false  | new BigDecimal("1")   | new BigDecimal("1")
        false  | new BigDecimal("001") | new BigDecimal("1")
        true   | new BigDecimal("1.1") | new BigDecimal("1")
    }

    def "BigDecimal-大于测试"() {
        when:
        result
        then:
        result == BigDecimalUtils.gt(var1, var2)
        where:
        result | var1                  | var2
        false  | new BigDecimal("1")   | new BigDecimal("1")
        false  | new BigDecimal("001") | new BigDecimal("1")
        true   | new BigDecimal("1.1") | new BigDecimal("1")
        true   | new BigDecimal("12")  | new BigDecimal("1")
        false  | new BigDecimal("12")  | new BigDecimal("12")
    }

    def "BigDecimal -小于测试"() {
        when:
        result
        then:
        result == BigDecimalUtils.lt(var1, var2)
        where:
        result | var1                  | var2
        false  | new BigDecimal("1")   | new BigDecimal("1")
        false  | new BigDecimal("001") | new BigDecimal("1")
        false  | new BigDecimal("1.1") | new BigDecimal("1")
        false  | new BigDecimal("12")  | new BigDecimal("1")
        false  | new BigDecimal("12")  | new BigDecimal("12")
        true   | new BigDecimal("12")  | new BigDecimal("13")
    }

    def "BigDecimal -大于等于测试"() {
        when:
        result
        then:
        result == BigDecimalUtils.ge(var1, var2)
        where:
        result | var1                  | var2
        true   | new BigDecimal("1")   | new BigDecimal("1")
        true   | new BigDecimal("001") | new BigDecimal("1")
        true   | new BigDecimal("1.1") | new BigDecimal("1")
        true   | new BigDecimal("12")  | new BigDecimal("1")
        true   | new BigDecimal("12")  | new BigDecimal("12")
        false  | new BigDecimal("12")  | new BigDecimal("13")
    }

    def "BigDecimal-小于等于测试"() {
        when:
        result
        then:
        result == BigDecimalUtils.le(var1, var2)
        where:
        result | var1                  | var2
        true   | new BigDecimal("1")   | new BigDecimal("1")
        true   | new BigDecimal("001") | new BigDecimal("1")
        false  | new BigDecimal("1.1") | new BigDecimal("1")
        false  | new BigDecimal("12")  | new BigDecimal("1")
        true   | new BigDecimal("12")  | new BigDecimal("12")
    }
}
