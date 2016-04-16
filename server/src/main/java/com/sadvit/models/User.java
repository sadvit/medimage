package com.sadvit.models;

import com.sadvit.enums.Role;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by vitaly.sadovskiy.
 */
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    private String name;

	private String hashpwd;

	private Role role;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Chain> chains;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Network> networks;

    @Enumerated
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