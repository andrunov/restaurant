package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.repository.MenuListRepository;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFound;
import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 30.01.2017.
 */
@Service
public class MenuListServiceImpl implements MenuListService {

    @Autowired
    private MenuListRepository repository;

    @Override
    public MenuList save(MenuList menuList, int restaurantId) {
        return checkNotFoundWithId(repository.save(menuList,restaurantId),restaurantId);
    }

    @Override
    public MenuList update(MenuList menuList, int restaurantId) {
        return checkNotFoundWithId(repository.save(menuList,restaurantId),restaurantId);
    }

    @Override
    public void delete(int id) {
        checkNotFound(repository.delete(id),"menu list noy found");
    }

    @Override
    public List<MenuList> getAll() {
        return repository.getAll();
    }

    @Override
    public MenuList get(int id, int restaurantId) {
        return checkNotFoundWithId(repository.get(id, restaurantId),restaurantId);
    }
}
