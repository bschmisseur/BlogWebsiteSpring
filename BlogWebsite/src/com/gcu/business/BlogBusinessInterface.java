package com.gcu.business;

import java.util.List;

import com.gcu.model.Blog;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * BlogBuessinessInterface is a outline for any business service to implement in the back-end
 */

public interface BlogBusinessInterface 
{
	/**
	 * postBlog method is called within the controller to access the data service in order to post the blog to the data base
	 * @param blog - Blog - Object containing the title and body of the blog
	 * @return boolean - variable to see if the blog was posted successfully 
	 */
	public boolean postBlog(Blog blog);
	
	/**
	 * getAllBlogs is a method that is used to access the data service in order to retrieve all blogs no matter the user
	 * this will later be implemented for our home page to show the most recent post
	 * @return blogs - List<Blogs> - List containing all blogs in the database
	 */
	public List<Blog> getAllBlogs();
	
	/**
	 * editUser takes in an over-ridden Blog object that and changes the information of the blog in the data base by getting the session
	 * variable of the ID that was set when the use clicked on the blog 
	 * @param blog - Blog - Object containing the title and body of the blog
	 * @return Integer - to show how many rows were effected in the database 
	 */
	public int editBlog(Blog blog);
	
	/**
	 * deteteBlog simply determines the blog that was selected by the session variable and access the data service inorder to delete the
	 * blog from the database
	 * @param blogID - int - the id number of the position of the blog in the data base
	 * @return Integer to show how many rows were effected in the database 
	 */
	public int deleteBlog();
	
	/**
	 * getAllBlogs is a method that is used to access the data service in order to retrieve all blogs based on the given user ID
	 * this will later be implemented for our home page to show the most recent post
	 * @param userID - int - a specified user id to find the blogs posted by that user
	 * @return List of Blogs - List<Blog> - refined list of blogs
	 */
	public List<Blog> getUserPost(int userID); 
	
	/**
	 * findBlog is a method that takes in a blog from the data base to search based on the blog title and body in order to determine the 
	 * ID of the blog
	 * @return blog - Blog - Object containing the title and body of the blog
	 */
	public Blog findBlog(Blog blog);
	
	
	/**
	 * findBlog is a overloaded method in this case of taking in an ID of a blogID to in return give the blog body and title
	 * @param blogID - int - the id number of the position of the blog in the data base
	 * @return blog - Blog - Object containing the title and body of the blog
	 */
	public Blog findBlog(int blogID);
	
}
