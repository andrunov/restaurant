package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.service.DishService;
import ru.agorbunov.restaurant.service.MenuListService;
import ru.agorbunov.restaurant.util.DateTimeUtil;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.util.List;

/**
 * Rest controller for works with dishes.jsp
 * to exchange dish data with service-layer
 */
@RestController
@RequestMapping(value =  "/ajax/dishes")
public class DishesAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    @Autowired
    private MenuListService menuListService;

    /*get dishes by current menu list*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByMenuList() {
        log.info("getByMenuList");
        MenuList currentMenuList = CurrentEntities.getCurrentMenuList();
        return service.getByMenuList(currentMenuList.getId());
    }

    /*get dishes by menu list Id pass as parameter*/
    @GetMapping(value = "byMenuList/{menuListId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByMenuListId(@PathVariable("menuListId") int menuListId) {
        log.info("getByMenuList");
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        MenuList currentMenuList = menuListService.get(menuListId,currentRestaurant.getId());
        CurrentEntities.setCurrentMenuList(currentMenuList);
        return service.getByMenuList(menuListId);
    }

    /*get dishes of menuList by dish Id, belongs to that menu list pass as parameter*/
    @GetMapping(value = "byDish/{dishId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByDishId(@PathVariable("dishId") int dishId) {
        log.info("getByDishId " + dishId);
        MenuList menuList = menuListService.getByDish(dishId);
        CurrentEntities.setCurrentMenuList(menuList);
        return menuList.getDishList();
    }


    /*get dish by Id and current menu list*/
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Dish getDish(@PathVariable("id") int id) {
        log.info("get " + id);
        MenuList MenuList = CurrentEntities.getCurrentMenuList();
        return service.get(id,MenuList.getId());
    }

    /*delete dish by Id*/
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    /*create new dish or update if exist */
    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("description") String description,
                               @RequestParam("price") Double price){
        MenuList menuList = CurrentEntities.getCurrentMenuList();
        Dish dish = new Dish(description,price);
        dish.setId(id);
        checkEmpty(dish);
        if (dish.isNew()) {
            ValidationUtil.checkNew(dish);
            log.info("create " + dish);
            service.save(dish,menuList.getId());
        } else {
            log.info("update " + dish);
            service.save(dish,menuList.getId());
        }
    }

    /*get current menuList description and dateTime*/
    @GetMapping(value = "currentMenuList",produces = "text/plain;charset=UTF-8")
    public String getCurrentMenuList() {
        log.info("currentMenuList");
        MenuList menuList = CurrentEntities.getCurrentMenuList();
        return menuList.getDescription() + ", " + DateTimeUtil.toString(menuList.getDateTime());
    }

    /*check dish for empty fields*/
    private void checkEmpty(Dish dish){
        ValidationUtil.checkEmpty(dish.getDescription(),"description");
        ValidationUtil.checkEmpty(dish.getPrice(),"price");
    }

}

