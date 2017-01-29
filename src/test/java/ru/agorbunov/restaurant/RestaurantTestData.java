package ru.agorbunov.restaurant;

import ru.agorbunov.restaurant.model.Restaurant;

import java.util.Collections;

import static ru.agorbunov.restaurant.MenuListTestData.*;

/**
 * Created by Admin on 21.01.2017.
 */
public class RestaurantTestData {

    public static final Restaurant RESTAURANT_01 = new Restaurant("Ёлки-палки","ул. Некрасова, 14", Collections.singletonList(MENU_LIST_01));
    public static final Restaurant RESTAURANT_02 = new Restaurant("Пиццерия","пл. Пушкина, 6", Collections.singletonList(MENU_LIST_02));
    public static final Restaurant RESTAURANT_03 = new Restaurant("Закусочная","Привокзальная пл, 3", Collections.singletonList(MENU_LIST_03));
    public static final Restaurant RESTAURANT_04 = new Restaurant("Прага","ул. Арбат, 1", Collections.singletonList(MENU_LIST_04));
}
