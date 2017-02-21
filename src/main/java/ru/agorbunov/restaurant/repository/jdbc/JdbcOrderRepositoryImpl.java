package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.OrderRepository;

import java.util.List;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcOrderRepositoryImpl implements OrderRepository {
    @Override
    public Order save(Order order, int userId, int restaurantId, int... dishIds) {
        return null;
    }

    @Override
    public Order get(int id, int userId, int restaurantId) {
        return null;
    }

    @Override
    public Order getWith(int id, int userId, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
