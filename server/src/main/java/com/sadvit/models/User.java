package com.sadvit.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sadvit.enums.Role;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vitaly.sadovskiy.
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
        @NamedQuery(name = "findUserByName", query = "SELECT U FROM User AS U WHERE U.name = :username"),
        @NamedQuery(name = "findChainsByUser", query = "SELECT USER.chains FROM User AS USER WHERE USER.name = :username"),
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String name;

    @JsonIgnore
	private String hashpwd;

    @Enumerated
	private Role role;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Chain> chains;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Network> networks;

	public Role getRole()
	{
		return role;
	}

	public void setRole(Role role)
	{
		this.role = role;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getHashpwd()
	{
		return hashpwd;
	}

	public void setHashpwd(String hashpwd)
	{
		this.hashpwd = hashpwd;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<Chain> getChains() {
        return chains;
    }

    public void setChains(Set<Chain> chains) {
        this.chains = chains;
    }

    public Set<Network> getNetworks() {
        return networks;
    }

    public void setNetworks(Set<Network> networks) {
        this.networks = networks;
    }

}