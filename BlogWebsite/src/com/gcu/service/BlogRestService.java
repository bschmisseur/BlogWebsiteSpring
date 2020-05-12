package com.gcu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.business.BlogBusinessInterface;
import com.gcu.model.Blog;
import com.gcu.model.Priciple;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * BlogRestService give developers access to see the blogs wiht the database
 */

@RestController
@RequestMapping("/v1")
public class BlogRestService 
{
	@Autowired
	BlogBusinessInterface service;
	
	@Autowired
	Priciple priciple;
	
	/**
	 * A method to return all blogs from database in JSON formated data
	 * @return blogPost - List<Blog> - List containing all blogs in the database
	 */
	@GetMapping("/blogs")	
	public List<Blog> getAllBlogs()
	{
		List<Blog> blogPosts = service.getAllBlogs();
		
		return blogPosts;
	}
	
	/**
	 * A method to return blogs from database based on a specified user id in JSON formated data
	 * @param userID - int - Integer to link the user to the database
	 * @return blogPost - List<Blog> - refined list containing blogs in the database
	 */
	@GetMapping("/userBlogs")
	public List<Blog> getUserBlogs(int userID)
	{
		List<Blog> blogPost = service.getUserPost(userID);
		
		return blogPost;
	}
}
