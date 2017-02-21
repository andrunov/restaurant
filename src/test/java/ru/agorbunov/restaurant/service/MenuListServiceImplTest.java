package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.matcher.ModelMatcher;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;

import static ru.agorbunov.restaurant.MenuListTestData.*;

/**
 * Created by Admin on 30.01.2017.
 */
public class MenuListServiceImplTest extends AbstractServiceTest {

    @Autowired
    private MenuListService service;

    @Test
    public void save() throws Exception {
        service.save(MENU_LIST_CREATED,RESTAURANT_01_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_LIST_01,MENU_LIST_02,MENU_LIST_03,MENU_LIST_04,MENU_LIST_CREATED),service.getAll());
    }

    @Test
    public void saveNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("menu list must not be null");
        service.save(null,RESTAURANT_01_ID);
    }

    @Test
    public void delete() throws Exception {
        service.delete(MENU_LIST_01_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_LIST_02,MENU_LIST_03,MENU_LIST_04),service.getAll());
    }

    @Test
    public void deleteNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", 10));
        service.delete(10);
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_LIST_01,MENU_LIST_02,MENU_LIST_03,MENU_LIST_04),service.getAll());
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(MENU_LIST_01,service.get(MENU_LIST_01_ID, RESTAURANT_01_ID));
    }

    @Test
    public void getNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", MENU_LIST_01_ID));
        service.get(MENU_LIST_01_ID, RESTAURANT_02_ID);
    }

    @Test
    public void update() throws Exception{
        MenuList menuList = service.get(MENU_LIST_01_ID, RESTAURANT_01_ID);
        menuList.setDateTime(LocalDateTime.of(2017,2,15,17,31));
        service.save(menuList,RESTAURANT_01_ID);
        MATCHER.assertEquals(menuList,service.get(MENU_LIST_01_ID, RESTAURANT_01_ID));
    }

    @Test
    public void updateNotFound() throws Exception{
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", MENU_LIST_01_ID));
        MenuList menuList = service.get(MENU_LIST_01_ID, RESTAURANT_02_ID);
    }

    @Test
    public void updateNull() throws Exception {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("menu list must not be null");
        service.update(null,RESTAURANT_01_ID);
    }

    @Test
    public void getWith() throws Exception {
        ModelMatcher<Dish> MatcherDish = new ModelMatcher<>();
        MenuList menuList = service.getWith(MENU_LIST_01_ID, RESTAURANT_01_ID);
        MatcherDish.assertCollectionEquals(MENU_LIST_01.getDishList(),menuList.getDishList());
    }

    @Test
    public void getWithNotFound() throws Exception {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(String.format("Not found entity with id=%d", MENU_LIST_01_ID));
        service.getWith(MENU_LIST_01_ID, RESTAURANT_02_ID);
    }

}