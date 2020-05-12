package com.gcu.model;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/** 
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * User model is used as a POJO to store information of the user
 */

public class User 
{
	//Data Validation Annotations
	@NotNull(message="First name cannot be null.")
	@Size(min=2, max=30, message="First name must be between 2 and 30 characters.")
	private String firstName;
	
	//Data Validation Annotations
	@NotNull(message="Last name cannot be null.")
	@Size(min=2, max=30, message="Last name must be between 2 and 30 characters.")
	private String lastName;
	
	//Data Validation Annotations
	@NotNull(message="Email cannot be null.")
	@Email(message="Must be a valid email.")
	@Size(min=2, max=30, message="Email must be between 2 and 30 characters.")
	private String email;
	
	//Data Validation Annotations
	@NotNull(message="Phone Number cannot be null.")
	@Pattern(regexp="([0-9]{3}+-[0-9]{3}+-[0-9]{4})|([0-9]{10})", message="Please enter a valid phone number.")
	private String phoneNumber;
	
	@Valid
	private UserCredentials credentials;
		
	//Constructor 
	public User(String firstName, String lastName, String email, String phoneNumber, String userName, String password)
	{
		this.firstName = firstName;
		this.lastName= lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		
		this.credentials = new UserCredentials();
		
		this.credentials.setUserName(userName);
		this.credentials.setPassword(password);
	}
	
	//For test purposes only
	public User()
	{
		this.firstName = "";
		this.lastName = "";
		this.email = "";
		this.phoneNumber = "";
		this.credentials = new UserCredentials();
	}
	
	//Getters and Setters for all varibles
	public String getFirstName() 
	{
		return firstName;
	}
	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}
	
	public String getLastName() 
	{
		return lastName;
	}
	
	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber)
	{
		this.phoneNumber = phoneNumber;
	}

	public UserCredentials getCredentials() {
		return credentials;
	}

	public void setCredentials(UserCredentials credentials) {
		this.credentials = credentials;
	}
}
