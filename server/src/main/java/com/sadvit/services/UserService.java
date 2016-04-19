package com.sadvit.services;

import com.sadvit.dto.UserInfo;
import com.sadvit.models.Authority;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FileSystemService fileSystemService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException	{
        try {
            return userRepository.getUser(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
	}

    public void register(UserInfo user) {
         registerUser(user.getUsername(), user.getPassword());
    }

	public void registerUser(String login, String pass) {
		userRepository.addUser(login, pass, Authority.USER);
		fileSystemService.createUserFolder(login);
	}

	public List<User> loadAllUsers() {
		return userRepository.getAllUsers();
	}

    public String getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        } else {
            return null;
        }
    }

}
