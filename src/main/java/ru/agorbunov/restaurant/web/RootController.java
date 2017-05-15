package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.*;

/**
 * Created by Admin on 26.02.2017.
 */
@Controller
public class RootController {

//    TODO write logger in methods
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @Autowired
    private MenuListService menuListService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private DishService dishService;

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
        model.addAttribute("description",CurrentEntities.getCurrentMenuList().getDescription());
        model.addAttribute("localDate",CurrentEntities.getCurrentMenuList().getDateTime().toLocalDate().toString());
        return "dishes";
    }

    @GetMapping(value = "/dishes/{id}")
    public String dishes(@PathVariable("id") int id) {
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        CurrentEntities.setCurrentMenuList(menuListService.get(id,restaurant.getId()));
        return "redirect:/dishes";
    }

    @GetMapping(value = "/orders_dishes")
    public String orders_dishes(Model model) {
        model.addAttribute("user",CurrentEntities.getCurrentUser());
        model.addAttribute("restaurant",CurrentEntities.getCurrentRestaurant());
        model.addAttribute("localDate",CurrentEntities.getCurrentOrder()
                                        .getDateTime().toString().replace('T', ' ').substring(0,16));
        return "orders_dishes";
    }

    @GetMapping(value = "/orders_dishes/{id}&{restaurantId}")
    public String orders_dishesByRestaurant(@PathVariable("id") int id,
                                @PathVariable("restaurantId") int restaurantId){
        User user = CurrentEntities.getCurrentUser();
        CurrentEntities.setCurrentRestaurant(restaurantService.get(restaurantId));
        CurrentEntities.setCurrentOrder(orderService.get(id,user.getId(),restaurantId));
        return "redirect:/orders_dishes";
    }

    @GetMapping(value = "/orders_dishes_by_user/{id}&{userId}")
    public String orders_dishesByUser(@PathVariable("id") int id,
                                @PathVariable("userId") int userId){
        User user = userService.get(userId);
        CurrentEntities.setCurrentUser(user);
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        CurrentEntities.setCurrentOrder(orderService.get(id,user.getId(),restaurant.getId()));
        return "redirect:/orders_dishes";
    }


    @GetMapping(value = "/orders_by_dish")
    public String ordersByDish(Model model){
        model.addAttribute("restaurant",CurrentEntities.getCurrentRestaurant());
        model.addAttribute("menuList",CurrentEntities.getCurrentMenuList());
        model.addAttribute("localDate",CurrentEntities.getCurrentMenuList()
                .getDateTime().toString().replace('T', ' ').substring(0,16));
        model.addAttribute("dish",CurrentEntities.getCurrentDish());
        return "orders_by_dish";
    }

    @GetMapping(value = "/orders_by_dish/{id}")
    public String ordersByDish(@PathVariable("id") int id){
        MenuList menuList = CurrentEntities.getCurrentMenuList();
        CurrentEntities.setCurrentDish(dishService.get(id,menuList.getId()));
        return "redirect:/orders_by_dish";
    }

}
