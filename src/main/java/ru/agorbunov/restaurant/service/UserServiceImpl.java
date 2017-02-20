package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.repository.UserAndRestaurantRepository;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFound;
import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 28.01.2017.
 */
@Service
public class UserServiceImpl implements UserAndRestaurantService<User> {

    @Autowired
    private UserAndRestaurantRepository<User> repository;

    @Override
    public User save(User user) {
        checkNotFound(user,"user must not be null");
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        checkNotFound(user,"user must not be null");
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }


}
