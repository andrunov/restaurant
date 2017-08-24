package ru.agorbunov.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.OrderRepository;
import ru.agorbunov.restaurant.service.OrderService;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkAcceptableUpdate;
import static ru.agorbunov.restaurant.util.ValidationUtil.checkArrCompatibility;
import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Order-service
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    /*save order if it is new entity and update if it is exist,
    *,int[] dishIds - Ids of dishes, int[] dishQuantityValues - dishes quantities,
    * each dishId from first arr matches its quantity from second arr, arrays must have equal size
    *userId and restaurantId in parameters is Ids of user and restaurant to which the order is belong
    * check that order not null, check that arrays have equals size
    * and check that order was found (order belongs to these user and restaurant*/
    @Override
    public Order save(Order order, int userId, int restaurantId, int[] dishIds, int[] dishQuantityValues) {
        Assert.notNull(order,"order must not be null");
        checkArrCompatibility(dishIds,dishQuantityValues);
        checkAcceptableUpdate(order);
        return checkNotFoundWithId(repository.save(order,userId,restaurantId,dishIds,dishQuantityValues),order.getId());
    }

    /*save order if it is new entity and update if it is exist,
    * userId and restaurantId in parameters is Ids of user and restaurant to which the order is belong,
    * if order is already exist and have collections of dishes they remain and not touch
    * check that order not null and check that order was found (order belongs to these user and restaurant*/
    @Override
    public Order save(Order order, int userId, int restaurantId) {
        Assert.notNull(order,"order must not be null");
        return checkNotFoundWithId(repository.save(order,userId,restaurantId),order.getId());
    }

    /*delete order by Id, check that order was found (order belongs to these user and restaurant */
    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    /*get all orders*/
    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    /*get order by Id, userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong,
    *check that order was found (order belongs to these user and restaurant*/
    @Override
    public Order get(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(repository.get(id,userId,restaurantId),id);
    }

    /*get order by Id with collections of dishes which the order is have ,
    *userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong,
    *check that order was found (order belongs to these user and restaurant*/
    @Override
    public Order getWithDishes(int id, int userId, int restaurantId) {
        return checkNotFoundWithId(repository.getWithDishes(id,userId,restaurantId),id);
    }

    /*get all orders that belongs to user with Id pass as parameter */
    @Override
    public List<Order> getByUser(int userId) {
        return repository.getByUser(userId);
    }

    /*get all orders that belongs to user with Id pass as 1st parameter
    * and with status pass as 2nd parameter */
    @Override
    public List<Order> getByUserAndStatus(int userId, String status) {
        return repository.getByUserAndStatus(userId,status);
    }

    /*get all orders that belongs to dish with Id pass as parameter */
    @Override
    public List<Order> getByDish(int dishId) {
        return repository.getByDish(dishId);
    }

    /*delete order by Id, userId and restaurantId in parameters is Ids of
   *user and restaurant to which the order is belong, check that updates acceptable*/
    @Override
    public void delete(int id, int userId, int restaurantId) {
        Order order = get(id,userId,restaurantId);
        checkAcceptableUpdate(order);
        delete(id);
    }
}
