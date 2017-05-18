package ru.agorbunov.restaurant.service;

import ru.agorbunov.restaurant.model.User;

/**
 * Interface for User-service
 */
public interface UserService extends BaseService<User> {

    /*save user*/
    User save(User user);

    /*get user by Id*/
    User get(int id);

    /*get user by Id with collection of
    *orders were made by the user*/
    User getWithOrders(int id);

    /*evict service-layer cash of user-entities*/
    void evictCache();
}
