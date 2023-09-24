package com.wei.transcation;

import com.wei.DataAppRun;
import com.wei.mapper.BooksMapper;
import com.wei.mock.BookManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest(classes = DataAppRun.class)
@Slf4j
public class TransactionTest {


    @Resource
    private BooksMapper booksMapper;
    @Resource
    private BookManager bookManager;

    @Test
    public void test() throws InterruptedException {
        bookManager.test();
    }
}
