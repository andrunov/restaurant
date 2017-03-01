package ru.agorbunov.restaurant.web.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.UserService;

import java.util.Collections;
import java.util.List;

/**
 * Created by Admin on 28.02.2017.
 */
@RestController
@RequestMapping(value = "/ajax")
public class UserAjaxController  {

    @Autowired
    private UserService service;

    @GetMapping("/ajax")
    public List<User> getAll() {
//        return service.getAll();
        return Collections.singletonList(new User(01,"sd","sdf","123",null,null));
    }




    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }


    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {

        User user = new User(id, name, email, password, null, Role.USER);
//        if (user.isNew()) {
            service.save(user);
//        } else {
//            service.update(user, id);
//        }
    }
}
