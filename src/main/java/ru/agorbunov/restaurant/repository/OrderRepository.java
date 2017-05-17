package ru.agorbunov.restaurant.repository;

import ru.agorbunov.restaurant.model.Order;

import java.util.List;

/**
 * Interface for Order-repository
 */
public interface OrderRepository extends BaseRepository<Order> {

    /*save order in database with dishes and their quantities that order is consist-
    *,int[] dishIds - Ids of dishes, int[] dishQuantityValues - dishes quantities,
    * each dishId from first arr matches its quantity from second arr, arrays must have equal size
    *userId and restaurantId in parameters is Ids of user and restaurant to which the order is belong*/
    Order save(Order order,  int userId, int restaurantId, int[] dishIds, int[] dishQuantityValues);

    /*save order in database, userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong*/
    Order save(Order order,  int userId, int restaurantId);

    /*get order from database by Id, userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong*/
    Order get(int id, int userId, int restaurantId);

    /*get order from database by Id with collections of dishes which the order is have ,
    *userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong*/
    Order getWithDishes(int id, int userId, int restaurantId);

    /*get all orders from database that belongs to user with Id pass as parameter */
    List<Order> getByUser(int userId);

    /*get all orders from database that belongs to dish with Id pass as parameter */
    List<Order> getByDish(int dishId);

}
