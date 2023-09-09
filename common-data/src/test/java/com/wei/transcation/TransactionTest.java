package com.wei.transcation;

import com.wei.entity.Books;
import com.wei.mapper.BooksMapper;
import com.wei.mock.BookManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@Slf4j
public class TransactionTest {


    @Autowired
    private TransactionTemplate transactionTemplate;
    @Resource
    private BooksMapper booksMapper;
    @Resource
    private BookManager bookManager;

    @Test
    public void test() throws InterruptedException {

        // 生成一个Code，存到数据库
        UUID uuid = UUID.randomUUID();
        long leastSignificantBits = uuid.getMostSignificantBits();
        String ISNB = String.valueOf(leastSignificantBits);
        Books books = buildBook();
        books.setISBN(ISNB);
        transactionTemplate.execute(r -> {
            booksMapper.insertBook(books);

            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            books.setISBN("测试");
            booksMapper.insertBook(books);
            return null;
        });
        // 其中执行一大串事务
        Books booksByISNB = bookManager.getBooksByISNB(ISNB);
        System.out.println("ISBN==" + ISNB);
        System.out.println(booksByISNB);
        // 另外一个线程去取这个数据，看能不能取到
    }

    private Books buildBook() {
        Books books = new Books();
        books.setTitle("深入理解MySQL事务");
        books.setAuthor("黄旭伟");
        books.setPublicationDate(new Date());
        books.setPublisher("清华大学出版社");
        books.setPrice(new BigDecimal("9.9"));
        books.setStockQuantity(0);
        books.setGenre("黄旭伟");
        books.setCoverImage("黄旭伟");
        books.setDescription("黄旭伟");
        books.setCreatedAt(new Date());
        books.setUpdatedAt(new Date());
        return books;
    }

}
