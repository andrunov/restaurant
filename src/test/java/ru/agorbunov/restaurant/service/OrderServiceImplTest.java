package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.matcher.ModelMatcher;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

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
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_CREATED,ORDER_01,ORDER_05,ORDER_03,ORDER_06,ORDER_04,ORDER_02),service.getAll());
    }

    @Test
    public void saveNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("order must not be null");
        service.save(null,USER_01_ID,RESTAURANT_01_ID, DISH_01_ID,DISH_02_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(ORDER_01_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_05,ORDER_03,ORDER_06,ORDER_04,ORDER_02),service.getAll());

    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_01,ORDER_05,ORDER_03,ORDER_06,ORDER_04,ORDER_02),service.getAll());
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(ORDER_01,service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", ORDER_01_ID));
        service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_02_ID);
    }

    @Test
    public void update() throws Exception{
        Order order = service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID);
        order.setDateTime( LocalDateTime.of(2017,2,16,17,46));
        service.save(order,USER_01_ID,RESTAURANT_01_ID,DISH_01_ID,DISH_02_ID,DISH_04_ID);
        MATCHER.assertEquals(order, service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID));
    }

    @Test
    public void updateNotFound() throws Exception{
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", ORDER_01_ID));
        Order order = service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID);
        service.save(order,USER_01_ID,RESTAURANT_02_ID);
    }

    @Test
    public void updateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("order must not be null");
        service.save(null,USER_01_ID,RESTAURANT_01_ID, DISH_01_ID,DISH_02_ID);
    }

    @Test
    public void getWith() throws Exception {
        ModelMatcher<Dish> DishMatcher = new ModelMatcher<>();
        Order order = service.getWith(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID);
        MATCHER.assertEquals(ORDER_01,order);
        DishMatcher.assertCollectionEquals(ORDER_01.getDishes(),order.getDishes());
    }

    @Test
    public void getWithNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", ORDER_01_ID));
        service.getWith(ORDER_01_ID,USER_01_ID,RESTAURANT_02_ID);
    }

    @Test
    public void getByUser() throws Exception{
        MATCHER.assertCollectionEquals(Collections.singletonList(ORDER_01),service.getByUser(USER_01_ID));
    }


}