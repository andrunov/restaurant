package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.Restaurant;

/**
 * Created by Admin on 23.02.2017.
 */
public interface RestaurantService extends BaseService<Restaurant> {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    Restaurant getWithMenuLists(int id);

    void evictCache();

}
