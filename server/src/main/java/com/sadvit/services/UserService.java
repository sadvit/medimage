package com.sadvit.services;

import com.sadvit.models.Authority;
import com.sadvit.models.User;
import com.sadvit.repositories.UserRepository;
import com.sadvit.to.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

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
    private FileSystemService systemService;

    @Autowired
    private ConversionService conversionService;

    @Value("${medimage.content}")
    private String content;

    protected static final String SEPARATOR = "/";

    private static final String IMAGES = "images";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }
            return user;
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
    }

    private Set<Authority> createDefaultAuthorities() {
        Authority defaultAuthority = new Authority(Authority.USER);
        List<Authority> authorityList = Collections.singletonList(defaultAuthority);
        return new HashSet<>(authorityList);
    }

    public void register(UserTO userTO) {
        User user = conversionService.convert(userTO, User.class);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setPassword(encoder.encode(userTO.getNewPassword()));
        user.setAuthorities(createDefaultAuthorities());
        String folder = UUID.randomUUID().toString();
        user.setFolder(folder);
        userRepository.save(user);
        systemService.createUserFolder(folder);
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

    public UserTO getUser(Long id) {
        User user = userRepository.findOne(id);
        return conversionService.convert(user, UserTO.class);
    }

    public UserTO updateUser(Long userId, UserTO userTO) {
        User oldUser = userRepository.findOne(userId);
        if (oldUser != null) {
            User newUser = conversionService.convert(userTO, User.class);
            newUser.setId(userId);
            if (userTO.getNewPassword() != null) {
                boolean matches = encoder.matches(userTO.getCurrentPassword(), oldUser.getPassword());
                if (matches) {
                    newUser.setPassword(encoder.encode(userTO.getNewPassword()));
                } else {
                    throw new BadCredentialsException("Incorrect current password");
                }
            }
            newUser = userRepository.save(newUser);
            return conversionService.convert(newUser, UserTO.class);
        } else {
            throw new SessionAuthenticationException("User session is lost");
        }

    }

}
