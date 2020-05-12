package com.gcu.business;

import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * UserBusinessInterface is a outline for any business service to implement in the back-end
 */

public interface UserBusinessInterface 
{
	/**
	 * Access the data service in order to verify the users credentials and return a boolean
	 * @param user - User - object containing all the information of the properties
	 * @return boolean - variable to see if the user if valid
	 */
	public boolean validateUser(UserCredentials user);
	
	/**
	 * Access the data service in order to write the users information to the database
	 * @param user - User - object containing all the information of the properties 
	 * @return boolean - variable to determine if the user was register successfully
	 */
	public int registerUser(User user);
	
	/**
	 * edit is a method that takes in a user model that is over-ridden with new information and an ID to Update the database with new user
	 * information
	 * @param user - User - object containing all the information of the properties
	 * @param userID - integer to specify the user within the database
	 * @return int - to show how many rows were effected in the database 
	 */
	public int edit(User user, int userID);
	
	/**
	 * delete takes in a userID and sends to the data service to delete the user from the database
	 * @param userID - integer to specify the user within the database
	 * @return int - to show how many rows were effected in the database 
	 */
	public int delete(int userID);
	
	/**
	 * getCurrentUser takes in a userID a returns a filled out user model from the data service
	 * @return user - User - object containing all the information of the properties
	 */
	public User getCurrentUser(int userID);

}
