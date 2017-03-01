package ru.agorbunov.restaurant.web.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 01.03.2017.
 */
@RestController
@RequestMapping
public class RestaurantAjaxController {

    @Autowired
    private RestaurantService service;

    @GetMapping(value = "/restaurant",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        list.add("789");
        return list;
    }

    @GetMapping(value = "/int")
    public int getInt() {
        return 15;
    }



}
