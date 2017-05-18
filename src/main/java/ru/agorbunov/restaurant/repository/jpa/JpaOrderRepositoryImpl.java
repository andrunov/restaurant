package ru.agorbunov.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.model.jpa.OrdersDishes;
import ru.agorbunov.restaurant.model.jpa.OrdersDishesId;
import ru.agorbunov.restaurant.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Order-entities repository by Java Persistence API
 */
@Repository
@Transactional(readOnly = true)
public class JpaOrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    /*save order in database if it is new entity and update if it is exist,
    *int[] dishIds - Ids of dishes, int[] dishQuantityValues - dishes quantities,
    *each dishId from first arr matches its quantity from second arr, arrays must have equal size
    *userId and restaurantId in parameters is Ids of user and restaurant to which the order is belong*/
    @Override
    @Transactional
    public Order save(Order order, int userId, int restaurantId, int[] dishIds, int[] dishQuantityValues) {

        if (!order.isNew() && get(order.getId(), userId, restaurantId) == null) {
            return null;
        }
        order.setUser(em.getReference(User.class, userId));
        order.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (order.isNew()){
            em.persist(order);
        }else {
            order = em.merge(order);
        }
        setOrdersDishes(order,dishIds,dishQuantityValues);
        return order;
    }

    /*save order's dishes and they quantities to database,
    *int[] dishIds - Ids of dishes, int[] dishQuantityValues - dishes quantities,
    * each dishId from first arr matches its quantity from second arr, arrays must have equal size*/
    private void setOrdersDishes(Order order, int[] dishIds, int[] dishQuantityValues) {
        for (int i = 0; i < dishIds.length; i++){
            OrdersDishes od = new OrdersDishes();
            od.setOrder(order);
            od.setDish(em.getReference(Dish.class, dishIds[i]));
            od.setDishQuantity(dishQuantityValues[i]);
            OrdersDishesId id = new OrdersDishesId();
            id.setDish(dishIds[i]);
            id.setOrder(order.getId());
            if (em.find(OrdersDishes.class,id)!=null){
                em.merge(od);
            }else {
                em.persist(od);
            }
        }
    }

    /*save order in database if it is new entity and update if it is exist,
    *userId and restaurantId in parameters is Ids of user and restaurant to which the order is belong,
    *if order is already exist and have collections of dishes they not erase in database*/
    @Override
    @Transactional
    public Order save(Order order, int userId, int restaurantId) {
        if (!order.isNew() && get(order.getId(), userId, restaurantId) == null) {
            return null;
        }
        order.setUser(em.getReference(User.class, userId));
        order.setRestaurant(em.getReference(Restaurant.class, restaurantId));

        if (order.isNew()){
            em.persist(order);
            return order;
        }else {
            return em.merge(order);
        }
    }

    /*delete order from database by Id */
    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Order.DELETE)
                .setParameter("id", id)
                .executeUpdate() !=0;
    }

    /*get all orders from database*/
    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.GET_ALL, Order.class).getResultList();
    }

    /*get order from database by Id, userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong*/
    @Override
    public Order get(int id, int userId, int restaurantId) {

        Order order = em.find(Order.class,id);

        if (order.getRestaurant().getId() == restaurantId){
            if (order.getUser().getId() == userId) {
                return order;
            }
        }
        return null;
    }

    /*get order from database by Id with collections of dishes which the order is have ,
    *userId and restaurantId in parameters is Ids of
    *user and restaurant to which the order is belong*/
    @Override
    public Order getWithDishes(int id, int userId, int restaurantId) {
        Order order = (Order)em.createNamedQuery(Order.GET_WITH_DISHES)
                                          .setParameter("id",id)
                                          .getSingleResult();
        if (order.getRestaurant().getId() == restaurantId){
            if (order.getUser().getId() == userId) {
                return order;
            }
        }
        return null;
    }

    /*get all orders from database that belongs to user with Id pass as parameter */
    @Override
    public List<Order> getByUser(int userId) {
        return em.createNamedQuery(Order.GET_ALL_BY_USER, Order.class)
                                    .setParameter("userId",userId)
                                    .getResultList();
    }

    /*get all orders from database that belongs to dish with Id pass as parameter */
    @Override
    public List<Order> getByDish(int dishId) {
        List<Order> result = new ArrayList<>();
        List<Order> orders = em.createNamedQuery(Order.GET_ALL_BY_DISH,Order.class)
                                    .setParameter(0,dishId)
                                    .getResultList();

        for (Order order : orders){
            result.add(getWithUser(order));
        }
        return result;
    }

    /*get order from database with user by which order was made*/
    private Order getWithUser(Order order){
        return (Order)em.createNamedQuery(Order.GET_WITH_USER)
                                    .setParameter("id",order.getId())
                                    .getSingleResult();
    }
}
