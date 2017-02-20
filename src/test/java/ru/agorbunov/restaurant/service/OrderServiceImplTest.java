package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
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
        service.save(ORDER_CREATED,USER_01_ID,RESTAURANT_01_ID, DISH_01_ID,DISH_02_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_01,ORDER_02,ORDER_03,ORDER_04,ORDER_05,ORDER_06,ORDER_CREATED),service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(ORDER_01_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_02,ORDER_03,ORDER_04,ORDER_05,ORDER_06),service.getAll());

    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_01,ORDER_02,ORDER_03,ORDER_04,ORDER_05,ORDER_06),service.getAll());
    }

    @Test
    public void get() throws Exception {
        System.out.println(service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID));
        MATCHER.assertEquals(ORDER_01,service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", ORDER_01_ID));
        System.out.println(service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_02_ID));
    }

    @Test
    public void update() throws Exception{
        Order order = service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID);
        order.setDateTime( LocalDateTime.of(2017,2,16,17,46));
        service.update(order,USER_01_ID,RESTAURANT_01_ID,DISH_01_ID,DISH_02_ID,DISH_04_ID);
        MATCHER.assertEquals(order, service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID));
    }

    @Test
    public void updateNotFound() throws Exception{
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", ORDER_01_ID));
        service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_02_ID);
    }

}