package com.sadvit.models;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by sadvit on 4/18/16.
 */
@Entity
@Table(name = "AUTHORITIES")
public class Authority implements GrantedAuthority {

    @Transient
    public static final String USER = "ROLE_USER";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authority_id", nullable = false)
    private Long id;

    private String role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Authority(String role) {
        this.role = role;
    }

    public Authority() {
    }

}
