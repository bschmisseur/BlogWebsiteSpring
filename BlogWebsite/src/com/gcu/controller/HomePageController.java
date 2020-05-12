package com.gcu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gcu.model.Priciple;
import com.gcu.model.UserCredentials;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * HomePageController us a class used to pass through data from pages as well as direct the user to the correct view
 */

@Controller
@RequestMapping("/welcomepage")
public class HomePageController 
{
	@Autowired
	Priciple priciple;
	
	/**
	 * displayHomePage is called from the login page when the validation was successful
	 * @param user - User - object containing all variables of the user 
	 * @return ModelAndView - send the user to the home page
	 */
	@RequestMapping("/homepage")
	public ModelAndView displayHomePage(UserCredentials user)
	{
		return new ModelAndView("homePage");
	}
}
