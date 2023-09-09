package com.wei.mock;

import com.wei.entity.Books;
import com.wei.mapper.BooksMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BookManager {

    @Resource
    private BooksMapper booksMapper;

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
}
