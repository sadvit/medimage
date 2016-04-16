package com.sadvit.repositories;

import com.sadvit.enums.Role;
import com.sadvit.models.User;
import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by vitaly.sadovskiy.
 */
@Repository
@Transactional(readOnly = false)
@SuppressWarnings("unchecked")
public class UserRepository {

	@Autowired
	private HibernateTemplate template;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	public User getUser(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("name", username));
        List<User> users = (List<User>) template.findByCriteria(criteria);
        return users.stream().findFirst().get();
	}

	public List<User> getAllUsers() {
		return template.loadAll(User.class);
	}

	public void addUser(String login, String pass, Role role) {
        User user = new User();
        user.setName(login);
        user.setHashpwd(encoder.encode(pass));
        user.setRole(role);
        template.save(user);
	}

    public void updateUser(User user) {
        template.update(user);
    }

}
