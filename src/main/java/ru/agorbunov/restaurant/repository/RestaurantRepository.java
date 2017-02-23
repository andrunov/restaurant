package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Restaurant;

/**
 * Created by Admin on 23.02.2017.
 */
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    Restaurant save(Restaurant restaurant);

    Restaurant get(int id);

    Restaurant getWith(int id);
}
