package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.repository.BaseRepository;

import java.util.List;

/**
 * Created by Admin on 27.01.2017.
 */
@Service
public class DishServiceImpl implements BaseService<Dish> {

    @Autowired
    private BaseRepository<Dish> repository;

    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
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
    public Dish get(int id) {
        return repository.get(id);
    }
}
