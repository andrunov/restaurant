package ru.agorbunov.restaurant;

import ru.agorbunov.restaurant.matcher.ModelMatcher;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import static ru.agorbunov.restaurant.OrderTestData.*;

/**
 * Created by Admin on 21.01.2017.
 */
public class UserTestData {

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>();


    public static final User USER_01 = new User("Алексей Иванов","ivanov.alexey@gmail.com","123",Collections.singletonList(ORDER_01) ,Role.USER);
    public static final User USER_02 = new User("Андрей Горбунов","andrunov@gmail.com","456",Collections.singletonList(ORDER_02),Role.ADMIN, Role.USER);
    public static final User USER_03 = new User("Павел Сидоров","sidor@gmail.com","789",Collections.singletonList(ORDER_03), Role.USER);
    public static final User USER_04 = new User("Roberto Zanetti","rzanetti@gmail.com","101112",Collections.singletonList(ORDER_04), Role.USER);
    public static final User USER_05 = new User("John Bon Jovi","jbj@gmail.com","131415",Collections.singletonList(ORDER_05), Role.USER);
    public static final User USER_06 = new User("Didier Maoruani","dmauruani@gmail.com","161718",Collections.singletonList(ORDER_06), Role.USER);

    public static final User USER_CREATED = new User("Созданный пользователь",
                                                        "created@yandex.ru",
                                                        "12340",
                                                        Collections.singletonList(ORDER_01),
                                                         Role.USER);

    public static final int USER_01_ID = 100000;

    public static User getUpdated(User user){
        user.setName("Обновленный пользователь");
        user.setEmail("updated@mail.ru");
        user.setRoles(new HashSet<>(Arrays.asList(Role.USER,Role.ADMIN)));
        return user;
    }

}
