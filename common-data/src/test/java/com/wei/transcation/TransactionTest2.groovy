package com.wei.transcation

import com.wei.DataAppRun
import com.wei.mock.BookManager
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification
import spock.lang.Unroll

import javax.annotation.Resource

@SpringBootTest(classes = DataAppRun.class)
class TransactionTest2 extends Specification {



    @Resource
    private BookManager bookManager;

    @Unroll
    def "test"() {
        expect: "when + then 的组合"
        bookManager.test();
    }
}
