package com.newqur.testjpa_mybatis;

import com.newqur.entities.Book;
import com.newqur.service.BookService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMybatisSpringBoot {

    private static BookService bookservice;

    @BeforeClass
    public static void before() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        bookservice = ctx.getBean(BookService.class);
    }

    @Test
    public void testGetAllBooks() {
        List<Book> books = bookservice.getAllBooks();
        //assertNotNull(books);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void testAdd() {
        Book entity = new Book(0, "Hibernate 第七版", 78.1, new Date());
        try {
            assertEquals(1, bookservice.add(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteInt() {
        assertEquals(1, bookservice.delete(9));
    }

    @Test
    public void testDeleteStringArray() {
        String[] ids = {"7", "11", "12"};
        assertEquals(3, bookservice.delete(ids));
    }

    @Test
    public void testUpdate() {
        Book entity = new Book(7, "Hibernate 第二版", 79.1, new Date());
        try {
            assertEquals(1, bookservice.update(entity));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetBookById() {
        assertNotNull(bookservice.getBookById(1));
    }

    @Test
    public void testAddDouble() {
        //因为书名相同，添加第二本会失败，用于测试事务
        Book entity1 = new Book(0, "Hibernate 第八版", 78.1, new Date());
        Book entity2 = new Book(0, "Hibernate 第八版", 78.1, new Date());
        assertEquals(2, bookservice.add(entity1, entity2));
    }
}
