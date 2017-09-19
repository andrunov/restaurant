package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.*;
import ru.agorbunov.restaurant.service.OrderService;
import ru.agorbunov.restaurant.service.UserService;
import ru.agorbunov.restaurant.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest controller for order_by_dish.jsp
 * to exchange order data with service-layer
 */
@RestController
@RequestMapping(value = "/ajax/order_by_dish")
public class OrderByDishAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    /*get orders by current dish*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByDish() {
        log.info("getByDish");
        Dish dish = CurrentEntities.getCurrentDish();
        return orderService.getByDish(dish.getId());
    }

    /*get orders by current dish*/
    @GetMapping(value = "/filterByStatus/{statusKey}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByDishAndStatus(@PathVariable("statusKey") String statusKey) {
        log.info("getByDishAndStatus");
        Dish dish = CurrentEntities.getCurrentDish();
        return orderService.getByDishAndStatus(dish.getId(),statusKey);
    }

    /*get order by Id of user by userId and of current restaurant */
    @GetMapping(value = "/{id}&{userId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(@PathVariable("id") int id,
                          @PathVariable("userId") int userId){
        log.info("get " + id);
        User user = userService.get(userId);
        CurrentEntities.setCurrentUser(user);
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        Order order = orderService.get(id, user.getId(),restaurant.getId());
        CurrentEntities.setCurrentOrder(order);
        return order;
    }

    /*update order, updates only DateTime and Status, dishes not touch*/
    @PostMapping
    public void update(@RequestParam("dateTime")@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime dateTime,
                       @RequestParam("status") String status){
        User user = CurrentEntities.getCurrentUser();
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        Order order = CurrentEntities.getCurrentOrder();
        order.setDateTime(dateTime);
        order.setStatus(Status.valueOf(status));
        log.info("update " + order);
        orderService.save(order,user.getId(),restaurant.getId());
    }

    /*delete order by Id*/
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        orderService.delete(id);
    }

}
