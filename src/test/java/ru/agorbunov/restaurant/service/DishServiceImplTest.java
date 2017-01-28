package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.agorbunov.restaurant.model.Dish;

import java.util.Arrays;

import static ru.agorbunov.restaurant.DishTestData.*;

/**
 * Created by Admin on 27.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest {

    @Autowired
    protected BaseService<Dish> service;

    @Test
    public void save() throws Exception {
        service.save(DISH_CREATED);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_01,DISH_02,DISH_03,DISH_04,DISH_05, DISH_06, DISH_07, DISH_08, DISH_09, DISH_10, DISH_11, DISH_12, DISH_13, DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20, DISH_CREATED),service.getAll());

    }

    @Test
    public void delete() throws Exception {
        service.delete(100010);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_02,DISH_03,DISH_04,DISH_05, DISH_06, DISH_07, DISH_08, DISH_09, DISH_10, DISH_11, DISH_12, DISH_13, DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20),service.getAll());

    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_01,DISH_02,DISH_03,DISH_04,DISH_05, DISH_06, DISH_07, DISH_08, DISH_09, DISH_10, DISH_11, DISH_12, DISH_13, DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20),service.getAll());
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(100010);
        MATCHER.assertEquals(DISH_01, dish);
    }

}