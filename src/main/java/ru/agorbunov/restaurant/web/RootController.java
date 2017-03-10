package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.agorbunov.restaurant.service.RestaurantService;

/**
 * Created by Admin on 26.02.2017.
 */
@Controller
public class RootController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value = "/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/restaurants")
    public String restaurants() {
        return "restaurants";
    }

    @GetMapping(value = "/menuLists")
    public String menuLists() {
        return "menuLists";
    }

    @GetMapping(value = "/menuLists/{id}")
    public String menuLists(@PathVariable("id") int id) {
        CurrentEntities.setCurrentRestaurant(restaurantService.get(id));
        return "redirect:/menuLists";
    }


}
