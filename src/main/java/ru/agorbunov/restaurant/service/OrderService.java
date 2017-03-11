package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.Order;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface OrderService extends BaseService<Order> {

    Order save(Order order, int userId, int restaurantId, int... dishesId);

    Order get(int id, int userId, int restaurantId);

    Order getWith(int id, int userId, int restaurantId);

    List<Order> getByUser(int userId);

}
