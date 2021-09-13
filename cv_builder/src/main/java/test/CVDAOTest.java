package test;

import static org.junit.Assert.*;

import org.junit.Test;

import dao.CVDAO;
import dao.UserDAO;
import model.CV;
import model.User;


public class CVDAOTest {

	@Test
	public void testInsertCV() {
		CVDAO cvDAO = new CVDAO();
		CV cv = new CV( 1, "keecv");
		boolean result = cvDAO.insertCV(cv) > 0;
		
		assertTrue(result);
	}

	@Test
	public void testUpdateCV() {
		CVDAO cvDAO = new CVDAO();
		CV cv = new CV( 3, 20, "keerthana","Albert", "0741408163","Batticaloa,Sri Lanka", "onlinekee@gmail.com","www.kee.com");
		boolean result = cvDAO.updateCV(cv);
		
		assertTrue(result);
	}

	@Test
	public void testGetCVById() {
		CVDAO cvDAO = new CVDAO();
		CV cv = cvDAO.getCVById(3);

		String expected = "Keerthana";
		assertEquals(expected, cv.getName());
	}

	

}
