package com.sadvit.repositories;

import com.sadvit.to.UserTO;
import com.sadvit.models.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
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

	public User getUser(String username) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("username", username));
        List<User> objects = (List<User>) template.findByCriteria(criteria);
        return objects.stream().findFirst().get();
	}

    public User getUser(Integer id) {
        return template.get(User.class, id);
    }

	public List<User> getAllUsers() {
		return template.loadAll(User.class);
	}

	public void addUser(User user) {
        template.save(user);
	}

    public UserTO updateUsername(Integer userId, UserTO info) {
        User user = getUser(userId);
        if (info.getUsername() != null && info.getUsername().length() > 0) {
            user.setUsername(info.getUsername());
            updateUser(user);
        }
        return info;
    }

    public User updateUser(User user) {
        template.update(user);
        return user; // TODO refactoring after
    }

}
