package ru.agorbunov.restaurant;

import org.springframework.test.context.ActiveProfilesResolver;

/**
 * Class for get active DB profile
 */
public class ActiveDbProfileResolver implements ActiveProfilesResolver {
    @Override
    public String[] resolve(Class<?> aClass) {
        return new String[]{Profiles.getActiveDbProfile()};
    }
}
