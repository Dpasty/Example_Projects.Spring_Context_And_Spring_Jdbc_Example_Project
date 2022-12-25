package school21.spring.service.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private DataSource ds;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(@Qualifier("hikariDataSource") DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<User> findById(Long id) {
        List<User> list = new ArrayList<>();
        if (id != null) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
            list = jdbcTemplate.query("SELECT * FROM users WHERE id = ?", new UserRowMapper(), id);
        }
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    @Override
    public List<User> findAll() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
    }

    @Override
    public void save(User entity) {
        if (entity != null) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
            jdbcTemplate.update("INSERT INTO users VALUES(DEFAULT, ?, ?)", entity.getEmail(), entity.getTempPassword());
        }
    }

    @Override
    public void update(User entity) {
        if (entity != null) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
            jdbcTemplate.update("UPDATE users SET id = ?, email = ? WHERE id = ?", entity.getId(), entity.getEmail(), entity.getId());
        }
    }

    @Override
    public void delete(Long id) {
        if (id != null){
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
            jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        List<User> list = new ArrayList<>();
        if (email != null) {
            JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
            list = jdbcTemplate.query("SELECT * FROM users WHERE email = ?", new UserRowMapper(), email);
        }
        return list.isEmpty() ? Optional.empty() : Optional.of(list.get(0));
    }

    public class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();

            user.setId(rs.getLong("id"));
            user.setEmail(rs.getString("email"));
            user.setTempPassword(rs.getString("temp_password"));

            return user;
        }
    }
}
