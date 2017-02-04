package ru.agorbunov.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.repository.MenuListRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Admin on 30.01.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaMenuListRepositoryImpl implements MenuListRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public MenuList save(MenuList menuList, int restaurantId) {
        if (!menuList.isNew() && get(menuList.getId(), restaurantId) == null) {
            return null;
        }
        menuList.setRestaurant(em.getReference(Restaurant.class, restaurantId));
        if (menuList.isNew()){
            em.persist(menuList);
            return menuList;
        }else {
            return em.merge(menuList);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(MenuList.DELETE)
                .setParameter("id", id)
                .executeUpdate() !=0;
    }

    @Override
    public List<MenuList> getAll() {
        return em.createNamedQuery(MenuList.GET_ALL, MenuList.class).getResultList();
    }

    @Override
    public MenuList get(int id, int restaurantId) {
        MenuList menuList = em.find(MenuList.class,id);
        return menuList != null && menuList.getRestaurant().getId() == restaurantId ? menuList : null;
    }
}
