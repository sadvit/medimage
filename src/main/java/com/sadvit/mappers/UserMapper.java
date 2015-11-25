package com.sadvit.mappers;

import com.sadvit.models.Role;
import com.sadvit.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by vitaly.sadovskiy.
 */
public class UserMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("login"));
		user.setHashpwd(rs.getString("hashpwd"));
		user.setRole(Role.valueOf(rs.getString("role")));
		return user;
	}

}
