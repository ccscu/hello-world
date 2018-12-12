package com.newqur.testjpa_mybatis;

import com.newqur.entities.Book;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.*;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author charles-desktop
 */
public class TestMybatisPure {

    private SqlSession session = null;

    @Before
    public void before() throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        session = factory.openSession();
    }

    @After
    public void after() {
        session.close();
    }
    @Test
    public void testGetBookById()  {
        //参数一：namespace.id
        Book book = session.selectOne("com.newqur.mapper.BookMapper.getBookByid",1);
        System.out.println(book);
    }

    @Test
    public void testGetAllBooks() throws Exception {
        List<Book> books = session.selectList("com.newqur.mapper.BookMapper.getAllBooks");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testAdd() throws Exception {
        Book book = new Book();
        book.setTitle("Java编程的艺术");
        book.setPrice(54);
        book.setPublishDate(new Date());
        session.insert("com.newqur.mapper.BookMapper.add", book);
        //增删改，一定一定要加上commit操作
        session.commit();
    }

    @Test
    public void testDelete() throws Exception {
        session.delete("com.newqur.mapper.BookMapper.delete", 7);
        session.commit();   //增删改，一定一定要加上commit操作
    }

    @Test
    public void testUpdate() throws Exception {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Java编程的艺术");
        book.setPrice(65);
        book.setPublishDate(new Date());
        session.update("com.newqur.mapper.BookMapper.update", book);
        session.commit();   //增删改，一定一定要加上commit操作

    }

}
