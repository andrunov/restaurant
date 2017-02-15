package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static ru.agorbunov.restaurant.OrderTestData.*;

/**
 * Created by Admin on 30.01.2017.
 */
public class OrderServiceImplTest extends AbstractServiceTest {

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