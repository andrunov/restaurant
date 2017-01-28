package ru.agorbunov.restaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.repository.BaseRepository;

import java.util.List;

/**
 * Created by Admin on 28.01.2017.
 */
@Service
public class UserService implements BaseService<User> {

    @Autowired
    private BaseRepository<User> repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }
}
