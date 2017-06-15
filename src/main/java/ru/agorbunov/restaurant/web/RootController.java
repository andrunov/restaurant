package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.service.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Root-controller. Map root requests
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

    /*return admin_home.jsp and display home page*/
    @GetMapping(value = "/")
    public String root() {
        log.info("get /");
        return "redirect:/home";
    }

    // TODO: 23.05.2017 rewrite method with loggedUser
    /*if request have errors put them into model and return back to login.jsp*/
    @GetMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

    /*return home page according to user role*/
    @GetMapping(value = "/home")
    public String welcome(HttpServletRequest request){
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "admin_home";
        }
        return "user_home";
    }

    /*return users.jsp and display all users*/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String users() {
        log.info("get /users");
        return "users";
    }

    /*return restaurants.jsp and display all restaurants*/
    @GetMapping(value = "/restaurants")
    public String restaurants() {
        log.info("get /restaurants");
        return "restaurants";
    }

    /*get id parameter to set current restaurant and redirect to menuLists.jsp*/
    @GetMapping(value = "/menuLists/{id}")
    public String menuLists(@PathVariable("id") int id) {
        log.info("get /menuLists/{id}");
        CurrentEntities.setCurrentRestaurant(restaurantService.get(id));
        return "redirect:/menuLists";
    }

    /*return menuLists.jsp and display menu lists of current restaurant*/
    @GetMapping(value = "/menuLists")
    public String menuLists(Model model) {
        log.info("get /menuLists");
        model.addAttribute(CurrentEntities.getCurrentRestaurant());
        return "menuLists";
    }

    /*get id parameter to set current user and redirect to orders.jsp*/
    @GetMapping(value = "/orders/{id}")
    public String orders(@PathVariable("id") int id) {
        log.info("get /orders/{id}");
        CurrentEntities.setCurrentUser(userService.get(id));
        return "redirect:/orders";
    }

    /*return orders.jsp and display orders of current user*/
    @GetMapping(value = "/orders")
    public String orders(Model model) {
        log.info("get /orders");
        model.addAttribute(CurrentEntities.getCurrentUser());
        return "orders";
    }

    /*get id parameter to set current menuList and redirect to dishes.jsp*/
    @GetMapping(value = "/dishes/{id}")
    public String dishes(@PathVariable("id") int id) {
        log.info("get /dishes/{id}");
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        CurrentEntities.setCurrentMenuList(menuListService.get(id,restaurant.getId()));
        return "redirect:/dishes";
    }

    /*return dishes.jsp and display dishes of current menu list of current restaurant*/
    @GetMapping(value = "/dishes")
    public String dishes(Model model) {
        log.info("get /dishes");
        model.addAttribute("restaurant",CurrentEntities.getCurrentRestaurant());
        model.addAttribute("description",CurrentEntities.getCurrentMenuList().getDescription());
        model.addAttribute("localDate",CurrentEntities.getCurrentMenuList().getDateTime().toLocalDate().toString());
        return "dishes";
    }

    /*get id parameter to set current order, get restaurantId parameter to set current restaurant
    * and redirect to orders_dishes.jsp*/
    @GetMapping(value = "/orders_dishes/{id}&{restaurantId}")
    public String orders_dishesByRestaurant(@PathVariable("id") int id,
                                @PathVariable("restaurantId") int restaurantId){
        log.info("get /orders_dishes/{id}&{restaurantId}");
        User user = CurrentEntities.getCurrentUser();
        CurrentEntities.setCurrentRestaurant(restaurantService.get(restaurantId));
        CurrentEntities.setCurrentOrder(orderService.get(id,user.getId(),restaurantId));
        return "redirect:/orders_dishes";
    }

    /*get userId parameter to set current user and id parameter to set current order
    * and redirect to orders_dishes.jsp*/
    @GetMapping(value = "/orders_dishes_by_user/{id}&{userId}")
    public String orders_dishesByUser(@PathVariable("id") int id,
                                @PathVariable("userId") int userId){
        log.info("get /orders_dishes_by_user/{id}&{userId}");
        User user = userService.get(userId);
        CurrentEntities.setCurrentUser(user);
        Restaurant restaurant = CurrentEntities.getCurrentRestaurant();
        CurrentEntities.setCurrentOrder(orderService.get(id,user.getId(),restaurant.getId()));
        return "redirect:/orders_dishes";
    }

    /*return orders_dishes.jsp and display dishes of current order of current user and current restaurant*/
    @GetMapping(value = "/orders_dishes")
    public String orders_dishes(Model model) {
        log.info("get /orders_dishes");
        model.addAttribute("user",CurrentEntities.getCurrentUser());
        model.addAttribute("restaurant",CurrentEntities.getCurrentRestaurant());
        model.addAttribute("localDate",CurrentEntities.getCurrentOrder()
                .getDateTime().toString().replace('T', ' ').substring(0,16));
        return "orders_dishes";
    }

    /*get id parameter to set current dish and redirect to orders_by_dish.jsp*/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/orders_by_dish/{id}")
    public String ordersByDish(@PathVariable("id") int id){
        log.info("get /orders_by_dish/{id}");
        MenuList menuList = CurrentEntities.getCurrentMenuList();
        CurrentEntities.setCurrentDish(dishService.get(id,menuList.getId()));
        return "redirect:/orders_by_dish";
    }

    /*return orders_by_dish.jsp and display orders of current dish of current menu List of current restaurant*/
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/orders_by_dish")
    public String ordersByDish(Model model){
        log.info("get /orders_by_dish");
        model.addAttribute("restaurant",CurrentEntities.getCurrentRestaurant());
        model.addAttribute("menuList",CurrentEntities.getCurrentMenuList());
        model.addAttribute("localDate",CurrentEntities.getCurrentMenuList()
                .getDateTime().toString().replace('T', ' ').substring(0,16));
        model.addAttribute("dish",CurrentEntities.getCurrentDish());
        return "orders_by_dish";
    }

}
