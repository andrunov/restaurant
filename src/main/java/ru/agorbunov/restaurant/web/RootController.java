package ru.agorbunov.restaurant.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Admin on 26.02.2017.
 */
@Controller
public class RootController {


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


}
