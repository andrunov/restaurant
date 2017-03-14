package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.Profiles;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Restaurant;
import ru.agorbunov.restaurant.repository.OrderRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Admin on 21.02.2017.
 */
//// TODO: 24.02.2017 remove profiles before production
@Transactional(readOnly = true)
public abstract class JdbcOrderRepositoryImpl<T> implements OrderRepository {
    private static final BeanPropertyRowMapper<Order> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Order.class);
    private static final BeanPropertyRowMapper<Dish> DISH_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);
    private static final BeanPropertyRowMapper<Restaurant> RESTAURANT_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Restaurant.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertOrder;

    protected abstract T toDbDateTime(LocalDateTime ldt);

    @Autowired
    private void setDataSource(DataSource dataSource) {
        this.insertOrder = new SimpleJdbcInsert(dataSource)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");
    }

    @Repository
    @Profile(Profiles.POSTGRES)
    public static class Java8JdbcOrderRepositoryImpl extends JdbcOrderRepositoryImpl<LocalDateTime> {
        @Override
        protected LocalDateTime toDbDateTime(LocalDateTime ldt) {
            return ldt;
        }
    }

    @Repository
    @Profile(Profiles.HSQLDB)
    public static class TimestampJdbcOrderRepositoryImpl extends JdbcOrderRepositoryImpl<Timestamp> {
        @Override
        protected Timestamp toDbDateTime(LocalDateTime ldt) {
            return Timestamp.valueOf(ldt);
        }
    }

    @Override
    @Transactional
    public Order save(Order order, int userId, int restaurantId, int... dishIds) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("user_id", userId)
                .addValue("restaurant_id", restaurantId)
                .addValue("date_time", toDbDateTime(order.getDateTime()));

        if (order.isNew()) {
            Number newKey = insertOrder.executeAndReturnKey(map);
            order.setId(newKey.intValue());
            insertDishes(order.getId(), dishIds);
        } else {
            if(namedParameterJdbcTemplate.update("UPDATE orders SET date_time=:date_time WHERE id=:id AND user_id=:user_id AND restaurant_id=:restaurant_id", map)==0){
                return null;
            }else {
                deleteDishes(order.getId());
                insertDishes(order.getId(), dishIds);
            }
        }

        return order;
    }

    private boolean deleteDishes(int orderId){
        return jdbcTemplate.update("DELETE FROM orders_dishes WHERE order_id=?", orderId) != 0;

    }

    private void insertDishes(int orderId, int... dishIds) {
        jdbcTemplate.batchUpdate("INSERT INTO orders_dishes (order_id, dish_id) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                            ps.setInt(1, orderId);
                            ps.setInt(2, dishIds[i]);
                    }

                    @Override
                    public int getBatchSize() {
                        return dishIds.length;
                    }
                });
    }

    @Override
    public Order get(int id, int userId, int restaurantId) {
        List<Order> orders = jdbcTemplate.query("SELECT * FROM orders WHERE id=? AND user_id=? AND restaurant_id=?", ROW_MAPPER, id,userId,restaurantId);
        return DataAccessUtils.singleResult(orders);
    }

    @Override
    public Order getWithDishes(int id, int userId, int restaurantId) {
        List<Order> orders = jdbcTemplate.query("SELECT * FROM orders WHERE id=? AND user_id=? AND restaurant_id=?", ROW_MAPPER, id,userId,restaurantId);
        Order result = DataAccessUtils.singleResult(orders);
        return setDishes(result);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM orders WHERE id=?", id) != 0;
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query("SELECT * FROM orders ORDER BY date_time DESC ", ROW_MAPPER);
    }

    @Override
    public List<Order> getByUser(int userId) {
        List<Order> result = jdbcTemplate.query("SELECT * FROM orders WHERE user_id=? ORDER BY date_time DESC ", ROW_MAPPER,userId);
        for (Order order : result) {
            setRestaurant(order);
        }
        return result;
    }

    private Order setDishes(Order o) {
        if (o != null) {
            List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes AS d LEFT JOIN orders_dishes AS od ON d.id = od.dish_id WHERE od.order_id=?",
                    DISH_ROW_MAPPER, o.getId());
            o.setDishes(dishes);
        }
        return o;
    }

    private Order setRestaurant(Order o){
        if (o != null) {
            List<Restaurant> restaurants = jdbcTemplate.query("SELECT r.id, r.name, r.address FROM restaurants AS r JOIN orders AS o ON r.id = o.restaurant_id WHERE o.id=?",
                    RESTAURANT_ROW_MAPPER, o.getId());
            o.setRestaurant(DataAccessUtils.singleResult(restaurants));
        }
        return o;
    }
}
