package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.repository.DishRepository;

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
    public Dish save(Dish dish, int menuListId, int...ordersIds ) {
        return checkNotFoundWithId(repository.save(dish,menuListId,ordersIds),dish.getId());
    }

    @Override
    public Dish update(Dish dish, int menulistId, int... ordersIds) {
        return checkNotFoundWithId(repository.save(dish,menulistId,ordersIds),dish.getId());
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
}
