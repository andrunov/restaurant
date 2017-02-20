package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.repository.UserAndRestaurantRepository;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 29.01.2017.
 */
@Service
public class RestaurantServiceImpl implements UserAndRestaurantService<Restaurant> {

    @Autowired
    private UserAndRestaurantRepository<Restaurant> repository;

    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant,"restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        Assert.notNull(restaurant,"restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Override
    public Restaurant get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    @Override
    public Restaurant getWith(int id) {
        return checkNotFoundWithId(repository.getWith(id),id);
    }
}
