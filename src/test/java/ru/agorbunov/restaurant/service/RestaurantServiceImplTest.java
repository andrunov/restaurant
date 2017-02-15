package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.model.Restaurant;

import java.util.Arrays;

import static ru.agorbunov.restaurant.RestaurantTestData.*;

/**
 * Created by Admin on 29.01.2017.
 */
public class RestaurantServiceImplTest extends AbstractServiceTest{

    @Autowired
    UserAndRestaurantService<Restaurant> service;

    @Test
    public void save() throws Exception {
        service.save(RESTAURANT_CREATED);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_01, RESTAURANT_02, RESTAURANT_03, RESTAURANT_04, RESTAURANT_CREATED), service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100006);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_02, RESTAURANT_03, RESTAURANT_04), service.getAll());
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_01, RESTAURANT_02, RESTAURANT_03, RESTAURANT_04), service.getAll());
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(100006);
        MATCHER.assertEquals(RESTAURANT_01, restaurant);
    }

}