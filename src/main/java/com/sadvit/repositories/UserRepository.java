package com.sadvit.repositories;

import com.sadvit.mappers.UserMapper;
import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public User getUser(String username) {
		String SQL = "SELECT * FROM users WHERE login = ?";
		return template.queryForObject(SQL, new Object[]{username}, new UserMapper());
	}

	public List<User> getAllUsers() {
		String SQL = "SELECT * FROM users";
		return template.query(SQL, new UserMapper());
	}

	public void addUser(String login, String pass) {
		String hash = encoder.encode(pass);
		String SQl = "INSERT INTO users (login, hashpwd, role) VALUES (?, ?, ?)";
		template.update(SQl, login, hash, "USER");
	}

}
