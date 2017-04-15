package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Dish;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 30.01.2017.
 */
public interface DishRepository extends BaseRepository<Dish> {

    Dish save(Dish dish,  int menulistId);

    Dish get(int id, int menuListId);

    Dish getWithOrders(int id, int menuListId);

    List<Dish> getByMenuList(int menuListId);

    Map<Dish, Integer> getByOrder(int orderId);

    boolean deleteFromOrder(int id,int orderId);
}
