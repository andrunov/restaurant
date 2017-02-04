package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.MenuList;

/**
 * Created by Admin on 30.01.2017.
 */
public interface MenuListService extends BaseService<MenuList> {

    MenuList save(MenuList menuList, int restaurantId);

    MenuList get(int id, int restaurantId);

}
