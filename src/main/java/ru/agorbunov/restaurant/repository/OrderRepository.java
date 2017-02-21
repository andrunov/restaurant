package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Order;

/**
 * Created by Admin on 30.01.2017.
 */
public interface OrderRepository extends BaseRepository<Order> {

    Order save(Order order,  int userId, int restaurantId, int... dishIds);

    Order get(int id, int userId, int restaurantId);

    Order getWith(int id, int userId, int restaurantId);

}
