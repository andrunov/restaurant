package ru.agorbunov.restaurant;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Created by Admin on 24.02.2017.
 */
public class ActiveDbProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}
