package ru.agorbunov.restaurant.util;

import ru.agorbunov.restaurant.model.Dish;

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
}
