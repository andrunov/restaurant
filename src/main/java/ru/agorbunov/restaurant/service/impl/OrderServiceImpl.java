package ru.agorbunov.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.OrderRepository;
import ru.agorbunov.restaurant.service.OrderService;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkArrCompatibility;
import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 30.01.2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order save(Order order, int userId, int restaurantId, int[] dishIds, int[] dishQuantityValues) {
        Assert.notNull(order,"order must not be null");
        checkArrCompatibility(dishIds,dishQuantityValues);
        return checkNotFoundWithId(repository.save(order,userId,restaurantId,dishIds,dishQuantityValues),order.getId());
    }

    @Override
    public Order save(Order order, int userId, int restaurantId) {
        Assert.notNull(order,"order must not be null");
        return checkNotFoundWithId(repository.save(order,userId,restaurantId),order.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public Order get(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(repository.get(id,userId,restaurantId),id);
    }

    @Override
    public Order getWithDishes(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(repository.getWithDishes(id,userId,restaurantId),id);
    }

    @Override
    public List<Order> getByUser(int userId) {
        return repository.getByUser(userId);
    }
}
