package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.agorbunov.restaurant.model.MenuList;

import java.util.Arrays;

import static ru.agorbunov.restaurant.MenuListTestData.*;

/**
 * Created by Admin on 30.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MenuListServiceImplTest {

    @Autowired
    private ReferenseService<MenuList> service;

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