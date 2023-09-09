package com.wei.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wei.entity.Books;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author huangxuwei
 * @description 针对表【books】的数据库操作Mapper
 * @createDate 2023-09-09 20:37:58
 * @Entity com.wei.entity.Books
 */
@Mapper
public interface BooksMapper extends BaseMapper<Books> {

    void insertBook(Books books);

    // 添加一个根据ISBN查询书籍信息的方法
    Books selectBookByISBN(String isbn);
}
