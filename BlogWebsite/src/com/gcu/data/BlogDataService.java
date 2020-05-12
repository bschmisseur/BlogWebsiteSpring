package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.Blog;
import com.gcu.model.Priciple;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * Blog Date Service instantiate the methods for the data access service
 */

public class BlogDataService implements BlogAccessInterface<Blog>
{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	@Autowired
	Priciple priciple;
	
	/**
	 * @See BlogAccessInterface
	 */
	public boolean createBlog(Blog blog, int userID) 
	{
		//Creates a SQL to be filled in later
		String sqlInsert = "INSERT INTO BLOG_POST(ID, BLOG_TITLE, BLOG_BODY, USER_TABLE_ID) VALUES (NULL, ?, ?, ?)";
		
		try
		{
			//Fills in the JDBC template with the SQL statment and variables
			int rows = jdbcTemplateObject.update(sqlInsert, blog.getBlogTitle(), blog.getBlogBody(), userID);
			return rows == 1 ? true : false; 
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * @See BlogAccessInterface
	 */
	public List<Blog> viewBlogs() 
	{
		//Creates a SQL to be filled in later
		String sql = "SELECT * FROM BLOG_POST";
		
		//Creates an array list of blogs to be later filled with information from the database
		List<Blog> blogList = new ArrayList<Blog>();
		
		try
		{
			//Gets a set of data from the database containing all blogs in the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				//Loops through each row in the data set in order to create Blog objects from the database 
				blogList.add(new Blog(srs.getString("BLOG_TITLE"), srs.getString("BLOG_BODY")));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		//Returns the filled in list
		return blogList;
	}
	
	/**
	 * @See BlogAccessInterface
	 */
	public int editBlog(Blog blog, int blogID) 
	{
		//Variable to determine the out come of the Update sql command
		int returnNum = 0;
		
		//Creates a SQL to be filled in later
		String sql = "UPDATE `BLOG_POST` SET `BLOG_TITLE` = ?, `BLOG_BODY` = ? WHERE `BLOG_POST`.`ID` = ?;";
		
		try
		{
			//Fills in the JDBC template with the SQL statement and variables
			int rowsChanged = jdbcTemplateObject.update(sql, blog.getBlogTitle(), blog.getBlogBody(), blogID);
			returnNum = rowsChanged;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return returnNum;
	}
	
	/**
	 * @See BlogAccessInterface
	 */
	public int deleteBlog(int blogID) 
	{
		//Variable to determine the out come of the Update sql command
		int returnNum = 0;
		
		//Creates a SQL to be filled in later
		String sql = "DELETE FROM `BLOG_POST` WHERE `BLOG_POST`.`ID` = ?";
		
		try
		{
			//Fills in the JDBC template with the SQL statement and variables
			int rowsChanged = jdbcTemplateObject.update(sql, blogID);
			returnNum = rowsChanged;
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return returnNum;
	}
	
	/**
	 * @See BlogAccessInterface
	 */
	public List<Blog> viewUserBlog(int userID) 
	{
		//Creates a SQL to be filled in later
		String sql = "SELECT * FROM BLOG_POST WHERE USER_TABLE_ID=?";
		
		//Creates an array list of blogs to be later filled with information from the database
		List<Blog> blogList = new ArrayList<Blog>();
		
		try
		{
			//Gets a set of data from the database containing all blogs in the database
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, userID);
			while(srs.next())
			{
				//Loops through each row in the data set in order to create Blog objects from the database 
				blogList.add(new Blog(srs.getString("BLOG_TITLE"), srs.getString("BLOG_BODY")));
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return blogList;
	}
	
	/**
	 * @See BlogAccessInterface
	 */
	public Blog findBlog(Blog blog) 
	{
		//Creates a SQL to be filled in later
		String sql = "SELECT * FROM BLOG_POST WHERE BLOG_TITLE=? AND BLOG_BODY=?;";
		
		try
		{
			//Gets a set of data from the database containing the blog that matched the parameters
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, blog.getBlogTitle(), blog.getBlogBody());
			
			//Gets the the ID of the blog and sets the session variable of the id
			srs.next();
			priciple.setBlogID(srs.getInt("ID"));
			
			//returns back the blog information 
			return new Blog(srs.getString("BLOG_TITLE"), srs.getString("BLOG_BODY"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * @See BlogAccessInterface
	 */
	public Blog findBlog(int blogID) 
	{
		//Creates a SQL to be filled in later
		String sql= "SELECT * FROM BLOG_POST WHERE ID=?;";
		
		try
		{
			//Gets a set of data from the database containing the blog that matched the parameters
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, blogID);
			
			//Gets the the ID of the blog and sets the session variable of the id
			srs.next();
			priciple.setBlogID(srs.getInt("ID"));
			
			//returns back the blog information 
			return new Blog(srs.getString("BLOG_TITLE"), srs.getString("BLOG_BODY"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
	}
	
	/**
	 * setDataSouce takes in a DataSource from our web.xml in order to create a dataSource and JDBC Template Object used to 
	 * connect and perform CRUD action to the database
	 * @param ds - DataSource - to connect the sql command to the databses
	 */
	public void setDataSource(DataSource ds)
	{
		this.dataSource = ds;
		this.jdbcTemplateObject = new JdbcTemplate(ds);
	}
}
