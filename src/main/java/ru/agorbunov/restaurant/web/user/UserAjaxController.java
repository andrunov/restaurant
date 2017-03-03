package ru.agorbunov.restaurant.web.user;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;

import java.util.List;

/**
 * Created by Admin on 28.02.2017.
 */
@RestController
@RequestMapping(value = "/ajax/admin/users")
public class UserAjaxController extends UserAbstractController {

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }


    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, email, password, null, Role.USER);
        if (user.isNew()) {
            super.create(user);
        } else {
            super.update(user, id);
        }
    }
}
