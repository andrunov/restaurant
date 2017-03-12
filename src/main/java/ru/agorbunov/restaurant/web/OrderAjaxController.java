package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.OrderService;
import ru.agorbunov.restaurant.service.RestaurantService;
import ru.agorbunov.restaurant.util.DateTimeUtil;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.time.LocalDateTime;
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

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/{id}&{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Order menuList(@PathVariable("id") int id,
                          @PathVariable("restaurantId") int restaurantId){
        log.info("get " + id);
        User currentUser = CurrentEntities.getCurrentUser();
        CurrentEntities.setCurrentRestaurant(restaurantService.get(restaurantId));
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

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("dateTime")@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime dateTime){
        User currentUser = CurrentEntities.getCurrentUser();
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        Order order = new Order(currentUser,currentRestaurant,dateTime);
        order.setId(id);
        checkEmpty(order);
        if (order.isNew()) {
            ValidationUtil.checkNew(order);
            log.info("create " + order);
            service.save(order,currentUser.getId(),currentRestaurant.getId());
        } else {
            log.info("update " + order);
            service.save(order,currentUser.getId(),currentRestaurant.getId());
        }
    }

    private void checkEmpty(Order order){
        ValidationUtil.checkEmpty(order.getDateTime(),"dateTime");
    }
}
