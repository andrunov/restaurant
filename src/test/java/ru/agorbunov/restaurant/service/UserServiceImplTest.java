package ru.agorbunov.restaurant.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.agorbunov.restaurant.model.User;

import java.util.Arrays;

import static ru.agorbunov.restaurant.UserTestData.*;


/**
 * Created by Admin on 28.01.2017.
 */
public class UserServiceImplTest extends AbstractServiceTest {


    @Autowired
    private UserAndRestaurantService<User> service;

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