package com.gcu.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * Blog class is used as a model to hold attributes related to blog such as the blog title and the message of the blog
 */

public class Blog 
{
	//Data Validation Annotations
	@NotNull(message="Blog cannot be null.")
	@Size(min=2, max=30, message="Blog tile must be between 2 and 30 characters.")
	private String blogTitle;
	
	//Data Validation Annotations
	@NotNull(message="Blog Body cannot be null.")
	@Size(min=2, message="Blog body can not be empty.")
	private String blogBody;

	/**
	 * Constructor to set the variables to given information
	 * @param blogTitle
	 * @param blogBody
	 */
	public Blog(String blogTitle, String blogBody) 
	{
		this.blogTitle = blogTitle;
		this.blogBody = blogBody;
	}

	/**
	 * Constructor to initialized, used in our jsp pages to set the model
	 * @param blogTitle
	 * @param blogBody
	 */
	public Blog() 
	{
		this.blogTitle = "";
		this.blogBody = "";
	}

	/**
	 * Getters a Setters for the property of the class
	 * @return String
	 * @param String
	 */
	public String getBlogTitle() 
	{
		return blogTitle;
	}

	public void setBlogTitle(String blogTitle) 
	{
		this.blogTitle = blogTitle;
	}

	public String getBlogBody() 
	{
		return blogBody;
	}

	public void setBlogBody(String blogBody) 
	{
		this.blogBody = blogBody;
	}
	
	
}
