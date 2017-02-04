package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.repository.UserAndRestaurantRepository;

import java.util.List;

/**
 * Created by Admin on 29.01.2017.
 */
@Service
public class RestaurantServiceImpl implements UserAndRestaurantService<Restaurant> {

    @Autowired
    private UserAndRestaurantRepository<Restaurant> repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant get(int id) {
        return repository.get(id);
    }
}
