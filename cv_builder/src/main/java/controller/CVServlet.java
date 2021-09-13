package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
import model.CV;
import model.Section;

/**
 * Servlet implementation class CVServlet
 */
@WebServlet("/cv_list/*")
public class CVServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CVDAO cvDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CVServlet() {
		this.cvDAO = new CVDAO();

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
		case "/cv_list":
			showCVList(request, response);
			break;
		case "/cv_list/get_all_cvs":
			try {
				getAllCVs(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case "/cv_list/delete_cv":
			try {
				deleteCV(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		default:
			showCVList(request, response);
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

	private void showCVList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("cvlist.jsp");
		dispatcher.forward(request, response);

	}

	private void getAllCVs(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();

		int user_id = (int) session.getAttribute("userId");

		PrintWriter out = response.getWriter();

		List<CV> cvs = cvDAO.getAllCVs(user_id);

		JSONObject obj = new JSONObject();

		if (cvs == null) {

			obj.put("error", true);
			obj.put("message", "There are no CVs");

			out.println(obj.toJSONString());
			out.flush();
		} else {
			JSONObject secObj = new JSONObject();

			JSONArray list = new JSONArray();

			for (CV cv : cvs) {
				JSONObject s = new JSONObject();
				s.put("cvId", cv.getId());
				s.put("userId", cv.getUserId());
				s.put("cvName", cv.getName());
				s.put("date", cv.getDate());

				list.add(s);
			}

			obj.put("error", false);
			obj.put("message", "CVs found");
			obj.put("data", list);
			out.println(obj.toJSONString());
			out.flush();

		}

	}
	
	private void deleteCV(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int cv_id = Integer.parseInt(request.getParameter("cv_id"));
		
		boolean result = cvDAO.deleteCV(cv_id);
		
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		if (result) {
			obj.put("error", false);
			obj.put("message", "CV deleted successfully!");

			out.println(obj.toJSONString());
			out.flush();
			
		} else {
			obj.put("error", true);
			obj.put("message", "Failed to delete CV!");

			out.println(obj.toJSONString());
			out.flush();
			
		}
	}

}
