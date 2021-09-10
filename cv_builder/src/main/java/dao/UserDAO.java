package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.DBConnection;
import model.User;

/** This class provides database CRUD operations for User table **/
public class UserDAO {
	private static final String INSERT_USER = "INSERT INTO Users (username, password) VALUES (?, ?, ?)";
	private static final String SELECT_USER_BY_USERNAME = "SELECT *  from Users WHERE username = ?;";

	public boolean insertUser(User user) {
		boolean result = false;
		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(INSERT_USER);

			statement.setString(1, user.getUsername());
			statement.setString(2, getHashedPassword(user.getPassword()));
			
			result = statement.executeUpdate() > 0;
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public User loginUser(String username, String password) {
		User user = null;
		System.out.println(getHashedPassword(password));
		try {
			user = getUserByUsername(username);
			
			
			if(user == null) {
				return null;
			}
			
			if(getHashedPassword(password) == user.getPassword()) {
				System.out.println("login user: "+user.getUsername());
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
		
	
	}
	
	public User getUserByUsername(String username) {
		User user = null;
		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(SELECT_USER_BY_USERNAME);

			statement.setString(1, username);
			ResultSet rs = statement.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("user_id");
				String uName = rs.getString("username");
				String pWord = rs.getString("password");
				user = new User(id, uName, pWord);
				
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public String getHashedPassword(String password) {
		String hashedPassword = null;
		try {

			MessageDigest md = MessageDigest.getInstance("MD5");

			md.update(password.getBytes());

			byte[] bytes = md.digest();

			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}

			hashedPassword = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hashedPassword;
	}
}
