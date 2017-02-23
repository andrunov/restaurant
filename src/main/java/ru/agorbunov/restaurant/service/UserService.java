package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.User;

/**
 * Created by Admin on 23.02.2017.
 */
public interface UserService extends BaseService<User> {

    User save(User user);

    User get(int id);

    User getWith(int id);

    void evictCache();
}
