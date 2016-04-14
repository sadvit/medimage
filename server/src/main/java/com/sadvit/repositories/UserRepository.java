package com.sadvit.repositories;

import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@Repository
public class UserRepository {

	@Autowired
	private HibernateTemplate template;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public User getUser(String username) {
		return template.load(User.class, 1);
	}

	public List<User> getAllUsers() {
		return template.loadAll(User.class);
	}

	public void addUser(String login, String pass) {
		//String hash = encoder.encode(pass);

		//template.update()
	}

}
