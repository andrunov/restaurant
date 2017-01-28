package ru.agorbunov.restaurant.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.repository.BaseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Admin on 28.01.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JpaUserRepository implements BaseRepository<User> {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public User save(User user) {
        if (user.isNew()){
            em.persist(user);
            return user;
        }else {
            return em.merge(user);
        }
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return em.createNamedQuery(User.DELETE)
                .setParameter("id", id)
                .executeUpdate() !=0;
    }

    @Override
    public List<User> getAll() {
        return em.createNamedQuery(User.GET_ALL, User.class).getResultList();
    }

    @Override
    public User get(int id) {
        return em.find(User.class, id);
    }
}
