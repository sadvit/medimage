package com.sadvit.models;

/**
 * Created by vitaly.sadovskiy.
 */
public class User {

	private int id;

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

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
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
