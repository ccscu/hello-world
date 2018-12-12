package com.newqur.jdbc.class02_springbase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

/**
 * @author charles-desktop
 */
public class SpringJDBCExample {
    public static void main(String[] args) {
        testApplicationContext();
    }

    private static void testApplicationContext() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) applicationContext.getBean("jdbcTemplate");
        jdbcTemplate.query("select * from test", (RowCallbackHandler) (resultSet) -> {
            System.out.println("id=" + resultSet.getInt(1));
            System.out.println("name=" + resultSet.getString("name"));
        });
    }
}
