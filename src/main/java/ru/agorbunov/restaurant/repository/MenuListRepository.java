package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.MenuList;

import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
public interface MenuListRepository extends BaseRepository<MenuList>{

    MenuList save(MenuList menuList, int restaurantId);

    MenuList get(int id, int restaurantId);

    MenuList getWith(int id, int restaurantId);

    List<MenuList> getByRestaurant(int restaurantId);

}
