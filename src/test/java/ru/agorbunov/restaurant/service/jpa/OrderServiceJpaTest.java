package ru.agorbunov.restaurant.service.jpa;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.agorbunov.restaurant.DishTestData;
import ru.agorbunov.restaurant.Profiles;
import ru.agorbunov.restaurant.RestaurantTestData;
import ru.agorbunov.restaurant.UserTestData;
import ru.agorbunov.restaurant.matcher.ModelMatcher;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.service.OrderServiceImplTest;

import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.agorbunov.restaurant.OrderTestData.*;

/**
 * Created by Admin on 23.02.2017.
 */
// TODO: 23.02.2017 delete class before production
@ActiveProfiles(Profiles.JPA)
public class OrderServiceJpaTest extends OrderServiceImplTest {

    @Test
    public void update() throws Exception{
        Order order = service.get(ORDER_01_ID,USER_01_ID,RESTAURANT_01_ID);
        order.setDateTime( LocalDateTime.of(2017,2,16,17,46));
        int dishIds[] = {DISH_01_ID,DISH_02_ID,DISH_03_ID,DISH_04_ID};
        int dishQuantityValues[] = {1,2,3,4};
        Order order1 = service.save(order,USER_01_ID,RESTAURANT_01_ID,dishIds,dishQuantityValues);
        Order orderSaved = service.getWithDishes(order1.getId(),USER_01_ID,RESTAURANT_01_ID);
//        MATCHER.assertEquals(order, orderSaved);
//        ModelMatcher<Dish> DishMatcher = new ModelMatcher<>();
//        DishMatcher.assertCollectionEquals(order.getDishes().keySet(),orderSaved.getDishes().keySet());
//        ModelMatcher<Integer> IntegerMatcher = new ModelMatcher<>();
//        IntegerMatcher.assertCollectionEquals(order.getDishes().values(),orderSaved.getDishes().values());
        System.out.println(orderSaved.getDishes().keySet());
        System.out.println(orderSaved.getDishes().values());
    }

    @Test
    public void save() throws Exception {
        int dishIds[] = {DISH_01_ID,DISH_02_ID};
        int dishQuantityValues[] = {1,2};
        Order orderSaved = service.save(ORDER_CREATED,USER_01_ID,RESTAURANT_01_ID, dishIds,dishQuantityValues);
        int orderSavedId = orderSaved.getId();
        orderSaved = service.getWithDishes(orderSavedId,USER_01_ID,RESTAURANT_01_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(ORDER_CREATED,ORDER_01,ORDER_05,ORDER_03,ORDER_06,ORDER_04,ORDER_02),service.getAll());
        ModelMatcher<Dish> DishMatcher = new ModelMatcher<>();
        DishMatcher.assertCollectionEquals(Arrays.asList(DishTestData.DISH_01,DishTestData.DISH_02), orderSaved.getDishes().keySet());
        ModelMatcher<Integer> IntegerMatcher = new ModelMatcher<>();
        IntegerMatcher.assertCollectionEquals(Arrays.asList(1,2), orderSaved.getDishes().values());
    }

    @Test
    public void updateWithoutDishes() throws Exception {
        Order order = service.get(ORDER_05_ID, UserTestData.USER_05_ID, RestaurantTestData.RESTAURANT_03_ID);
        order.setDateTime( LocalDateTime.of(2017,3,16,19,56));
        service.save(order, UserTestData.USER_05_ID, RestaurantTestData.RESTAURANT_03_ID);
        MATCHER.assertEquals(order,service.get(ORDER_05_ID, UserTestData.USER_05_ID, RestaurantTestData.RESTAURANT_03_ID));
        DishTestData.MATCHER.assertCollectionEquals(Arrays.asList(DishTestData.DISH_10, DishTestData.DISH_11),
                service.getWithDishes(ORDER_05_ID, UserTestData.USER_05_ID, RestaurantTestData.RESTAURANT_03_ID).getDishes().keySet());
    }

}
