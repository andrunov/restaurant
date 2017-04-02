package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Dish;
import ru.agorbunov.restaurant.model.MenuList;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.repository.DishRepository;
import ru.agorbunov.restaurant.util.DateTimeUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcDishRepositoryImpl<T> implements DishRepository {
    private static final BeanPropertyRowMapper<Dish> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);
    private static final BeanPropertyRowMapper<Order> ORDER_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Order.class);
    private static final BeanPropertyRowMapper<MenuList> MENU_LIST_ROW_MAPPER = BeanPropertyRowMapper.newInstance(MenuList.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertDish;

    @Autowired
    public JdbcDishRepositoryImpl(DataSource dataSource) {
        this.insertDish = new SimpleJdbcInsert(dataSource)
                .withTableName("dishes")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public Dish save(Dish dish, int menulistId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", dish.getId())
                .addValue("menu_list_id", menulistId)
                .addValue("description", dish.getDescription())
                .addValue("price", dish.getPrice());
        if (dish.isNew()) {
            Number newKey = insertDish.executeAndReturnKey(map);
            dish.setId(newKey.intValue());
        } else {
            if(namedParameterJdbcTemplate.update("UPDATE dishes SET description=:description, price=:price WHERE id=:id AND menu_list_id=:menu_list_id", map)==0){
                return null;
            }
        }
        return dish;
    }

    private boolean deleteOrders(int dishId){
        return jdbcTemplate.update("DELETE FROM orders_dishes WHERE dish_id=?", dishId) != 0;

    }

    @Override
    public Dish get(int id, int menuListId) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE id=? AND menu_list_id=?", ROW_MAPPER, id,menuListId);
        return DataAccessUtils.singleResult(dishes);

    }

    @Override
    public Dish getWithOrders(int id, int menuListId) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE id=? AND menu_list_id=?", ROW_MAPPER, id,menuListId);
        Dish result = DataAccessUtils.singleResult(dishes);
        return setOrders(result);
    }

    @Override
    public List<Dish> getByMenuList(int menuListId) {
        List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes WHERE menu_list_id=?", ROW_MAPPER, menuListId);
        for (Dish dish : dishes) {
            setMenuList(dish);
        }
        return dishes;
    }

    @Override
    public Map<Dish,Integer> getByOrder(int orderId) {
        List<Map<String,Object>> results = jdbcTemplate.queryForList("SELECT d.* , od.dish_quantity FROM orders_dishes AS od LEFT JOIN dishes as d ON d.id = od.dish_id WHERE od.order_id=? ",orderId);
        Map<Dish,Integer> dishMap = new LinkedHashMap<>();
        for (Map row : results){
            Dish dish = new Dish();
            dish.setId((Integer)row.get("id"));
            dish.setDescription((String) row.get("description"));
            dish.setPrice((Double) row.get("price"));
            Integer dishQuantity = (Integer)row.get("dish_quantity");
            dishMap.put(dish,dishQuantity);
        }
        return dishMap;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM dishes WHERE id=?", id) != 0;
    }

    @Override
    public List<Dish> getAll() {
        return jdbcTemplate.query("SELECT * FROM dishes", ROW_MAPPER);
    }

    private Dish setOrders(Dish d) {
        if (d != null) {
            List<Map<String,Object>> results = jdbcTemplate.queryForList("SELECT o.*, od.dish_quantity FROM orders AS o LEFT JOIN orders_dishes AS od ON o.id = od.order_id WHERE od.dish_id=?", d.getId());
            Map<Order,Integer> orderMap = new HashMap<>();
            for (Map row : results){
                Order order = new Order();
                order.setId((Integer)row.get("id"));
                order.setDateTime(DateTimeUtil.parseLocalDateTime(row.get("date_time")
                                  .toString().substring(0,16),
                                  DateTimeUtil.DATE_TIME_FORMATTER));
                Integer dishQuantity = (Integer)row.get("dish_quantity");
                orderMap.put(order,dishQuantity);
            }
            d.setOrders(orderMap);
        }
        return d;
    }

    private Dish setMenuList(Dish d){
        if (d != null) {
            List<MenuList> dishes = jdbcTemplate.query("SELECT m.id, m.date_time FROM menu_lists AS m JOIN dishes AS d ON m.id = d.menu_list_id WHERE d.id=?",
                    MENU_LIST_ROW_MAPPER, d.getId());
            d.setMenuList(DataAccessUtils.singleResult(dishes));
        }
        return d;
    }
}
