package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.service.MenuListService;
import ru.agorbunov.restaurant.service.RestaurantService;
import ru.agorbunov.restaurant.service.UserService;

/**
 * Created by Admin on 26.02.2017.
 */
@Controller
public class RootController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuListService menuListService;

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
    public String menuLists(Model model) {
        model.addAttribute(CurrentEntities.getCurrentRestaurant());
        return "menuLists";
    }

    @GetMapping(value = "/menuLists/{id}")
    public String menuLists(@PathVariable("id") int id) {
        CurrentEntities.setCurrentRestaurant(restaurantService.get(id));
        return "redirect:/menuLists";
    }

    @GetMapping(value = "/orders")
    public String orders(Model model) {
        model.addAttribute(CurrentEntities.getCurrentUser());
        return "orders";
    }

    @GetMapping(value = "/orders/{id}")
    public String orders(@PathVariable("id") int id) {
        CurrentEntities.setCurrentUser(userService.get(id));
        return "redirect:/orders";
    }

    @GetMapping(value = "/dishes")
    public String dishes(Model model) {
        model.addAttribute("restaurant",CurrentEntities.getCurrentRestaurant());
        model.addAttribute("localDate",CurrentEntities.getCurrentMenuList().getDateTime().toLocalDate().toString());
        return "dishes";
    }

    @GetMapping(value = "/dishes/{id}")
    public String dishes(@PathVariable("id") int id) {
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        CurrentEntities.setCurrentMenuList(menuListService.get(id,restaurant.getId()));
        return "redirect:/dishes";
    }


}
