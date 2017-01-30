package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.Order;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface OrderService {

    Order save(Order order, int userId, int restaurantId, List<Integer> dishesId);

    void delete(int id);

    List<Order> getAll();

    Order get(int id, int userId, int restaurantId);

}
