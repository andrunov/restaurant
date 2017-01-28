package ru.agorbunov.restaurant;

import ru.agorbunov.restaurant.matcher.ModelMatcher;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Admin on 21.01.2017.
 */
public class UserTestData {

    public static final ModelMatcher<User> MATCHER = new ModelMatcher<>();


    public static final User USER_01 = new User("Алексей Иванов","ivanov.alexey@gmail.com", Role.USER);
    public static final User USER_02 = new User("Андрей Горбунов","andrunov@gmail.com",Role.ADMIN, Role.USER);
    public static final User USER_03 = new User("Павел Сидоров","sidor@gmail.com", Role.USER);
    public static final User USER_04 = new User("Roberto Zanetti","rzanetti@gmail.com", Role.USER);
    public static final User USER_05 = new User("John Bon Jovi","jbj@gmail.com", Role.USER);
    public static final User USER_06 = new User("Didier Maoruani","dmauruani@gmail.com", Role.USER);

    public static final User USER_CREATED = new User("Созданный пользователь",
                                                        "created@yandex.ru",
                                                        "12340",
                                                         Role.USER);

    public static User getUpdated(User user){
        user.setName("Обновленный пользователь");
        user.setEmail("updated@mail.ru");
        user.setRoles(new HashSet<>(Arrays.asList(Role.USER,Role.ADMIN)));
        return user;
    }

}
