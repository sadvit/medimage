package com.sadvit.repositories;

import com.sadvit.mappers.UserMapper;
import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by vitaly.sadovskiy.
 */
@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate template;

	public User getUser(String username) {
		String SQL = "select * from users where login = ?";
		return template.queryForObject(SQL, new Object[]{username}, new UserMapper());
	}

	public List<User> getAllUsers() {
		String SQL = "select * from users";
		return template.query(SQL, new UserMapper());
	}

}
