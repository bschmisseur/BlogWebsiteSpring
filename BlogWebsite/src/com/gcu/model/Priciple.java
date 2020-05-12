package com.gcu.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Priciple object is the websites way of keeping track of the users information throught there time on the webite
 * throught a session scoped class
 * 
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 *
 */
@Component
@Scope("session")
public class Priciple 
{
	/**
	 * Defining the properties of the class
	 */
	private int userID;
	private int blogID;
	private String userName;
	
	/**
	 * Default constructor to initialize the variables
	 */
	public Priciple()
	{
		userID = -1;
		blogID = -1;
	}
	
	
	/**
	 * Getters and setters for the properties of the class
	 * @return object - return the object of the property
	 */
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getBlogID() {
		return blogID;
	}
	public void setBlogID(int blogID) {
		this.blogID = blogID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
