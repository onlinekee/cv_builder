package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import dao.CVDAO;
import dao.SectionDAO;
import model.CV;
import model.Section;

public class SectionDAOTest {

	@Test
	public void testInsertSection() {
		SectionDAO sectionDAO = new SectionDAO();
		Section section = new Section(9,"Education", "[{\"type\":\"Paragraph\",\"subTitle\":\"Abcd\",\"paragraph\":\"Assss\"}]", 2);
		boolean result = sectionDAO.insertSection(section) > 0;
		
		assertTrue(result);
	}

	
	@Test
	public void testUpdatePosition() {
		SectionDAO sectionDAO = new SectionDAO();
		boolean result = sectionDAO.updatePosition(9,28,1,2);
		
		assertTrue(result);
	}

	@Test
	public void testGetSectionsByCV() {
		SectionDAO sectionDAO = new SectionDAO();
		List<Section> result = sectionDAO.getSectionsByCV(9);
		
		assertTrue(result.size() > 1);
	}

	
}
