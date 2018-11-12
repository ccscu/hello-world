package com.newqur.mapper;

import com.newqur.entities.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    /**
     * 获得所有图书
     */
    public List<Book> getAllBooks();
    /**
     * 根据图书编号获得图书对象
     */
    public Book getBookById(@Param("id") int id);
    /**
     * 添加图书
     */
    public int add(Book entity);
    /**
     * 根据图书编号删除图书
     */
    public int delete(int id);
    /**
     * 更新图书
     */
    public int update(Book entity);
}
