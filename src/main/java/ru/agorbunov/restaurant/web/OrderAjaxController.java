package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.OrderService;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.util.List;

/**
 * Created by Admin on 08.03.2017.
 */
@RestController
@RequestMapping(value = "/ajax/orders")
public class OrderAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Order get(@PathVariable("id") int id,
                     @PathVariable("restaurantId") int restaurantId) {
        log.info("get " + id);
        User currentUser = CurrentEntities.getCurrentUser();
        return service.get(id, currentUser.getId(),restaurantId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByUser() {
        log.info("getByUser");
        User currentUser = CurrentEntities.getCurrentUser();
        return service.getByUser(currentUser.getId());
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

//    @PostMapping
//    public void createOrUpdate(@RequestParam("id") Integer id,
//                               @RequestParam("user") String name,
//                               @RequestParam("email") String email,
//                               @RequestParam("password") String password) {
//        Order order = new Order();
//        User user = new User(name, email, password, Role.USER);
//        user.setId(id);
//        checkEmpty(user);
//        if (user.isNew()) {
//            ValidationUtil.checkNew(user);
//            log.info("create " + user);
//            service.save(user);
//        } else {
//            log.info("update " + user);
//            service.save(user);
//        }
//    }

    private void checkEmpty(User user){
        ValidationUtil.checkEmpty(user.getName(),"name");
        ValidationUtil.checkEmpty(user.getEmail(),"email");
        ValidationUtil.checkEmpty(user.getPassword(),"password");
    }
}
