package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.UserService;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.util.List;

/**
 * Created by Admin on 28.02.2017.
 */
@RestController
@RequestMapping(value = "/ajax/admin/users")
public class UserAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable("id") int id) {
        log.info("get " + id);
        return service.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        User user = new User(name, email, password, Role.USER);
        user.setId(id);
        checkEmpty(user);
        if (user.isNew()) {
            ValidationUtil.checkNew(user);
            log.info("create " + user);
            service.save(user);
        } else {
            log.info("update " + user);
            service.save(user);
        }
    }

    private void checkEmpty(User user){
        ValidationUtil.checkEmpty(user.getName(),"name");
        ValidationUtil.checkEmpty(user.getEmail(),"email");
        ValidationUtil.checkEmpty(user.getPassword(),"password");
    }
}
