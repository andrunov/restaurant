package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.service.RestaurantService;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.util.List;

/**
 * Created by Admin on 01.03.2017.
 */
@RestController
@RequestMapping(value =  "/ajax/restaurants")
public class RestaurantAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getRestaurant(@PathVariable("id") int id) {
        log.info("get " + id);
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("address") String address) {
        Restaurant restaurant = new Restaurant(name, address);
        restaurant.setId(id);
        checkEmpty(restaurant);
        if (restaurant.isNew()) {
            ValidationUtil.checkNew(restaurant);
            log.info("create " + restaurant);
            service.save(restaurant);
        } else {
            log.info("update " + restaurant);
            service.save(restaurant);
        }
    }

    private void checkEmpty(Restaurant restaurant){
        ValidationUtil.checkEmpty(restaurant.getName(),"name");
        ValidationUtil.checkEmpty(restaurant.getAddress(),"address");
    }


}
