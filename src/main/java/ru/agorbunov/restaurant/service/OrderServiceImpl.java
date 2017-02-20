package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.OrderRepository;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 30.01.2017.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Override
    public Order save(Order order, int userId, int restaurantId, int... dishesId) {
        return checkNotFoundWithId(repository.save(order,userId,restaurantId,dishesId),order.getId());
    }

    @Override
    public Order update(Order order, int userId, int restaurantId, int... dishesId) {
        return checkNotFoundWithId(repository.save(order,userId,restaurantId,dishesId),order.getId());
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
}
