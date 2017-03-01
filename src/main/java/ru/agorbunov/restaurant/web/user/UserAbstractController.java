package ru.agorbunov.restaurant.web.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.UserService;

import java.util.List;

/**
 * Created by Admin on 28.02.2017.
 */
public abstract class UserAbstractController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public User create(User user) {
//        checkNew(user);
        log.info("create " + user);
        return service.save(user);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(User user, int id) {
//        checkIdConsistent(user, id);
        log.info("update " + user);
        service.save(user);
    }

}
