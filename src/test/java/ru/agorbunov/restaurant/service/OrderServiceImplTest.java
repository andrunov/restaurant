package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static ru.agorbunov.restaurant.OrderTestData.*;

/**
 * Created by Admin on 30.01.2017.
 */
@ContextConfiguration(value = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class OrderServiceImplTest {

    @Autowired
    private OrderService service;

    @Test
    public void save() throws Exception {
        service.save(ORDER_CREATED,100000,100006, 100020,100021);
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_01,ORDER_02,ORDER_03,ORDER_04,ORDER_05,ORDER_06,ORDER_CREATED),service.getAll());

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_01,ORDER_02,ORDER_03,ORDER_04,ORDER_05,ORDER_06),service.getAll());
    }

    @Test
    public void get() throws Exception {
        System.out.println("*****************");
//        System.out.println(service.get(100010));
        System.out.println("*****************");

    }

}