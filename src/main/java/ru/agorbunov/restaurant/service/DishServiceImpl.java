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
        checkNotFound(dish,"dish must not be null");
        return repository.save(dish,menuListId,ordersIds);
    }

    @Override
    public Dish update(Dish dish, int menulistId, int... ordersIds) {
        return checkNotFound(repository.save(dish,menulistId,ordersIds),"dish in that menu list not found");
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
