package com.sadvit.repositories;

import com.sadvit.models.Authority;
import com.sadvit.models.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        criteria.add(Restrictions.eq("username", username));
        List<User> objects = (List<User>) template.findByCriteria(criteria);
        return objects.stream().findFirst().get();
	}

	public List<User> getAllUsers() {
		return template.loadAll(User.class);
	}

	public void addUser(String login, String pass, String role) {
        User user = new User();
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setUsername(login);
        user.setPassword(encoder.encode(pass));
        List<Authority> authorityList = Arrays.asList(new Authority(role));
        Set<Authority> authorities = new HashSet<>(authorityList);
        user.setAuthorities(authorities);
        template.save(user);
	}

    public void updateUser(User user) {
        template.update(user);
    }

}
