package ru.agorbunov.restaurant.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.agorbunov.restaurant.model.Order;
import ru.agorbunov.restaurant.model.Role;
import ru.agorbunov.restaurant.model.User;
import ru.agorbunov.restaurant.repository.UserAndRestaurantRepository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Admin on 21.02.2017.
 */
@Repository
@Transactional(readOnly = true)
public class JdbcUserRepositoryImpl implements UserAndRestaurantRepository<User> {

    private static final BeanPropertyRowMapper<User> ROW_MAPPER = BeanPropertyRowMapper.newInstance(User.class);
    private static final BeanPropertyRowMapper<Order> ORDER_ROW_MAPPER = BeanPropertyRowMapper.newInstance(Order.class);
    private static final RowMapper<Role> ROLE_ROW_MAPPER = (rs, rowNum) -> Role.valueOf(rs.getString("role"));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcInsert insertUser;

    @Autowired
    public JdbcUserRepositoryImpl(DataSource dataSource) {
        this.insertUser = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    @Transactional
    public User save(User user) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword());

        if (user.isNew()) {
            Number newKey = insertUser.executeAndReturnKey(map);
            user.setId(newKey.intValue());
            insertRoles(user);
        } else {
            deleteRoles(user);
            insertRoles(user);
            namedParameterJdbcTemplate.update(
                    "UPDATE users SET name=:name, password=:password, email=:email WHERE id=:id", map);
        }
        return user;
    }

    @Override
    public User get(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        return setRoles(DataAccessUtils.singleResult(users));
    }

    @Override
    public User getWith(int id) {
        List<User> users = jdbcTemplate.query("SELECT * FROM users WHERE id=?", ROW_MAPPER, id);
        User result = DataAccessUtils.singleResult(users);
        setRoles(result);
        setOrders(result);
        return result;
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id) != 0;
    }

    @Override
    public List getAll() {
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet("SELECT * FROM roles");
        Map<Integer, Set<Role>> map = new HashMap<>();
        while (rowSet.next()) {
            Set<Role> roles = map.computeIfAbsent(rowSet.getInt("user_id"), userId -> EnumSet.noneOf(Role.class));
            roles.add(Role.valueOf(rowSet.getString("role")));
        }
        List<User> users = jdbcTemplate.query("SELECT * FROM users", ROW_MAPPER);
        users.forEach(u -> u.setRoles(map.get(u.getId())));
        return users;
    }

    private void insertRoles(User u) {
        Set<Role> roles = u.getRoles();
        Iterator<Role> iterator = roles.iterator();

        jdbcTemplate.batchUpdate("INSERT INTO roles (user_id, role) VALUES (?, ?)",
                new BatchPreparedStatementSetter() {
                    @Override
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, u.getId());
                        ps.setString(2, iterator.next().name());
                    }

                    @Override
                    public int getBatchSize() {
                        return roles.size();
                    }
                });
    }

    private void deleteRoles(User u) {
        jdbcTemplate.update("DELETE FROM roles WHERE user_id=?", u.getId());
    }

    private User setRoles(User u) {
        if (u != null) {
            List<Role> roles = jdbcTemplate.query("SELECT role FROM roles  WHERE user_id=?",
                                                    ROLE_ROW_MAPPER, u.getId());
            u.setRoles(EnumSet.copyOf(roles));
        }
        return u;
    }

    private User setOrders(User u) {
        if (u != null) {
            List<Order> orders = jdbcTemplate.query("SELECT * FROM orders  WHERE user_id=?",
                    ORDER_ROW_MAPPER, u.getId());
            u.setOrders(orders);
        }
        return u;
    }
}
