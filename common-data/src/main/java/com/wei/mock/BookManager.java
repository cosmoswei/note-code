package com.wei.mock;

import com.wei.entity.Books;
import com.wei.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Component
public class BookManager {

    @Resource
    private BooksMapper booksMapper;
    @Autowired
    private TransactionTemplate transactionTemplate;

    public Books getBooksByISNB(String isbn) throws InterruptedException {
        final Books[] books = {new Books()};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                books[0] = booksMapper.selectBookByISBN(isbn);
            }
        });
        thread.start();
        Thread.sleep(1000L);
        return books[0];

    }

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
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            booksMapper.insertBook(books);
            return null;

        });
        // 本地
        // DB：select UNIX_TIMESTAMP(eventTime) from books where BookID = 46                // 1694275016.113489
        System.out.println("System.currentTimeMillis() = " + System.currentTimeMillis());   // 1694275016122
        // 其中执行一大串事务
        Books booksByISNB = this.getBooksByISNB(ISNB);
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
