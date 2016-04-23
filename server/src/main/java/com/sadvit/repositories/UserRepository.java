package com.sadvit.repositories;

import com.sadvit.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vitaly.sadovskiy.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}