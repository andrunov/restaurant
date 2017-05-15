package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.Status;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.OrderService;
import ru.agorbunov.restaurant.service.RestaurantService;
import ru.agorbunov.restaurant.util.DateTimeUtil;
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
        User user = CurrentEntities.getCurrentUser();
        CurrentEntities.setCurrentRestaurant(restaurantService.get(restaurantId));
        Order order = orderService.get(id, user.getId(),restaurantId);
        CurrentEntities.setCurrentOrder(order);
        return order;
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

    @PostMapping(value = "/create")
    public void create(@RequestParam("dishIds")String[] dishIds ){
        int[] intDishesIds = Arrays.stream(dishIds).mapToInt(Integer::parseInt).toArray();
//        set dishes quantities as 1 default values, will be changed longer
        int[] intDishQuantityValues = new int[intDishesIds.length];
        for (int i = 0; i < intDishQuantityValues.length; i++){
            intDishQuantityValues[i] = 1;
        }
        User currentUser = CurrentEntities.getCurrentUser();
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        LocalDateTime dateTime = LocalDateTime.now();
        Order order = new Order(currentUser,currentRestaurant, dateTime);
        CurrentEntities.setCurrentOrder(order);
        checkEmpty(order);
        if (order.isNew()) {
            ValidationUtil.checkNew(order);
            log.info("create " + order);
            orderService.save(order,currentUser.getId(),currentRestaurant.getId(),intDishesIds,intDishQuantityValues);
        }
    }

    @PostMapping
    public void update(@RequestParam("dateTime")@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime dateTime,
                       @RequestParam("status") String status){
        User user = CurrentEntities.getCurrentUser();
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        Order order = CurrentEntities.getCurrentOrder();
        checkEmpty(order);
        order.setDateTime(dateTime);
        order.setStatus(Status.valueOf(status));
        log.info("update " + order);
        orderService.save(order,user.getId(),restaurant.getId());
    }

    @PostMapping(value = "/update")
    public void update(@RequestParam("dishIds")String[] dishIds,
                       @RequestParam("dishQuantityValues")String[] dishQuantityValues){
        int[] intDishesIds = Arrays.stream(dishIds).mapToInt(Integer::parseInt).toArray();
        int[] intDishQuantityValues = Arrays.stream(dishQuantityValues).mapToInt(Integer::parseInt).toArray();
        User currentUser = CurrentEntities.getCurrentUser();
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        Order order = CurrentEntities.getCurrentOrder();
        checkEmpty(order);
        log.info("update " + order);
        orderService.save(order,currentUser.getId(),currentRestaurant.getId(),intDishesIds,intDishQuantityValues);
    }

    private void checkEmpty(Order order){
        ValidationUtil.checkEmpty(order.getDateTime(),"dateTime");
    }
}
