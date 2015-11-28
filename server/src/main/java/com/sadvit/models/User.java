package com.sadvit.models;

/**
 * Created by vitaly.sadovskiy.
 */
public class User extends Entity {

    private String name;

	private String hashpwd;

	private Role role;

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

}
