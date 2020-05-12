package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.gcu.exception.DatabaseException;
import com.gcu.model.User;
import com.gcu.model.UserCredentials;

/**
 * @author Hermes Mimini and Bryce Schmisseur
 * CST-341-TF300
 * CLC Final Project
 * User Date Service instantiate the methods for the data access service
 */

public class UserDataService implements UserAccessInterface<User, UserCredentials> 
{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	/**
	 * @see UserAccessInterface
	 */
	public int createUser(User user) 
	{
		int returnNum = 0;
		
		//Checks if the user is valid
		String sqlValidUser = "SELECT * FROM USER_CREDENTIAL WHERE USERNAME=?;";
		
		try
		{
			SqlRowSet srsFind = jdbcTemplateObject.queryForRowSet(sqlValidUser, user.getCredentials().getUserName());
			
			if (srsFind.next() == false)
			{
				//Creates SQL statements to be filled in later
				String sqlInsertCreds = "INSERT INTO USER_CREDENTIAL(ID, USERNAME, PASSWORD) VALUES(NULL, ?, ?)";
				String sqlInsertUser = "INSERT INTO USER_TABLE(ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER, USER_CREDENTIAL_ID) VALUES (NULL, ?, ?, ?, ?, ?)";
				
				try
				{
					//Inputs inforamtion into the database for both credentials and user information 
					int rows = jdbcTemplateObject.update(sqlInsertCreds, user.getCredentials().getUserName(), user.getCredentials().getPassword());
					
					String sqlQuery = "SELECT LAST_INSERT_ID() AS LAST_ID FROM USER_CREDENTIAL";
					
					SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery);
					srs.next();
					int userCredID = Integer.parseInt(srs.getString("LAST_ID"));
					
					rows += jdbcTemplateObject.update(sqlInsertUser, user.getFirstName(), user.getLastName(), user.getEmail(),user.getPhoneNumber(), userCredID);
					
					returnNum = rows;
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
					throw new DatabaseException(e);
				}
			}
			
			else
			{
				returnNum = -1;
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return returnNum;
	}

	/**
	 * @see UserAccessInterface
	 */
	public List<User> getAllUsers() 
	{
		//Creates a SQL statement to be filled in later
		String sql = "SELECT USERNAME, PASSWORD, USER_TABLE.ID, FIRST_NAME, LAST_NAME, EMAIL, PHONE_NUMBER FROM USER_CREDENTIAL INNER JOIN USER_TABLE ON USER_CREDENTIAL.ID = USER_TABLE.USER_CREDENTIAL_ID";
		
		//Creates an ArrayList of users that will be filled with all the users from the database
		List<User> userList = new ArrayList<User>();
		
		try
		{
			//Access the database and Queries for all users and is given a results set with information of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				userList.add(new User(srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), srs.getString("EMAIL"), srs.getString("PHONE_NUMBER"), srs.getString("USERNAME"), srs.getString("PASSWORD")));
			}
		}
		catch(Exception e)
		{
		 e.printStackTrace();
		}
		
		return userList;
	}
	
	/**
	 * @see UserAccessInterface
	 */
	public List<UserCredentials> getAllUserCredentials() 
	{
		//Creates SQL statements to be filled in later
		String sql = "SELECT * FROM USER_CREDENTIAL";
		List<UserCredentials> userList = new ArrayList<UserCredentials>();
		
		try
		{
			//Access the database and Queries for all users and is given a results set with the username and password of all users
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				userList.add(new UserCredentials(srs.getString("USERNAME"), srs.getString("PASSWORD")));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return userList;
	}

	/**
	 * @see UserAccessInterface
	 */
	public int editUser(User user, int userID) 
	{
		int returnNum = 0;
		
		//Creates SQL statements to be filled in later
		String sqlQuery = "SELECT USER_CREDENTIAL_ID FROM USER_TABLE WHERE USER_TABLE.ID=?";
		String sqlUpdateCred = "UPDATE USER_CREDENTIAL SET USERNAME=?, PASSWORD=? WHERE ID=?";
		String sqlUpdateUser = "Update USER_TABLE SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, PHONE_NUMBER=? WHERE ID=?";
		
		try
		{
			//Updated information for both the crednetial and user information
			int userCredNum = 0; 
			
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, userID);
			while(srs.next())
			{
				userCredNum = srs.getInt("USER_CREDENTIAL_ID");
			}
			
			int rowsChanged = jdbcTemplateObject.update(sqlUpdateCred, user.getCredentials().getUserName(), user.getCredentials().getPassword(), userCredNum);
			
			rowsChanged += jdbcTemplateObject.update(sqlUpdateUser, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber(), userID);
			
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
	 * @see UserAccessInterface
	 */
	public int deleteUser(int userID) 
	{
		int returnNum = 0;
		
		//Creates SQL statements to be filled in later
		String sqlQuery = "SELECT USER_CREDENTIAL_ID FROM USER_TABLE WHERE ID=?";
		String sqlUpdateCred = "DELETE FROM USER_CREDENTIAL WHERE ID=?";
		String sqlUpdateUser = "DELETE FROM USER_TABLE WHERE ID=?";
		
		try
		{
			//Sends and receives data from the database in order to delete all user information form the database
			int userCredNum = 0; 
			
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sqlQuery, userID);
			while(srs.next())
			{
				userCredNum = srs.getInt("USER_CREDENTIAL_ID");
			}
			
			int rowsChanged = jdbcTemplateObject.update(sqlUpdateUser, userID);
			
			rowsChanged += jdbcTemplateObject.update(sqlUpdateCred, userCredNum);
			
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
	 * @see UserAccessInterface
	 */
	public int getCurrentUserID(UserCredentials user) 
	{
		//Creates SQL statements to be filled in later
		String sql = "SELECT USER_TABLE.ID FROM USER_TABLE  INNER JOIN USER_CREDENTIAL ON USER_CREDENTIAL.USERNAME = ? AND USER_CREDENTIAL.ID = USER_TABLE.USER_CREDENTIAL_ID;";
		
		try
		{
			//Bases on the ID will return the the inforamtion of the user
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, user.getUserName());
			while(srs.next())
			{
				return srs.getInt("ID");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		
		return -1;
	}

	/**
	 * @see UserAccessInterface
	 */
	public User getUser(int userID) 
	{
		//Creates SQL statements to be filled in later
		String sql = "SELECT * FROM USER_CREDENTIAL INNER JOIN USER_TABLE ON USER_TABLE.ID = ? AND USER_CREDENTIAL.ID = USER_TABLE.USER_CREDENTIAL_ID;";
		
		try
		{
			//Gets the users inforamtion based on the users id
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, userID);
			while(srs.next())
			{
				return new User(srs.getString("FIRST_NAME"), srs.getString("LAST_NAME"), srs.getString("EMAIL"), srs.getString("PHONE_NUMBER"), srs.getString("USERNAME"), srs.getString("PASSWORD"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new DatabaseException(e);
		}
		return null;
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
