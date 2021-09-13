package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.CV;
import model.Section;

public class SectionDAO {
	private static final String INSERT_SECTION = "INSERT INTO sections (cv_id, title, content, position) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_SECTION = "UPDATE sections SET title = ?, content = ? WHERE section_id = ?;";
	private static final String UPDATE_POSITION = "UPDATE sections SET position = ? WHERE section_id = ?;";
	private static final String DELETE_SECTION = "DELETE FROM sections WHERE section_id = ?;";
	private static final String SELECT_SECTIONS_BY_CV = "SELECT *  from sections WHERE cv_id = ? ORDER BY position ASC;";
	private static final String GET_SECTION_BY_POSITION = "SELECT *  from sections WHERE cv_id = ? AND position = ?;";

	public int insertSection(Section section) {
		int result = 0;

		String[] id = { "section_id" };

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(INSERT_SECTION, id);

			statement.setInt(1, section.getCv_id());
			statement.setString(2, section.getTitle());
			statement.setString(3, section.getContent());
			statement.setInt(4, section.getPosition());

			result = statement.executeUpdate();

			ResultSet rs = statement.getGeneratedKeys();

			if (result > 0) {
				if (rs.next()) {
					return rs.getInt(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updateSection(Section section) {
		boolean result = false;

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(UPDATE_SECTION);

			statement.setString(1, section.getTitle());
			statement.setString(2, section.getContent());
			statement.setInt(3, section.getSection_id());

			result = statement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public boolean updatePosition(int cv_id, int sec_id, int curPosition, int changePosition) {
		boolean result = false;

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement1 = conn.prepareStatement(UPDATE_POSITION);

			PreparedStatement statement2 = conn.prepareStatement(GET_SECTION_BY_POSITION);
			statement2.setInt(1, cv_id);
			statement2.setInt(2, changePosition);
			ResultSet rs = statement2.executeQuery();

			while (rs.next()) {
				int section_id = rs.getInt("section_id");
				int cvId = rs.getInt("cv_id");

				statement1.setInt(1, curPosition);
				statement1.setInt(2, section_id);
				result = statement1.executeUpdate() > 0;

				if (result) {
					statement1.setInt(1, changePosition);
					statement1.setInt(2, sec_id);
					result = statement1.executeUpdate() > 0;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public List<Section> getSectionsByCV(int id) {
		List<Section> sections = new ArrayList<>();
		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(SELECT_SECTIONS_BY_CV);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int sectionId = rs.getInt("section_id");
				int cvId = rs.getInt("cv_id");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int position = rs.getInt("position");

				sections.add(new Section(sectionId, cvId, title, content, position));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sections;
	}
	
	public boolean deleteSection(int sectionId) {
		boolean result = false;

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(DELETE_SECTION);

			statement.setInt(1, sectionId);
			
			result = statement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
}
