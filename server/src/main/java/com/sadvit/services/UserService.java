package com.sadvit.services;

import com.sadvit.enums.Role;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Principal;
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
		User user = userRepository.getUser(username);
		List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_" + user.getRole().toString());
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getHashpwd(), authorityList);
	}

	public Authentication getCurrentAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

    public String getCurrentUserName() {
        return getCurrentAuthentication().getName();
    }

	public void registerUser(String login, String pass) {
		userRepository.addUser(login, pass, Role.USER);
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
