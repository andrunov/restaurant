package ru.agorbunov.restaurant.service.jpa;

import org.springframework.test.context.ActiveProfiles;
import ru.agorbunov.restaurant.Profiles;
import ru.agorbunov.restaurant.service.DishServiceImplTest;

/**
 * Created by Admin on 23.02.2017.
 */
// TODO: 23.02.2017 delete class before production
@ActiveProfiles(Profiles.JPA)
public class DishServiceJpaTest extends DishServiceImplTest {
}
