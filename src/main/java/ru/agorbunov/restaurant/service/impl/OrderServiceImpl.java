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
        int[][] copyValues = removeNullValues(dishIds,dishQuantityValues);
        dishIds = copyValues[0];
        dishQuantityValues = copyValues[1];
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

    /*remove dishes vith quantity==0 in order*/
    public static int[][] removeNullValues(int[] dishIds, int[]dishQuantityValues){
        int newCapacity = 0;
        for (int value : dishQuantityValues ){
            if (value > 0) newCapacity++;
        }
        int[] copyDishIds = new int[newCapacity];
        int[] copyDishQuantityValues = new int[newCapacity];
        int counter = 0;
        for (int i = 0; i < dishQuantityValues.length; i++){
            if (dishQuantityValues[i]>0){
                copyDishIds[counter] = dishIds[i];
                copyDishQuantityValues[counter] = dishQuantityValues[i];
                counter++;
            }
        }
        return new int[][]{copyDishIds,copyDishQuantityValues};
    }
}
