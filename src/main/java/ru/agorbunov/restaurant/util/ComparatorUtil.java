package ru.agorbunov.restaurant.util;

import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;

import java.util.Comparator;

/**
 * Created by Admin on 02.04.2017.
 */
public class ComparatorUtil {
    public static Comparator<Dish> dishComparator = new Comparator<Dish>() {
        @Override
        public int compare(Dish o1, Dish o2) {
            return o1.getDescription().compareTo(o2.getDescription());
        }
    };

    public static Comparator<Order> orderComparator = new Comparator<Order>() {
        @Override
        public int compare(Order o1, Order o2) {
            return o1.getId()-o2.getId();
        }
    };
}
