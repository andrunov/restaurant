package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.service.OrderService;

import java.util.List;

/**
 * Created by Admin on 17.04.2017.
 */
@RestController
@RequestMapping(value = "/ajax/order_by_dish")
public class OrderByDishAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Order> getByDish() {
        log.info("getByDish");
        Dish dish = CurrentEntities.getCurrentDish();
        return service.getByDish(dish.getId());
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }


}
