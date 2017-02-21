package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.repository.DishRepository;

import java.util.List;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcDishRepositoryImpl implements DishRepository {
    @Override
    public Dish save(Dish dish, int menulistId, int... ordersIds) {
        return null;
    }

    @Override
    public Dish get(int id, int menuListId) {
        return null;
    }

    @Override
    public Dish getWith(int id, int menuListId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Dish> getAll() {
        return null;
    }
}
