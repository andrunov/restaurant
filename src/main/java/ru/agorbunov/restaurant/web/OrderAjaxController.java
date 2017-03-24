package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.OrderService;
import ru.agorbunov.restaurant.service.RestaurantService;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 08.03.2017.
 */
@RestController
@RequestMapping(value = "/ajax/orders")
public class OrderAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/{id}&{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(@PathVariable("id") int id,
                          @PathVariable("restaurantId") int restaurantId){
        log.info("get " + id);
        User currentUser = CurrentEntities.getCurrentUser();
        CurrentEntities.setCurrentRestaurant(restaurantService.get(restaurantId));
        return orderService.get(id, currentUser.getId(),restaurantId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByUser() {
        log.info("getByUser");
        User currentUser = CurrentEntities.getCurrentUser();
        return orderService.getByUser(currentUser.getId());
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        orderService.delete(id);
    }

//    @PostMapping
//    public void update(@RequestParam("id") Integer id,
//                                @RequestParam("dateTime")@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime dateTime){
//        User currentUser = CurrentEntities.getCurrentUser();
//        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
//        Order order = new Order(currentUser,currentRestaurant, dateTime);
//        order.setId(id);
//        checkEmpty(order);
//        if (order.isNew()) {
//            ValidationUtil.checkNew(order);
//            log.info("create " + order);
//            orderService.save(order,currentUser.getId(),currentRestaurant.getId());
//        } else {
//            log.info("update " + order);
//            orderService.save(order,currentUser.getId(),currentRestaurant.getId());
//        }
//    }

    @PostMapping
    public void create(@RequestParam("dishIds")String[] dishIds ){
        int[] dishesIds = Arrays.stream(dishIds).mapToInt(Integer::parseInt).toArray();
        User currentUser = CurrentEntities.getCurrentUser();
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order(currentUser,currentRestaurant, dateTime);
        checkEmpty(order);
        if (order.isNew()) {
            ValidationUtil.checkNew(order);
            log.info("create " + order);
//            orderService.save(order,currentUser.getId(),currentRestaurant.getId(),dishesIds);
        }
    }

    private void checkEmpty(Order order){
        ValidationUtil.checkEmpty(order.getDateTime(),"dateTime");
    }
}
