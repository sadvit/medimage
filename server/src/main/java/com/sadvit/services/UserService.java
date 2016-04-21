package com.sadvit.services;

import com.sadvit.dto.UserInfo;
import com.sadvit.models.Authority;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * Created by vitaly.sadovskiy.
 */
@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private FileSystemService fileSystemService;

    @Value("${medimage.content}")
    private String content;

    protected static final String SEPARATOR = "/";

    private static final String IMAGES = "images";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException	{
        try {
            return userRepository.getUser(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
	}

    private Set<Authority> createDefaultAuthorities () {
        Authority defaultAuthority = new Authority(Authority.USER);
        List<Authority> authorityList = Collections.singletonList(defaultAuthority);
        return new HashSet<>(authorityList);
    }

	public void register(UserInfo info) {
        User user = info.convertToEntity();
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(encoder.encode(info.getPassword()));
        user.setAuthorities(createDefaultAuthorities());
        String folder = UUID.randomUUID().toString().replace("-", "");
        user.setFolder(folder);
		userRepository.addUser(user);
		fileSystemService.createUserFolder(folder);
	}

	public List<User> loadAllUsers() {
		return userRepository.getAllUsers();
	}

    public User getCurrentUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return (User) authentication.getPrincipal();
        } catch (Exception e) {
            throw new SessionAuthenticationException("User session is not found");
        }
    }

    public String getUserFolderPath() {
        User user = getCurrentUser();
        return content + SEPARATOR + user.getFolder() + SEPARATOR + IMAGES;
    }

    public String getUserFilePath(String id) {
        return getUserFolderPath() + SEPARATOR + id;
    }

    public UserInfo getUser(Integer id) {
        User user = userRepository.getUser(id);
        UserInfo info = new UserInfo();
        info.setUsername(user.getUsername());
        return info;
    }

    public UserInfo updateUsername(Integer userId, UserInfo info) {
        return userRepository.updateUsername(userId, info);
    }

    public void updatePassword(Integer userId, UserInfo info) {
        User user = userRepository.getUser(userId);
        boolean matches = encoder.matches(info.getCurrentPassword(), user.getPassword());
        if (matches) {
            user.setPassword(encoder.encode(info.getPassword()));
            userRepository.updateUser(user);
        } else {
            throw new BadCredentialsException("Incorrect password");
        }
    }

}
