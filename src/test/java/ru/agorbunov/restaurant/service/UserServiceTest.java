package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.agorbunov.restaurant.model.User;

import java.util.Arrays;

import static ru.agorbunov.restaurant.UserTestData.*;


/**
 * Created by Admin on 28.01.2017.
 */
@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {

    @Autowired
    private BaseService<User> service;

    @Test
    public void save() throws Exception {
        service.save(USER_CREATED);
        MATCHER.assertCollectionEquals(Arrays.asList(USER_01, USER_02, USER_03, USER_04, USER_05, USER_06, USER_CREATED), service.getAll());
    }

    @Test
    public void delete() throws Exception {
        service.delete(100000);
        MATCHER.assertCollectionEquals(Arrays.asList( USER_02, USER_03, USER_04, USER_05, USER_06), service.getAll());
    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(USER_01, USER_02, USER_03, USER_04, USER_05, USER_06), service.getAll());

    }

    @Test
    public void get() throws Exception {
        User user = service.get(100000);
        MATCHER.assertEquals(USER_01, user);
    }

}