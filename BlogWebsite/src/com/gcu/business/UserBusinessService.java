package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.UserAccessInterface;
import com.gcu.model.Priciple;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * User Business Service instantiate all method to connect the the controller to the database
 */

public class UserBusinessService implements UserBusinessInterface
{
	@SuppressWarnings("rawtypes")
	@Autowired
	UserAccessInterface doa; 
	
	@Autowired
	Priciple priciple;
	
	/**
	 * @see UserBuesinessInterface
	 */
	@SuppressWarnings({ "unchecked"})
	public boolean validateUser(UserCredentials user) 
	{
		//Initialize variable to set the user valid or not
		boolean validUser = false;
		
		//Gets the full list of users from the Database
		List<UserCredentials> userList = doa.getAllUserCredentials();
		
		//For loop to iterate through the list of users to match the credentials
		for(int i = 0; i < userList.size(); i ++)
		{
			if(userList.get(i).getUserName().equals(user.getUserName()) && userList.get(i).getPassword().equals(user.getPassword()))
			{
				validUser = true;
				priciple.setUserID(doa.getCurrentUserID(userList.get(i)));
				priciple.setUserName(userList.get(i).getUserName());
				break;
			}
		}
	
		return validUser;
	}

	/**
	 * @see UserBuesinessInterface
	 */
	@SuppressWarnings("unchecked")
	public int registerUser(User user) 
	{		
		//Add user to database
		return doa.createUser(user);
	}

	/**
	 * @see UserBuesinessInterface
	 */
	@SuppressWarnings("unchecked")
	public int edit(User user, int userID) 
	{
		return doa.editUser(user, userID);
	}

	/**
	 * @see UserBuesinessInterface
	 */ 
	public int delete(int userID) 
	{
		return doa.deleteUser(userID);
	}

	/**
	 * @see UserBuesinessInterface
	 */
	public User getCurrentUser(int userID) 
	{
		return (User)doa.getUser(userID);
	}

	
	
}
