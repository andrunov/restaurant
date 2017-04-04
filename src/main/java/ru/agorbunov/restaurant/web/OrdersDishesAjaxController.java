package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.service.DishService;
import ru.agorbunov.restaurant.to.DishTo;

import java.util.List;

/**
 * Created by Admin on 14.03.2017.
 */
@RestController
@RequestMapping(value = "/ajax/orders_dishes")
public class OrdersDishesAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private DishService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishTo> getByOrder() {
        log.info("getByOrder");
        Order order = CurrentEntities.getCurrentOrder();
        return DishTo.toList(service.getByOrder(order.getId()));
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

}
