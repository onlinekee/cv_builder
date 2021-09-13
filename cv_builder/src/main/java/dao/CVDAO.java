package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.DBConnection;
import model.CV;
import model.Section;
import model.User;

public class CVDAO {
	private static final String INSERT_CV = "INSERT INTO cvs (user_id, cv_name) VALUES (?, ?)";
	private static final String UPDATE_CV = "UPDATE cvs SET first_name = ?, last_name = ?, phone = ?, address = ?, email = ?, website = ? WHERE cv_id = ?;";
	private static final String SELECT_CV_BY_ID = "SELECT *  from cvs WHERE cv_id = ?;";
	private static final String DELETE_CV = "DELETE from cvs WHERE cv_id = ?;";
	private static final String SELECT_CVS_BY_USER = "SELECT *  from cvs WHERE user_id = ?;";

	public int insertCV(CV cv) {
		int result = 0;

		String[] id = { "cv_id" };

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(INSERT_CV, id);

			statement.setInt(1, cv.getUserId());
			statement.setString(2, cv.getName());

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

	public boolean updateCV(CV cv) {
		boolean result = false;

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(UPDATE_CV);

			statement.setString(1, cv.getFirst_name());
			statement.setString(2, cv.getLast_name());
			statement.setString(3, cv.getPhone());
			statement.setString(4, cv.getAddress());
			statement.setString(5, cv.getEmail());
			statement.setString(6, cv.getWebsite());
			statement.setInt(7, cv.getId());

			result = statement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public CV getCVById(int id) {
		CV cv = null;
		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(SELECT_CV_BY_ID);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				int cvId = rs.getInt("cv_id");
				int userId = rs.getInt("user_id");
				String cvName = rs.getString("cv_name");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String email = rs.getString("email");
				String website = rs.getString("website");
				String dateCreated = rs.getString("date_created");

				cv = new CV(cvId, userId, cvName, firstName, lastName, phone, address, email, website, dateCreated);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cv;
	}

	public List<CV> getAllCVs(int userId) {
		List<CV> cvs = new ArrayList<>();
		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(SELECT_CVS_BY_USER);
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {

				int cv_id = rs.getInt("cv_id");
				int user_id = rs.getInt("user_id");
				String name = rs.getString("cv_name");
				String first_name = rs.getString("cv_name");
				String last_name = rs.getString("cv_name");
				String phone = rs.getString("cv_name");
				String address = rs.getString("cv_name");
				String email = rs.getString("cv_name");
				String website = rs.getString("cv_name");
				String date = rs.getString("date_created");

				cvs.add(new CV(cv_id, user_id, name, first_name, last_name, phone, address, email, website, date));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cvs;
	}
	
	public boolean deleteCV(int cv_id) {
		boolean result = false;

		try {
			Connection conn = DBConnection.getInstance();
			PreparedStatement statement = conn.prepareStatement(DELETE_CV);

			statement.setInt(1, cv_id);
			
			result = statement.executeUpdate() > 0;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

}
