package ru.agorbunov.restaurant;

import ru.agorbunov.restaurant.model.Order;

import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.agorbunov.restaurant.DishTestData.*;
import static ru.agorbunov.restaurant.RestaurantTestData.*;
import static ru.agorbunov.restaurant.UserTestData.*;

/**
 * Created by Admin on 21.01.2017.
 */
public class OrderTestData {
    public static final Order ORDER_01 = new Order(USER_01,RESTAURANT_01, Arrays.asList(DISH_01, DISH_02, DISH_04), LocalDateTime.of(2017,1,15,15,47));
    public static final Order ORDER_02 = new Order(USER_02,RESTAURANT_01, Arrays.asList(DISH_01, DISH_02, DISH_04, DISH_05), LocalDateTime.of(2017,1,14,18,49));
    public static final Order ORDER_03 = new Order(USER_03,RESTAURANT_02, Arrays.asList(DISH_06, DISH_07, DISH_08), LocalDateTime.of(2017,1,15,12,7));
    public static final Order ORDER_04 = new Order(USER_04,RESTAURANT_02, Arrays.asList(DISH_08, DISH_09), LocalDateTime.of(2017,1,15,2,9));
    public static final Order ORDER_05 = new Order(USER_05,RESTAURANT_03, Arrays.asList(DISH_10, DISH_11), LocalDateTime.of(2017,1,15,14,17));
    public static final Order ORDER_06 = new Order(USER_06,RESTAURANT_04, Arrays.asList(DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20), LocalDateTime.of(2017,1,15,9,29));
}
