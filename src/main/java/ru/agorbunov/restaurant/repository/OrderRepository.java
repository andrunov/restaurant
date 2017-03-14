package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Order;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface OrderRepository extends BaseRepository<Order> {

    Order save(Order order,  int userId, int restaurantId, int... dishIds);

    Order get(int id, int userId, int restaurantId);

    Order getWithDishes(int id, int userId, int restaurantId);

    List<Order> getByUser(int userId);

}
