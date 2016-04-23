package com.sadvit.configs;

import com.sadvit.components.CacheHeaderWriter;
import com.sadvit.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.CacheControlHeadersWriter;
import org.springframework.security.web.header.writers.DelegatingRequestMatcherHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * Created by vitaly.sadovskiy.
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpAuthenticationEntryPoint authenticationEntryPoint;

    private static final RequestMatcher CACHE_MATCHER = new AntPathRequestMatcher("/images/*", "GET");

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers("/chains/**").hasRole("USER")
                .antMatchers("/images/**").hasRole("USER")
                .antMatchers("/networks/**").hasRole("USER")
                .antMatchers("/process/**").hasRole("USER")
                .antMatchers("/statistics/**").hasRole("USER")
                .antMatchers("/results/**").hasRole("USER")
                .antMatchers("/temp/**").hasRole("USER")
                .antMatchers("/users/**").hasRole("USER")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .disable()
                .headers()
                .cacheControl()
                .disable()
                .addHeaderWriter(new DelegatingRequestMatcherHeaderWriter(
                        CACHE_MATCHER,
                        new CacheHeaderWriter())
                )
                .addHeaderWriter(new DelegatingRequestMatcherHeaderWriter(
                        new NegatedRequestMatcher(CACHE_MATCHER),
                        new CacheControlHeadersWriter())
                );

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
