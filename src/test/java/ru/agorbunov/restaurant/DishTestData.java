package ru.agorbunov.restaurant;

import ru.agorbunov.restaurant.matcher.ModelMatcher;
import ru.agorbunov.restaurant.model.Dish;

import java.util.Arrays;
import java.util.Collections;

import static ru.agorbunov.restaurant.MenuListTestData.*;
import static ru.agorbunov.restaurant.OrderTestData.*;

/**
 * Created by Admin on 20.01.2017.
 */
public class DishTestData {

    public static final ModelMatcher<Dish> MATCHER = new ModelMatcher<>();


    public static final Dish DISH_01 = new Dish("Каша овсяная",1.25, MENU_LIST_01, Arrays.asList(ORDER_01, ORDER_02));
    public static final Dish DISH_02 = new Dish("Сырники",3.45,MENU_LIST_01,Arrays.asList(ORDER_01, ORDER_02));
    public static final Dish DISH_03 = new Dish("Блины",2.48, MENU_LIST_01,Collections.singletonList(null));
    public static final Dish DISH_04 = new Dish("Суп гороховый",5.57,MENU_LIST_01,Arrays.asList(ORDER_01, ORDER_02));
    public static final Dish DISH_05 = new Dish("Рассольник",6.87,MENU_LIST_01,Collections.singletonList(ORDER_02));
    public static final Dish DISH_06 = new Dish("Жульен с грибами",12.47,MENU_LIST_02,Collections.singletonList(ORDER_03));
    public static final Dish DISH_07 = new Dish("Пельмени",7.96, MENU_LIST_02,Collections.singletonList(ORDER_03));
    public static final Dish DISH_08 = new Dish("Котлета по киевски",14.58, MENU_LIST_02,Arrays.asList(ORDER_03, ORDER_04));
    public static final Dish DISH_09 = new Dish("Чай черный",0.55, MENU_LIST_02,Collections.singletonList(ORDER_04));
    public static final Dish DISH_10 = new Dish("Чай зеленый",0.55,MENU_LIST_03,Collections.singletonList(ORDER_05));
    public static final Dish DISH_11 = new Dish("Кофе черный",0.75,MENU_LIST_03,Collections.singletonList(ORDER_05));
    public static final Dish DISH_12 = new Dish("Кофе белый",0.95,MENU_LIST_03,Collections.singletonList(null));
    public static final Dish DISH_13 = new Dish("Котлета по питерски",12.54,MENU_LIST_03,Collections.singletonList(null));
    public static final Dish DISH_14 = new Dish("Поросенок под хреном",24.58,MENU_LIST_03,Collections.singletonList(null));
    public static final Dish DISH_15 = new Dish("Чебурек",4.62,MENU_LIST_04,Collections.singletonList(ORDER_06));
    public static final Dish DISH_16 = new Dish("Беляш",4.12,MENU_LIST_04,Collections.singletonList(ORDER_06));
    public static final Dish DISH_17 = new Dish("Чай черный с лимоном",1.95,MENU_LIST_04,Collections.singletonList(ORDER_06));
    public static final Dish DISH_18 = new Dish("Борщ",17.58,MENU_LIST_04,Collections.singletonList(ORDER_06));
    public static final Dish DISH_19 = new Dish("Плов узбекский",12.75,MENU_LIST_04,Collections.singletonList(ORDER_06));
    public static final Dish DISH_20 = new Dish("Салат оливье",8.12,MENU_LIST_04,Collections.singletonList(ORDER_06));

    public static final Dish DISH_CREATED = new Dish("Созданная еда",3.12,MENU_LIST_01,Collections.singletonList(ORDER_01));


    public Dish getUpdated(Dish dish){
        dish.setDescription("Обновленная еда");
        dish.setPrice(5.48);
        return dish;
    }






}
