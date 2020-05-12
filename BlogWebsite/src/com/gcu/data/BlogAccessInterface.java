package com.gcu.data;

import java.util.List;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * Blog access interface sets up the the method for access the database to change blog information
 */

public interface BlogAccessInterface <T>
{
	/**
	 * CreateBlog takes in a blog object in order to write SQl
	 * statements to the database to store the information. It
	 * also takes in a userId in order to save the blog under the
	 * correct user
	 * @param t - object - object that will be created in the database
	 * @param userID - int - Integer to link the user to the database
	 * @return Boolean - to determine if the blog was created Successfully
	 */
	public boolean createBlog(T t, int userID);
	
	/**
	 * viewBlogs reads the information from the database and
	 * creates blogs objects adding them to a list and returning
	 * the full list 
	 * @return List of blogs - List<Blog> - List containing all blogs in the database
	 */
	public List<T> viewBlogs();
	
	/**
	 * editBlog takes in a over written blog object with a blog ID
	 * to write change the information of the selected blog in the 
	 * database
	 * @param t - object - contain information from the database
	 * @param blogID - int - the id number of the position of the blog in the data base
	 * @return Number of Rows Changed
	 */
	public int editBlog(T t, int blogID);
	
	/**
	 * deleteBlog takes in a blogID to simple remove the blog from the
	 * database
	 * @param blogID - int - the id number of the position of the blog in the data base
	 * @return int - number of rows changed in the database
	 */
	public int deleteBlog(int blogID);
	
	/**
	 * viewUserBlogs takes in a user id in order to read all blogs
	 * from the given user and return them in to a list
	 * @param userID
	 * @return List of blogs
	 */
	public List<T> viewUserBlog(int userID);
	
	/**
	 * findBlog takes in a blog object and compares the information to
	 * the information in the database in order to get the id
	 * @param t - object - contain information from the database
	 * @return t - object - contain information from the database
	 */
	public T findBlog(T t);
	
	/**
	 * findBlog which takes in a blodID returns a blog object full
	 * of information gathered from the database
	 * @param blogID - int - the id number of the position of the blog in the data base
	 * @return t - object - contain information from the database
	 */
	public T findBlog(int blogID);
}
