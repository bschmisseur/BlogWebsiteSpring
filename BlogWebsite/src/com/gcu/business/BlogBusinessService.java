package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.BlogAccessInterface;
import com.gcu.model.Blog;
import com.gcu.model.Priciple;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * Blog Business Service instantiate all method to connect the the controller to the database
 */

public class BlogBusinessService implements BlogBusinessInterface
{	
	@SuppressWarnings("rawtypes")
	@Autowired
	BlogAccessInterface doa;
	
	@Autowired
	Priciple priciple;

	/**
	 * @see BlogBusinessInterface
	 */
	@SuppressWarnings("unchecked")
	public boolean postBlog(Blog blog) 
	{		
		return doa.createBlog(blog, priciple.getUserID());
	}

	/**
	 * @see BlogBusinessInterface
	 */
	@SuppressWarnings("unchecked")
	public List<Blog> getAllBlogs() 
	{
		return doa.viewBlogs();
	}

	/**
	 * @see BlogBusinessInterface
	 */
	@SuppressWarnings("unchecked")
	public int editBlog(Blog blog) 
	{
		return doa.editBlog(blog, priciple.getBlogID());
	}

	/**
	 * @see BlogBusinessInterface
	 */
	public int deleteBlog() 
	{
		return doa.deleteBlog(priciple.getBlogID());
	}

	/**
	 * @see BlogBusinessInterface
	 */
	@SuppressWarnings("unchecked")
	public List<Blog> getUserPost(int userID) 
	{
		return doa.viewUserBlog(userID);
	}

	/**
	 * @see BlogBusinessInterface
	 */
	@SuppressWarnings("unchecked")
	public Blog findBlog(Blog blog) 
	{
		return (Blog) doa.findBlog(blog);
	}

	/**
	 * @see BlogBusinessInterface
	 */
	public Blog findBlog(int blogID) 
	{
		return (Blog) doa.findBlog(blogID);
	}
	
	
}
