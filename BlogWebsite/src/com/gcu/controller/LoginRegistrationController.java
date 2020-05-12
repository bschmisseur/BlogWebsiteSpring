package com.gcu.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.business.UserBusinessInterface;
import com.gcu.model.Priciple;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * LoginRegisterController is used to connect the views for register and login with the model of user
 */

@Controller
@RequestMapping("/loginRegistration")
public class LoginRegistrationController 
{
	UserBusinessInterface service;
	
	@Autowired
	Priciple priciple;
	
	Logger logger = LoggerFactory.getLogger(LoginRegistrationController.class);
	
	/**
	 * Displays the registration form to the user with an empty user object
	 * @return ModelAndView - sends the user to the registration page withe a blank user
	 */
	@RequestMapping(path="/registrationForm", method=RequestMethod.GET)
	public ModelAndView displayRegistrationForm()
	{
		logger.info("Displaying Registration Form");
		return new ModelAndView("registration", "user", new User());
	}
	
	/**
	 * After the user is presses submit on the registration form this method is called to first validate the 
	 * input of the user is correct. Then will add the user to the data base and send them to the login page to complete 
	 * the login process
	 * @param user - User - object containing all variables of the user 
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView
	 */
	@RequestMapping(path="/registerUser", method=RequestMethod.POST)
	public ModelAndView registerUser(@Valid @ModelAttribute("user") User user, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("registration", "user", user);
		}
		
		try
		{
			//Adding the user to the data base
			int returnNum = service.registerUser(user);
			
			//If all Database actions were completed successfully
			if(returnNum > 0)
			{
				return new ModelAndView("login", "user" , user.getCredentials());
			}
			
			//If a user was already created with the same user name
			else if(returnNum == -1)
			{
				ModelAndView modelAndView = new ModelAndView();
				user.setCredentials(new UserCredentials());
				modelAndView.setViewName("registration");
				modelAndView.addObject("user", user);
				modelAndView.addObject("message", new String("That User Name is Already Taken"));
				return modelAndView; 
			}
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPageIndex");
		}

		return null;
	}
	
	/**
	 * Displays the user the login form and send a empty user to the view in order to fill with information inputed by the user
	 * @return ModelAndView - send the user to the login page with an empty user credentials objects
	 */
	@RequestMapping(path="/loginForm", method=RequestMethod.GET)
	public ModelAndView displayLoginForm()
	{
		return new ModelAndView("login", "user", new UserCredentials());
	}
	
	/**
	 * After the user submits their user name and password the loginUser method is called to validate
	 * their information and send them to the home page
	 * @param user - User - object containing all variables of the user 
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView - returns the user to a new page based on the information they had given
	 */
	@RequestMapping(path="/loginUser", method=RequestMethod.POST)
	public ModelAndView loginUser(@Valid @ModelAttribute("user")UserCredentials user, BindingResult result)
	{
		//Validates the users information for data validation errors 
		if(result.hasErrors())
		{
			return new ModelAndView("login", "user", user);
		}
		
		try
		{		
			//Validates users information 		
			if(service.validateUser(user))
			{
				HomePageController homePageObject = new HomePageController();
				return homePageObject.displayHomePage(user);
			}
			
			else
			{
				ModelAndView modelAndView = new ModelAndView("login", "user", user);
				modelAndView.addObject("message", "Invalid Credentials");
				return modelAndView;
			}
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPageIndex");
		}

	}
	
	/**
	 * Displays Page with all the user information
	 * @return ModelAndView - display the page with current users information
	 */
	@RequestMapping(path="/userInformation", method=RequestMethod.GET)
	public ModelAndView displayUserInfo()
	{
		try
		{
			ModelAndView modelAndView = new ModelAndView("userInformation");
		
			int userID = priciple.getUserID();
			
			modelAndView.addObject("user", service.getCurrentUser(userID));
			return modelAndView;
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPageIndex");
		}
	}
	
	/**
	 * Displayed a filled in form with the users information in order to give them the ability to edit there information
	 * @return - send the user to the edit user with an empty user model
	 */
	@RequestMapping(path="/editUserForm", method=RequestMethod.GET)
	public ModelAndView displayEditUserFrom()
	{
		int userID = priciple.getUserID();
		User user = service.getCurrentUser(userID);
		
		ModelAndView modelAndView = new ModelAndView("editUser", "user", user);
		return modelAndView;
	}
	
	/**
	 * Once the user saves the changed from the editing page the information is then sent to the business service in order to be updated 
	 * in the database
	 * @param user - User - object containing all variables of the user 
	 * @param result - BindingResult - object used to determine if the information inputed was valid
	 * @return ModelAndView - redirects the user to a different page if there information is correct
	 */
	@RequestMapping(path="/editUser", method=RequestMethod.POST)
	public ModelAndView editUser(@Valid @ModelAttribute("user") User user, BindingResult result)
	{
		if(result.hasErrors())
		{
			return new ModelAndView("editUser", "user", user);
		}
		
		int userID = priciple.getUserID();
		
		try
		{
			//Adding the user to the data base
			service.edit(user, userID);
			
			return new ModelAndView("userInformation");
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPage");
		}
		
	}
	
	/**
	 * Deletes the users information of the current user
	 * @return ModelAndView - send the user to the welcome page
	 */
	@RequestMapping(path="/deleteUser", method=RequestMethod.GET)
	public ModelAndView deleteUser()
	{
		int userID = priciple.getUserID();
		
		try
		{
			service.delete(userID);
			
			return new ModelAndView("welcomePage");
		}
		
		catch(Exception e)
		{
			return new ModelAndView("errorPage");
		}
	}
	
	/**
	 * setUserBusinessService is used to inject out data service through IoC and Dependecy Injection
	 * @param service - UserBusinessInterface - service in order to interact with the business service
	 */
	@Autowired
	public void setUserBusinessService(UserBusinessInterface service)
	{
		this.service = service;
	}
}
