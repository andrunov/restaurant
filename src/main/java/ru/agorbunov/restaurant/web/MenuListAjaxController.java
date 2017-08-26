package ru.agorbunov.restaurant.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.service.MenuListService;
import ru.agorbunov.restaurant.service.RestaurantService;
import ru.agorbunov.restaurant.util.DateTimeUtil;
import ru.agorbunov.restaurant.util.ValidationUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Rest controller for menuLists.jsp and other .jsp
 * to exchange menuList data with service-layer
 */
@RestController
@RequestMapping(value =  "/ajax/menuLists")
public class MenuListAjaxController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MenuListService service;

    @Autowired
    private RestaurantService restaurantService;

    /*get all menu lists by current restaurant*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuList> getByRestaurant() {
        log.info("getByRestaurant");
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        return service.getByRestaurant(currentRestaurant.getId());
    }

    /*get all menu lists by current restaurant Id pass as parameter*/
    @GetMapping(value = "byRestaurant/{restaurantId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MenuList> getByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        log.info("byRestaurant/{restaurantId}");
        Restaurant currentRestaurant = restaurantService.get(restaurantId);
        CurrentEntities.setCurrentRestaurant(currentRestaurant);
        return service.getByRestaurant(restaurantId);
    }

    /*get menuList by Id and current restaurant*/
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public MenuList getMenuList(@PathVariable("id") int id) {
        log.info("get " + id);
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        return service.get(id,currentRestaurant.getId());
    }

//    /*use for orders.jsp to update data in modal windows
//    * get menuList by Id and setup by its data fields in modal window*/
//    @GetMapping(value = "/set/{id}")
//    public String setCurrentMenuList(@PathVariable("id") int id) {
//        log.info("set current menuList " + id);
//        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
//        MenuList menuList = service.get(id,currentRestaurant.getId());
//        CurrentEntities.setCurrentMenuList(menuList);
//        return String.format("%s, %s", menuList.getDescription(),
//                                        menuList.getDateTime().toLocalDate().toString());
//    }

    /*delete menuList by Id*/
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    /*create new menuList or update if exist*/
    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("description")String description,
                               @RequestParam("dateTime")@DateTimeFormat(pattern = DateTimeUtil.DATE_TIME_PATTERN) LocalDateTime dateTime,
                               @RequestParam(value = "enabled",required = false) String enabled){
        Restaurant currentRestaurant = CurrentEntities.getCurrentRestaurant();
        MenuList menuList = new MenuList(currentRestaurant, description, dateTime);
        menuList.setId(id);
        menuList.setEnabled(Boolean.parseBoolean(enabled));
        checkEmpty(menuList);
        if (menuList.isNew()) {
            ValidationUtil.checkNew(menuList);
            log.info("create " + menuList);
            service.save(menuList,currentRestaurant.getId());
        } else {
            log.info("update " + menuList);
            service.save(menuList,currentRestaurant.getId());
        }
    }

    /*check menuList for empty fields*/
    private void checkEmpty(MenuList menuList){
        ValidationUtil.checkEmpty(menuList.getDateTime(),"dateTime");
    }


}
