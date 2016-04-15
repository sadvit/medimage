package com.sadvit.repositories;

import com.sadvit.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@Repository
@Transactional(readOnly = false)
public class UserRepository {

	@Autowired
	private HibernateTemplate template;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional(readOnly = false)
	public User getUser(String username) {
        User user = new User();
        user.setName("sadvit");
        user.setHashpwd(encoder.encode("sadvit"));
        template.save(new User());
		return null;
	}

	public List<User> getAllUsers() {
		return template.loadAll(User.class);
	}

	public void addUser(String login, String pass) {
		//String hash = encoder.encode(pass);

		//template.update()
	}

}
