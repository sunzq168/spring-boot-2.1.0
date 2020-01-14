package com.sun.zq;

import com.sun.zq.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() {
	}

	@Test
	public void mysqlInsertTest() {
		String sql = "insert into `User`(`name`,`age`,`email`) values ('zhaoliu',23,'zhaoliu@sina.com')";
		jdbcTemplate.execute(sql);
	}

	@Test
	public void mysqlQueryTest() {
		String sql = "select * from User";
		List<User> list = jdbcTemplate.query(sql, new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet resultSet, int i) throws SQLException {
				User user = new User();
				user.setId(resultSet.getInt("id"));
				user.setName(resultSet.getString("name"));
				user.setEmail(resultSet.getString("email"));
				user.setAge(resultSet.getInt("age"));

				return user;
			}
		});

		list.forEach(user -> {
			System.out.println(user);
		});
	}

}
