package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.UserDAO;
import model.User;

public class UserDAOTest {

	@Test
	public void testInsertUser() {
		UserDAO userDAO = new UserDAO();
		User user = new User("keee", "123");
		boolean result = userDAO.insertUser(user);

		boolean expected = true;
		
		assertTrue(result);
	
	}

	@Test
	public void testLoginUser() {
		UserDAO userDAO = new UserDAO();

		User user = userDAO.loginUser("keee", "123");

		String expected = "keee";
		assertEquals(expected, user.getUsername());

	}

	@Test
	public void testGetUserByUsername() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserByUsername("keee");

		String expected = "keee";
		assertEquals(expected, user.getUsername());
	}

	@Test
	public void testCheckUsername() {
		UserDAO userDAO = new UserDAO();
		boolean result = userDAO.checkUsername("keee");

		boolean expected = true;
		assertEquals(expected, result);
	}

	@Test
	public void testGetHashedPassword() {
		UserDAO userDAO = new UserDAO();
		String result = userDAO.getHashedPassword("1234");

		String expected = "81dc9bdb52d04dc20036dbd8313ed055";
		assertEquals(expected, result);

	}

}
