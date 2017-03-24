package ru.agorbunov.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 30.01.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaOrderRepositoryImpl implements OrderRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public Order save(Order order, int userId, int restaurantId, int[] dishIds, int[] dishQuantityValues) {
        if (!order.isNew() && get(order.getId(), userId, restaurantId) == null) {
            return null;
        }
        order.setUser(em.getReference(User.class, userId));
        order.setRestaurant(em.getReference(Restaurant.class, restaurantId));

        Map<Dish,Integer> dishes = new HashMap<>();
        for (int id : dishIds){
            dishes.put(em.getReference(Dish.class, id),1);
        }
        order.setDishes(dishes);
        if (order.isNew()){
            em.persist(order);
            return order;
        }else {
            return em.merge(order);
        }
    }

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

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Order.DELETE)
                .setParameter("id", id)
                .executeUpdate() !=0;
    }

    @Override
    public List<Order> getAll() {
        return em.createNamedQuery(Order.GET_ALL, Order.class).getResultList();
    }

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

    @Override
    public List<Order> getByUser(int userId) {
        return em.createNamedQuery(Order.GET_ALL_BY_USER, Order.class)
                                    .setParameter("userId",userId)
                                    .getResultList();
    }
}
