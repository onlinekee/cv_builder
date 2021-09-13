package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import dao.CVDAO;
import dao.SectionDAO;
import model.CV;
import model.Section;
import model.User;

/**
 * Servlet implementation class CreateCV
 */
@WebServlet("/create_cv/*")
public class CreateCVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CVDAO cvDAO;
	private SectionDAO sectionDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateCVServlet() {
		this.cvDAO = new CVDAO();
		this.sectionDAO = new SectionDAO();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length(),
				request.getRequestURI().length());

		switch (action) {
		case "/create_cv":
			if (request.getSession().getAttribute("username") == null) {
				response.sendRedirect("login");
			} else {
				showCreateCV(request, response);
			}

			break;
		case "/create_cv/start_cv":
			try {
				insertCV(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/create_cv/get_cv":
			try {
				getCVById(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/create_cv/add_section":
			try {
				insertSection(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/create_cv/get_sections":
			try {
				getSectionsByCV(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/create_cv/add_basic_info":
			try {
				insertBasicInfo(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/create_cv/change_position":
			try {
				updatePosition(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/create_cv/delete_section":
			try {
				deleteSection(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			try {
				if (request.getSession().getAttribute("username") == null) {
					response.sendRedirect("login");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	private void showCreateCV(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("create_cv.jsp");
		dispatcher.forward(request, response);
	}

	private void insertCV(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {

		HttpSession session = request.getSession();

		String name = request.getParameter("cv_name");
		int userId = (int) session.getAttribute("userId");

		CV cv = new CV(userId, name);

		int result = cvDAO.insertCV(cv);

		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		JSONObject cvObj = new JSONObject();

		if (result > 0) {
			CV newCv = cvDAO.getCVById(result);
			cvObj.put("cvId", newCv.getId());
			cvObj.put("userId", newCv.getUserId());
			cvObj.put("cvName", newCv.getName());
			cvObj.put("dateCreated", newCv.getDate());

			obj.put("error", false);
			obj.put("message", "CV creation started!");
			obj.put("data", cvObj);

			out.println(obj.toJSONString());
			out.flush();
		} else {
			obj.put("error", true);
			obj.put("message", "Failed to start cv creation! Try again.");
			out.println(obj.toJSONString());
			out.flush();
		}
	}

	private void insertBasicInfo(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		HttpSession session = request.getSession();

		int cv_id = Integer.parseInt(request.getParameter("cv_id"));
		int user_id = (int) session.getAttribute("userId");
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String email = request.getParameter("email");
		String website = request.getParameter("website");

		CV cv = new CV(cv_id, user_id, first_name, last_name, phone, address, email, website);

		boolean result = cvDAO.updateCV(cv);

		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		JSONObject cvObj = new JSONObject();

		if (result) {

			obj.put("error", false);
			obj.put("message", "CV Updated Successfully!");
			out.println(obj.toJSONString());
			out.flush();
		} else {
			obj.put("error", true);
			obj.put("message", "Failed to update CV! Try again.");
			out.println(obj.toJSONString());
			out.flush();
		}
	}

	private void insertSection(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		HttpSession session = request.getSession();

		int cv_id = Integer.parseInt(request.getParameter("cv_id"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		int position = Integer.parseInt(request.getParameter("position"));
		int userId = (int) session.getAttribute("userId");

		Section section = new Section(cv_id, title, content, position);

		int result = sectionDAO.insertSection(section);

		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();

		if (result > 0) {

			obj.put("error", false);
			obj.put("message", "Section created successfully!");

			out.println(obj.toJSONString());
			out.flush();
		} else {
			obj.put("error", true);
			obj.put("message", "Failed create section! Try again.");
			out.println(obj.toJSONString());
			out.flush();
		}
	}

	private void updatePosition(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int section_id = Integer.parseInt(request.getParameter("section_id"));
		int cv_id = Integer.parseInt(request.getParameter("cv_id"));
		int cur_pos = Integer.parseInt(request.getParameter("cur_pos"));
		int change_pos = Integer.parseInt(request.getParameter("change_pos"));
	
		boolean result = sectionDAO.updatePosition(cv_id, section_id, cur_pos, change_pos);
		
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		if (result) {
			obj.put("error", false);
			obj.put("message", "Position Changed");

			out.println(obj.toJSONString());
			out.flush();
			
		} else {
			obj.put("error", true);
			obj.put("message", "Could not change position");

			out.println(obj.toJSONString());
			out.flush();
			
		}
	}
	
	private void deleteSection(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int section_id = Integer.parseInt(request.getParameter("section_id"));
		
	
		boolean result = sectionDAO.deleteSection(section_id);
		
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		if (result) {
			obj.put("error", false);
			obj.put("message", "Section deleted successfully!");

			out.println(obj.toJSONString());
			out.flush();
			
		} else {
			obj.put("error", true);
			obj.put("message", "Failed to delete section!");

			out.println(obj.toJSONString());
			out.flush();
			
		}
	}
	
	private void getSectionsByCV(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		PrintWriter out = response.getWriter();

		CV cv = cvDAO.getCVById(id);

		HttpSession session = request.getSession();

		JSONObject obj = new JSONObject();

		if (cv == null) {

			obj.put("error", true);
			obj.put("message", "CV not found");

			out.println(obj.toJSONString());
			out.flush();
		} else {
			JSONObject secObj = new JSONObject();
			List<Section> sections = sectionDAO.getSectionsByCV(id);
			JSONArray list = new JSONArray();

			for (Section sec : sections) {
				JSONObject s = new JSONObject();
				s.put("cvId", sec.getCv_id());
				s.put("sectionId", sec.getSection_id());
				s.put("title", sec.getTitle());
				s.put("content", sec.getContent());
				s.put("position", sec.getPosition());
				list.add(s);
			}

			obj.put("error", false);
			obj.put("message", "sections found");
			obj.put("data", list);
			out.println(obj.toJSONString());
			out.flush();

		}

	}

	private void getCVById(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		PrintWriter out = response.getWriter();

		CV cv = cvDAO.getCVById(id);

		HttpSession session = request.getSession();

		JSONObject obj = new JSONObject();

		if (cv == null) {

			obj.put("error", true);
			obj.put("message", "CV not found");

			out.println(obj.toJSONString());
			out.flush();
		} else {

			if (cv.getUserId() != (int) session.getAttribute("userId")) {
				obj.put("error", true);
				obj.put("message", "You do not have access to this cv");

				out.println(obj.toJSONString());
				out.flush();
			} else {
				JSONObject cvObj = new JSONObject();

				cvObj.put("cvId", cv.getId());
				cvObj.put("userId", cv.getUserId());
				cvObj.put("cvName", cv.getName());
				cvObj.put("firstName", cv.getFirst_name());
				cvObj.put("lastName", cv.getLast_name());
				cvObj.put("phone", cv.getPhone());
				cvObj.put("address", cv.getAddress());
				cvObj.put("email", cv.getEmail());
				cvObj.put("website", cv.getWebsite());
				cvObj.put("dateCreated", cv.getDate());

				obj.put("error", false);
				obj.put("message", "CV Creation Started Successfully!");
				obj.put("data", cvObj);

				out.println(obj.toJSONString());
				out.flush();
			}

		}

	}



}
