package ru.agorbunov.restaurant.web;

import ru.agorbunov.restaurant.model.*;

/**
 * Current entities of session
 */
public class CurrentEntities {

    private static User currentUser;

    private static Restaurant currentRestaurant;

    private static MenuList currentMenuList;

    private static Order currentOrder;

    private static Dish currentDish;

    public CurrentEntities() {
    }

    public static Restaurant getCurrentRestaurant() {
        return currentRestaurant;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        CurrentEntities.currentUser = currentUser;
    }

    public static void setCurrentRestaurant(Restaurant currentRestaurant) {
        CurrentEntities.currentRestaurant = currentRestaurant;
    }

    public static MenuList getCurrentMenuList() {
        return currentMenuList;
    }

    public static void setCurrentMenuList(MenuList currentMenuList) {
        CurrentEntities.currentMenuList = currentMenuList;
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void setCurrentOrder(Order currentOrder) {
        CurrentEntities.currentOrder = currentOrder;
    }

    public static Dish getCurrentDish() {
        return currentDish;
    }

    public static void setCurrentDish(Dish currentDish) {
        CurrentEntities.currentDish = currentDish;
    }
}
