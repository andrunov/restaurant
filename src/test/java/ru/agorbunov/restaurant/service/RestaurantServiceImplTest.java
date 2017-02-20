package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.util.exception.NotFoundException;

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
    public void saveNull() throws Exception{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("restaurant must not be null");
        service.save(null);
    }

    @Test
    public void delete() throws Exception {
        service.delete(RESTAURANT_01_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_02, RESTAURANT_03, RESTAURANT_04), service.getAll());
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", 10));
        service.delete(10);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT_01, RESTAURANT_02, RESTAURANT_03, RESTAURANT_04), service.getAll());
    }

    @Test
    public void get() throws Exception {
        Restaurant restaurant = service.get(RESTAURANT_01_ID);
        MATCHER.assertEquals(RESTAURANT_01, restaurant);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", 10));
        Restaurant restaurant = service.get(10);
    }

    @Test
    public void update() throws Exception{
        Restaurant restaurant = service.get(RESTAURANT_01_ID);
        restaurant.setName("обновленное название");
        restaurant.setAddress("обновленный адрес");
        service.update(restaurant);
        MATCHER.assertEquals(restaurant,service.get(RESTAURANT_01_ID));
    }

    @Test
    public void updateNull() throws Exception{
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("restaurant must not be null");
        service.update(null);
    }

}