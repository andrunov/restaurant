package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.repository.MenuListRepository;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
@Service
public class MenuListServiceImpl implements MenuListService {

    @Autowired
    private MenuListRepository repository;

    @Override
    public MenuList save(MenuList menuList, int restaurantId) {
        return repository.save(menuList,restaurantId);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<MenuList> getAll() {
        return repository.getAll();
    }

    @Override
    public MenuList get(int id, int restaurantId) {
        return repository.get(id, restaurantId);
    }
}
