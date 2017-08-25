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
 * Rest controller for orders.jsp
 * to exchange order data with service-layer
 */
@RestController
@RequestMapping(value = "/ajax/orders")
public class OrderAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;

    @Autowired
    private RestaurantService restaurantService;

    /*get order by Id of restaurant by restaurantId and of current user */
    @GetMapping(value = "/{id}&{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Order getOrder(@PathVariable("id") int id,
                          @PathVariable("restaurantId") int restaurantId){
        log.info("get " + id + ", " + restaurantId );
        User user = CurrentEntities.getCurrentUser();
        CurrentEntities.setCurrentRestaurant(restaurantService.get(restaurantId));
        Order order = orderService.get(id, user.getId(),restaurantId);
        CurrentEntities.setCurrentOrder(order);
        return order;
    }

    /*get all orders by current user*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByUser() {
        log.info("getByUser");
        User currentUser = CurrentEntities.getCurrentUser();
        return orderService.getByUser(currentUser.getId());
    }

    /*get all orders by current user*/
    @GetMapping(value = "/filterByStatus/{statusKey}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByUserWithFilter(@PathVariable("statusKey") String statusKey) {
        log.info("getByUser");
        User currentUser = CurrentEntities.getCurrentUser();
        return orderService.getByUserAndStatus(currentUser.getId(),statusKey);
    }

    /*delete order by Id*/
    @DeleteMapping(value = "/{id}&{restaurantId}")
    public void delete(@PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        log.info("delete " + id + ", " + restaurantId );
        User user = CurrentEntities.getCurrentUser();
        orderService.delete(id, user.getId(), restaurantId);
    }

    /*create new order, in request parameters send only array of dishes Ids,
    * dishes quantities sets as1 by default, change quantities will be offer to user in next steps*/
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

    /*update order, updates only DateTime and Status, dishes not touch*/
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

    /*update order, update dishes and their quantities, DateTime and Status not touch*/
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

    /*check order for empty fields*/
    private void checkEmpty(Order order){
        ValidationUtil.checkEmpty(order.getDateTime(),"dateTime");
    }
}
