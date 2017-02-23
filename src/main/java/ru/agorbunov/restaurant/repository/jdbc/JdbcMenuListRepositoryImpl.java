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
import ru.agorbunov.restaurant.repository.MenuListRepository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcMenuListRepositoryImpl implements MenuListRepository {
    private static final BeanPropertyRowMapper<MenuList> ROW_MAPPER = BeanPropertyRowMapper.newInstance(MenuList.class);
    private static final BeanPropertyRowMapper<Dish> DISH_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Dish.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertMenuList;

    @Autowired
    public JdbcMenuListRepositoryImpl(DataSource dataSource) {
        this.insertMenuList = new SimpleJdbcInsert(dataSource)
                .withTableName("menu_lists")
                .usingGeneratedKeyColumns("id");
    }
    @Override
    @Transactional
    public MenuList save(MenuList menuList, int restaurantId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", menuList.getId())
                .addValue("restaurant_id", restaurantId)
                .addValue("date_time", menuList.getDateTime());

        if (menuList.isNew()) {
            Number newKey = insertMenuList.executeAndReturnKey(map);
            menuList.setId(newKey.intValue());
        } else {
            if(namedParameterJdbcTemplate.update("UPDATE menu_lists SET date_time=:date_time WHERE id=:id AND restaurant_id=:restaurant_id", map)==0){
                return null;
            }
        }
        return menuList;
    }

    @Override
    public MenuList get(int id, int restaurantId) {
        List<MenuList> menuLists = jdbcTemplate.query("SELECT * FROM menu_lists WHERE id=? AND restaurant_id=?", ROW_MAPPER, id,restaurantId);
        return DataAccessUtils.singleResult(menuLists);
    }

    @Override
    public MenuList getWith(int id, int restaurantId) {
        List<MenuList> menuLists = jdbcTemplate.query("SELECT * FROM menu_lists WHERE id=? AND restaurant_id=?", ROW_MAPPER, id,restaurantId);
        MenuList result = DataAccessUtils.singleResult(menuLists);
        return setDishes(result);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM menu_lists WHERE id=?", id) != 0;
    }

    @Override
    public List<MenuList> getAll() {
        return jdbcTemplate.query("SELECT * FROM menu_lists", ROW_MAPPER);
    }

    private MenuList setDishes(MenuList m) {
        if (m != null) {
            List<Dish> dishes = jdbcTemplate.query("SELECT * FROM dishes  WHERE menu_list_id=?",
                    DISH_ROW_MAPPER, m.getId());
            m.setDishList(dishes);
        }
        return m;
    }
}
