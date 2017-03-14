package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.MenuList;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface MenuListService extends BaseService<MenuList> {

    MenuList save(MenuList menuList, int restaurantId);

    MenuList get(int id, int restaurantId);

    MenuList getWithDishes(int id, int restaurantId);

    List<MenuList> getByRestaurant(int restaurantId);

}
