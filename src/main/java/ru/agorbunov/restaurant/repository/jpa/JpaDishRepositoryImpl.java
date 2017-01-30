package ru.agorbunov.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.ListRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 27.01.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaDishRepositoryImpl implements ListRepository<Dish> {


    @PersistenceContext
    private EntityManager em;



    @Override
    @Transactional
    public Dish save(Dish dish, int menuListId, int...ids) {
        if (!dish.isNew() && get(dish.getId(), menuListId) == null) {
            return null;
        }
        dish.setMenuList(em.getReference(MenuList.class, menuListId));
        List<Order> orders = new ArrayList<>();
        for (int id : ids){
            orders.add(em.getReference(Order.class, id));
        }
        dish.setOrders(orders);

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
}
