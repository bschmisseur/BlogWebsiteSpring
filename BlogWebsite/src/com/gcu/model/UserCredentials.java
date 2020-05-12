package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * This User Credentials model is used to hold data relating to the users user name and password
 */

public class UserCredentials
{
	//Data Validation Annotations
	@NotNull(message="User name cannot be null.")
	@Size(min=2, max=30, message="User name must be between 2 and 30 characters.")
	private String userName;
	
	//Data Validation Annotations
	@NotNull(message="Pass word cannot be null.")
	@Size(min=2, max=30, message="Password must be between 2 and 30 characters.")
	private String password;
	
	/**
	 * Constructor to initialize the two variables
	 */
	public UserCredentials() {
		this.userName = "";
		this.password = "";
	}
	
	public UserCredentials(String username, String password)
	{
		this.userName = username;
		this.password = password;
		}

	/**
	 * Getters and Setters for the properties of the class
	 * @return String
	 * @param String
	 */
	public String getUserName() 
	{
		return userName;
	}

	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	
}
