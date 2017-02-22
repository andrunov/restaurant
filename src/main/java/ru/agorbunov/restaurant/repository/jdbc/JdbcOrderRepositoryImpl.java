package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.OrderRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcOrderRepositoryImpl implements OrderRepository {
    private static final BeanPropertyRowMapper<Order> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Order.class);
    private static final BeanPropertyRowMapper<Dish> DISH_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertRestaurant;

    @Autowired
    public JdbcOrderRepositoryImpl(DataSource dataSource) {
        this.insertRestaurant = new SimpleJdbcInsert(dataSource)
                .withTableName("orders")
                .usingGeneratedKeyColumns("id");
    }
    @Override
    public Order save(Order order, int userId, int restaurantId, int... dishIds) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", order.getId())
                .addValue("user_id", userId)
                .addValue("restaurant_id", restaurantId)
                .addValue("date_time", order.getDateTime());

        if (order.isNew()) {
            Number newKey = insertRestaurant.executeAndReturnKey(map);
            order.setId(newKey.intValue());
            insertDishes(order.getId(),dishIds);
        } else {
            if(namedParameterJdbcTemplate.update("UPDATE orders SET date_time=:date_time WHERE id=:id AND user_id=:user_id AND restaurant_id=:restaurant_id", map)==0){
                return null;
            }else {
                deleteDishes(order.getId());
                insertDishes(order.getId(),dishIds);
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
        List<Order> menuLists = jdbcTemplate.query("SELECT * FROM orders WHERE id=? AND user_id=? AND restaurant_id=?", ROW_MAPPER, id,userId,restaurantId);
        return DataAccessUtils.singleResult(menuLists);
    }

    @Override
    public Order getWith(int id, int userId, int restaurantId) {
        List<Order> menuLists = jdbcTemplate.query("SELECT * FROM orders WHERE id=? AND user_id=? AND restaurant_id=?", ROW_MAPPER, id,userId,restaurantId);
        Order result = DataAccessUtils.singleResult(menuLists);
        return setDishes(result);
    }

    @Override
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM orders WHERE id=?", id) != 0;
    }

    @Override
    public List<Order> getAll() {
        return jdbcTemplate.query("SELECT * FROM orders", ROW_MAPPER);
    }

    private Order setDishes(Order o) {
        if (o != null) {
            List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes AS d LEFT JOIN orders_dishes AS od ON d.id = od.dish_id WHERE od.order_id=?",
                    DISH_ROW_MAPPER, o.getId());
            o.setDishes(dishes);
        }
        return o;
    }
}
