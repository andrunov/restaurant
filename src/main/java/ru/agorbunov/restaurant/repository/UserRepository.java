package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.User;

/**
 * Created by Admin on 23.02.2017.
 */
public interface UserRepository extends BaseRepository<User> {

    User save(User user);

    User get(int id);

    User getWithOrders(int id);
}
