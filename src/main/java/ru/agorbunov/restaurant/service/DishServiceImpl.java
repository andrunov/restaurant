package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.repository.DishRepository;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFound;
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
        return checkNotFoundWithId(repository.save(dish,menuListId,ordersIds),menuListId);
    }

    @Override
    public Dish update(Dish dish, int menulistId, int... ordersIds) {
        return checkNotFoundWithId(repository.save(dish,menulistId,ordersIds),menulistId);
    }

    @Override
    public void delete(int id) {
        checkNotFound(repository.delete(id),"dish not found");
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public Dish get(int id, int menuListId) {
        return checkNotFoundWithId(repository.get(id,menuListId),menuListId);
    }
}
