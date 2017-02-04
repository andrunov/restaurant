package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.agorbunov.restaurant.model.Restaurant;

import java.util.Arrays;

import static ru.agorbunov.restaurant.RestaurantTestData.*;

/**
 * Created by Admin on 29.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RestaurantServiceImplTest {

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