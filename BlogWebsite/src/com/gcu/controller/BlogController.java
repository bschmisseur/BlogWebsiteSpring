package com.gcu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.BlogBusinessInterface;
import com.gcu.model.Blog;
import com.gcu.model.Priciple;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * BlogController is used for all pages that have to do with information towards Blogs. It is used to pass through data to the Blog
 * Model and redurect the user to the correct views.
 */

@Controller
@RequestMapping("/blog")
public class BlogController 
{
	// Service used for any back-end action that must be done to a blog
	BlogBusinessInterface service;
	
	@Autowired
	Priciple priciple;
	
	/**
	 * Called from the homepage when the user would like to add a blog to the website
	 * @return ModelAndView - send the user to a new page with an empty blog object
	 */
	@RequestMapping(path="/blogForm", method=RequestMethod.GET)
	public ModelAndView displayBlogForm()
	{
		return new ModelAndView("blogForm", "blog", new Blog());
	}
	
	/**
	 * postBlog takes in a blog from the blogForm view and aceess the business serive in order to write it to the database
	 * @param blog - Blog - Object containing the title and body of the blog
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView - based on the result of the database will redirect the user
	 */
	@RequestMapping(path="/postBlog", method=RequestMethod.POST)
	public ModelAndView postBlog(@Valid @ModelAttribute("blog") Blog blog, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("blogForm", "blog", blog);
		}
		
		try
		{
			//Access Database to post blog
			service.postBlog(blog);
			
			return this.viewUserPost();
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * Takes in the information of the blog and reaches out to the business service in order to set the page to view the whole blog
	 * @param blogTitle - String - string containing the title of the blog
	 * @param blogBody - String - string containing the body of the blog
	 * @return ModelAndView - send the user to view the full blog with the blog object
	 */
	@RequestMapping(path="/fullBlogPost", method=RequestMethod.POST)
	public ModelAndView displayBlogPost(String blogTitle, String blogBody)
	{
		try
		{
			Blog currentBlog = new Blog(blogTitle, blogBody);
			
			currentBlog = service.findBlog(currentBlog);
			
			return new ModelAndView("viewBlogPost", "blog", currentBlog);
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * Sends the user to an edit page where they can edit their own blog and save the changes to the database
	 * @return ModelAndView - sends the user to the Edit Blog From with the oringal blog
	 */
	@RequestMapping(path="/editBlogForm", method=RequestMethod.GET)
	public ModelAndView displayEditBlogForm()
	{
		return new ModelAndView("editBlog", "blog", service.findBlog(priciple.getBlogID()));
	}
	
	/**
	 *  editUser is called when the user saves changes and then the inforamtion needs to be changed in the database
	 * @param blog - Blog - Object containing the title and body of the blog
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView - send the user to to view the edited post
	 */
	@RequestMapping(path="/editBlogPost", method=RequestMethod.POST)
	public ModelAndView editBlogPost(@Valid @ModelAttribute("blog") Blog blog, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("editBlog", "blog", blog);
		}
		
		try
		{
			//Adding the user to the data base
			service.editBlog(blog);
			
			return new ModelAndView("viewBlogPost");
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPage");
		}
		
	}
	
	/**
	 * DeleteBlog calls the deleteBlog method within the business service in order to delete the selected blog 
	 * @return ModelAndView - sends the user to view all their post
	 */
	@RequestMapping(path="/deleteBlog", method=RequestMethod.POST)
	public ModelAndView deleteBlog()
	{
		try
		{
			service.deleteBlog();
			
			return this.viewUserPost();
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * Displays all the post the current user has posted
	 * @return - Sends the user to the page of all their post 
	 */
	@RequestMapping(path="/viewUserPost", method=RequestMethod.GET)
	public ModelAndView viewUserPost()
	{	
		ModelAndView modelAndView = new ModelAndView("usersPost", "blogs", service.getUserPost(priciple.getUserID()));
		modelAndView.addObject("userID", priciple.getUserID());
		return modelAndView;
	}
	
	/**
	 * setBlogBusinessService is used to inject out data service through IoC and Dependecy Injection
	 * @param service - BlogBusinessInterface - service inorder to interact with the business layer
	 */
	@Autowired
	public void setBlogBusinessService(BlogBusinessInterface service)
	{
		this.service = service;
	}
}
