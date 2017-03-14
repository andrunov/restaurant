package ru.agorbunov.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.repository.DishRepository;
import ru.agorbunov.restaurant.service.DishService;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 27.01.2017.
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepository repository;

    @Override
    public Dish save(Dish dish, int menuListId) {
        Assert.notNull(dish,"dish must not be null");
        return checkNotFoundWithId(repository.save(dish,menuListId),dish.getId());
    }

    @Override
    public Dish save(Dish dish, int menuListId, int...ordersIds ) {
        Assert.notNull(dish,"dish must not be null");
        return checkNotFoundWithId(repository.save(dish,menuListId,ordersIds),dish.getId());
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public Dish get(int id, int menuListId) {
        return checkNotFoundWithId(repository.get(id,menuListId),id);
    }

    @Override
    public Dish getWithOrders(int id, int menulistId) {
        return checkNotFoundWithId(repository.getWithOrders(id,menulistId),id);
    }

    @Override
    public List<Dish> getByMenuList(int menuListId) {
        return repository.getByMenuList(menuListId);
    }

    @Override
    public List<Dish> getByOrder(int orderId) {
        return repository.getByOrder(orderId);
    }
}
