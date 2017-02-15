package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        service.save(MENU_LIST_CREATED,100006);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_LIST_01,MENU_LIST_02,MENU_LIST_03,MENU_LIST_04,MENU_LIST_CREATED),service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100016);
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_LIST_02,MENU_LIST_03,MENU_LIST_04),service.getAll());
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MENU_LIST_01,MENU_LIST_02,MENU_LIST_03,MENU_LIST_04),service.getAll());
    }

    @Test
    public void get() throws Exception {
        MATCHER.assertEquals(MENU_LIST_01,service.get(100016, 100006));
    }

}