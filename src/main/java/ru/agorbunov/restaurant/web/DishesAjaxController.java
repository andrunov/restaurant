package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.service.DishService;
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

    /*get dishes by current menu list*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getByMenuList() {
        log.info("getByMenuList");
        MenuList MenuList = CurrentEntities.getCurrentMenuList();
        return service.getByMenuList(MenuList.getId());
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

    /*check dish for empty fields*/
    private void checkEmpty(Dish dish){
        ValidationUtil.checkEmpty(dish.getDescription(),"description");
        ValidationUtil.checkEmpty(dish.getPrice(),"price");
    }

}

