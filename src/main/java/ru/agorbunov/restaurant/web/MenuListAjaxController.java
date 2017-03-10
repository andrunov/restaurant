package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.service.MenuListService;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.util.List;

/**
 * Created by Admin on 09.03.2017.
 */
@RestController
@RequestMapping(value =  "/ajax/menuLists")
public class MenuListAjaxController {


    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    private MenuListService service;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuList> getAll(Integer restaurantId) {
        log.info("getAll");
        return service.getByRestaurant(Integer.valueOf(100006));
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuList menuList(@PathVariable("id") int id) {
        log.info("get " + id);
        return service.get(id,100006);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

//    @PostMapping
//    public void createOrUpdate(@RequestParam("id") Integer id,
//                               @RequestParam("dateTime") LocalDateTime dateTime){
//        MenuList menuList = new MenuList(currentRestaurant, dateTime);
//        menuList.setId(id);
//        checkEmpty(menuList);
//        if (menuList.isNew()) {
//            ValidationUtil.checkNew(menuList);
//            log.info("create " + menuList);
//            service.save(menuList,currentRestaurant.getId());
//        } else {
//            log.info("update " + menuList);
//            service.save(menuList,currentRestaurant.getId());
//        }
//    }

    private void checkEmpty(MenuList menuList){
        ValidationUtil.checkEmpty(menuList.getDateTime(),"dateTime");
    }


}
