package ru.agorbunov.restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.agorbunov.restaurant.service.RestaurantService;

/**
 * Created by Admin on 26.02.2017.
 */
@Controller
public class RootController {

    @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
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


}
