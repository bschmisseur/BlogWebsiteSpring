package com.gcu.data;

import java.util.List;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * User access interface sets up the the method for access the database to change user information
 */

public interface UserAccessInterface <T, Q> 
{
	/**
	 * creatUser takes in a full user model and writes to the database with SQL commands in order to save the information of the user
	 * @param t - object - contain information from the database
	 * @return int - number of rows changed to the database
	 */
	public int createUser(T t);
	
	/**
	 * getAllUsers gathers information from the database of all users, creates user models, and returns of list of the user models
	 * @return List of Users - List<User> - list od all users from the database
	 */
	public List<T> getAllUsers();
	
	/**
	 * getAllUserCredentials gathers the user names and passwords form the database and creates user credentials objects in order to
	 * populate a list and return the list
	 * @return List of credentials - List<UserCredentials> - list of all user names and passwords from the database
	 */
	public List<Q> getAllUserCredentials();
	
	/**
	 * getUser takes in a userID in order to return a user model containing all the users information from the database
	 * @param userID - integer to specify the user within the database
	 * @return user - User - object containing all the information of the properties
	 */
	public T getUser(int userID);
	
	/**
	 * Edit user takes in a over written user object and user ID in order to update the information within the database 
	 * @param t - object - contain information from the database
	 * @param userID - integer to specify the user within the database
	 * @return int - number of rows changed to the database
	 */
	public int editUser(T t, int userID);
	
	/**
	 * deleteUser takes in a userID and find the user in the database in order to remove there information form the website 
	 * @param userID - integer to specify the user within the database
	 * @return Number of rows changed
	 */
	public int deleteUser(int userID);
	
	/**
	 * Get getCurrentUserID takes in a user Credentials object and find the specified user to set the session variable of user ID
	 * @param q - object - contain information from the database
	 * @return userID - integer to specify the user within the database ID
	 */
	public int getCurrentUserID(Q q);
}
