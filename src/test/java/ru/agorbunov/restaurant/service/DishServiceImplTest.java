package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.util.exception.NotFoundException;

import java.util.Arrays;

import static ru.agorbunov.restaurant.DishTestData.*;

/**
 * Created by Admin on 27.01.2017.
 */
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;

    @Test
    public void save() throws Exception {
        service.save(DISH_CREATED,100016,100010);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_01,DISH_02,DISH_03,DISH_04,DISH_05, DISH_06, DISH_07, DISH_08, DISH_09, DISH_10, DISH_11, DISH_12, DISH_13, DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20, DISH_CREATED),service.getAll());

    }

    @Test
    public void delete() throws Exception {
        service.delete(100020);
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_02,DISH_03,DISH_04,DISH_05, DISH_06, DISH_07, DISH_08, DISH_09, DISH_10, DISH_11, DISH_12, DISH_13, DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20),service.getAll());

    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        service.delete(1);
    }


    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(DISH_01,DISH_02,DISH_03,DISH_04,DISH_05, DISH_06, DISH_07, DISH_08, DISH_09, DISH_10, DISH_11, DISH_12, DISH_13, DISH_14, DISH_15, DISH_16, DISH_17, DISH_18, DISH_19, DISH_20),service.getAll());
    }

    @Test
    public void get() throws Exception {
        Dish dish = service.get(100020,100016);
        MATCHER.assertEquals(DISH_01, dish);
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        Dish dish = service.get(10,10);
    }

    @Test
    public  void update() throws Exception{
        Dish dish = service.get(100020,100016);
        dish.setDescription("обновленное блюдо");
        dish.setPrice(1.02);
        service.update(dish, 100016,100010,100011);
        MATCHER.assertEquals(dish, service.get(100020,100016));
    }

    @Test
    public void updateNotFound() throws Exception{
        thrown.expect(NotFoundException.class);
        Dish dish = service.get(100020,100016);
        service.update(dish, 100017,100010,100011);
    }

}