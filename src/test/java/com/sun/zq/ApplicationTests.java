package com.sun.zq;

import com.sun.zq.activemq.MqProducer;
import com.sun.zq.model.User;
import com.sun.zq.service.UserService;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.jms.Destination;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
	private UserService userService;

    @Autowired
	private RedisTemplate redisTemplate;
    @Autowired
	private StringRedisTemplate stringRedisTemplate;

    @Autowired
	private MqProducer producer;


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


	@Test
	public void saveTest() {
		User user = new User();
		user.setAge(18);
		user.setName("zhangsan");
		user.setEmail("zhangsan@qq.com");

		userService.save(user);
	}

	@Test
	public void redisTest() {
		//redisTemplate.opsForValue().set("sun", "test");
		String val = (String)redisTemplate.opsForValue().get("name");
		System.out.println(val);

		val = stringRedisTemplate.opsForValue().get("name");
		System.out.println(val);
	}

	@Test
	public void testActiveMq() {
		Destination destination = new ActiveMQQueue("testMq.queue");
		producer.sendMessage(destination, "消息内容");
	}



}
