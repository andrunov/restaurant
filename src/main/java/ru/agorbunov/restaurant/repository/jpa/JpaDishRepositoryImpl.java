package ru.agorbunov.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.DishRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 27.01.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements DishRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Dish save(Dish dish, int menuListId) {
        if (!dish.isNew() && get(dish.getId(), menuListId) == null) {
            return null;
        }
        dish.setMenuList(em.getReference(MenuList.class, menuListId));

        if (dish.isNew()){
            em.persist(dish);
            return dish;
        }else {
            return em.merge(dish);
        }
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int menuListId, int[] ordersIds, int[] dishQuantityValues) {
        this.save(dish,menuListId);
        if (!dish.isNew() && get(dish.getId(), menuListId) == null) {
            return null;
        }
        dish.setMenuList(em.getReference(MenuList.class, menuListId));
        Map<Order,Integer> ordersDishes = new LinkedHashMap<>();
        for (int i = 0; i < ordersIds.length;i++){
            ordersDishes.put(em.getReference(Order.class, ordersIds[i]),dishQuantityValues[i]);
        }
        dish.setOrdersDishesList(ordersDishes);

        if (dish.isNew()){
            em.persist(dish);
            return dish;
        }else {
            return em.merge(dish);
        }
    }


    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(Dish.DELETE)
                .setParameter("id", id)
                .executeUpdate() !=0;
    }

    @Override
    public List<Dish> getAll() {
        return em.createNamedQuery(Dish.GET_ALL, Dish.class).getResultList();
    }

    @Override
    public Dish get(int id, int menuListId) {
        Dish dish = em.find(Dish.class, id);
        return dish != null && dish.getMenuList().getId() == menuListId ? dish : null;
    }

    @Override
    public Dish getWithOrders(int id, int menuListId) {
        Dish dish = (Dish)em.createNamedQuery(Dish.GET_WITH_ORDERS)
                                        .setParameter("id",id)
                                        .getSingleResult();
        return dish != null && dish.getMenuList().getId() == menuListId ? dish : null;
    }

    @Override
    public List<Dish> getByMenuList(int menuListId) {
        return em.createNamedQuery(Dish.GET_ALL_BY_MENU_LIST, Dish.class)
                .setParameter("menuListId",menuListId)
                .getResultList();
    }

    @Override
    public Map<Dish,Integer> getByOrder(int orderId) {
        List<Order> result = em.createNamedQuery(Dish.GET_ALL_BY_ORDER, Order.class)
                .setParameter("orderId",orderId)
                .getResultList();
        return result.get(0).getDishes();
    }
}
