import com.wei.util.SplitUtils
import com.wei.util.mock.People
import spock.lang.Specification

class SplitUtilsTest extends Specification {

    def "获取首个分割字符测试"() {
        expect:
        first == SplitUtils.splitFirst(str, delim)
        where:
        first | str   | delim
        "a"   | "a_a" | "_"
        "a"   | "a,b" | ","
        ""    | "_a"  | "_"
        "a"   | "a"   | "_"
        null  | null  | "_"
        "a_a" | "a_a" | null
    }

    def ""(){
        given: "就是一个棒槌"
        def p1 = new People(name:"1",id:1)
    }
}
