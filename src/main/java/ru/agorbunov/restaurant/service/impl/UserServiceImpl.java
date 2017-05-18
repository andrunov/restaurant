package ru.agorbunov.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.repository.UserRepository;
import ru.agorbunov.restaurant.service.UserService;

import java.util.List;

import static ru.agorbunov.restaurant.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Admin on 28.01.2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    /*save user, check that user not noll*/
    @CacheEvict(value = "users",allEntries = true)
    @Override
    public User save(User user) {
        Assert.notNull(user,"user must not be null");
        return repository.save(user);
    }

    /*delete user by Id, check that user was found */
    @CacheEvict(value = "users",allEntries = true)
    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id),id);
    }

    /*get all users*/
    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    /*get user by Id, check that user was found*/
    @Override
    public User get(int id) {
        return checkNotFoundWithId(repository.get(id),id);
    }

    /*get user by Id with collection of orders were made by the user,
    * check that user was found*/
    @Override
    public User getWithOrders(int id) {
        return checkNotFoundWithId(repository.getWithOrders(id),id);
    }

    /*evict service-layer cash of user-entities*/
    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }

}
