package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.repository.ReferenseRepository;

import java.util.List;

/**
 * Created by Admin on 27.01.2017.
 */
@Service
public class DishServiceImpl implements ReferenseService<Dish> {

    @Autowired
    private ReferenseRepository<Dish> repository;

    @Override
    public Dish save(Dish dish, int menuListId) {
        return repository.save(dish,menuListId);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @Override
    public Dish get(int id, int menuListId) {
        return repository.get(id,menuListId);
    }
}
