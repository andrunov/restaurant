package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.Dish;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 30.01.2017.
 */
public interface DishService extends BaseService<Dish> {

    Dish save(Dish dish,  int menulistId);

    Dish get(int id, int menulistId);

    Dish getWithOrders(int id, int menulistId);

    List<Dish> getByMenuList(int menuListId);

    Map<Dish, Integer> getByOrder(int orderId);

    boolean deleteFromOrder(int id,int orderId);

}
