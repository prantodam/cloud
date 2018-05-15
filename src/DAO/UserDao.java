package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import Model.User;
import Util.DbUtil;



public class UserDao {
	
	private Connection connection;
	
	public UserDao() {
		connection = DbUtil.getConnection();
	}
	
	public void Register(User user)
	{
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into user( username, password, phone, email)values( ?, ?, ?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getPhone());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.executeUpdate();
			
			preparedStatement=null;
		 preparedStatement = connection
					.prepareStatement("select max(userid) as id from user");
			ResultSet rs = preparedStatement.executeQuery();
			int userid=0;
			if (rs.next() && rs!=null) {
				
				userid= rs.getInt("id");
				
			}
			
			preparedStatement=null;
			 preparedStatement = connection
						.prepareStatement("insert into userrolemapping(userid, typeofuserid)values( ?, ?)");

			    preparedStatement.setInt(1, userid);
				preparedStatement.setInt(2, user.getTypeofusersid());
			 preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		
		}
	}

	public User typeofUser(String username, String password) {
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
			prepareStatement("select a.userid,a.username,a.password,a.email,d.typeofuser,d.typeofuserid from user a join userrolemapping as r on a.userid=r.userid join typeofuser d on d.typeofuserid=r.typeofuserid where a.username=? and a.password=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();
			

			if (rs.next() && rs!=null) {
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTypeofuser(rs.getString("typeofuser"));
				user.setTypeofusersid(rs.getInt("typeofuserid"));
				user.setEmail(rs.getString("email"));
				
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return user;

	}

  
	public User getUserDetails(String username)
	{
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection.
			prepareStatement("select * from user where username = ?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			

			if (rs.next() && rs!=null) {
				user.setUserid(rs.getInt("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setTypeofuser(rs.getString("typeofuser"));
				user.setTypeofusersid(rs.getInt("typeofuserid"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));
			}
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}

		return user;
	}
	
	public List<User> getAllUser()
   {
	   List<User> usrs=new ArrayList<User>();
	  
	   try {
			
		   PreparedStatement preparedStatement = connection
					.prepareStatement("select * from user as b join userrolemapping c on c.userid = b.userid join typeofuser d on d.typeofuserid = c.typeofuserid");
		

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				 User usr = new User();
				usr.setUsername(rs.getString("username"));
				usr.setPassword(rs.getString("password"));
				usr.setTypeofuser(rs.getString("typeofuser"));
				usr.setEmail(rs.getString("email"));
				usr.setPhone(rs.getString("phone"));
				
				usrs.add(usr);
			
			}

		} catch (SQLException e) {
            e.printStackTrace();
		}
	   
	return usrs;
	   
   }
}
