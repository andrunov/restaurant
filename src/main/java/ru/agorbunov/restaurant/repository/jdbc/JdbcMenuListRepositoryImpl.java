package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.repository.MenuListRepository;

import java.util.List;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcMenuListRepositoryImpl implements MenuListRepository {
    @Override
    public MenuList save(MenuList menuList, int restaurantId) {
        return null;
    }

    @Override
    public MenuList get(int id, int restaurantId) {
        return null;
    }

    @Override
    public MenuList getWith(int id, int restaurantId) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<MenuList> getAll() {
        return null;
    }
}
