package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.UserDAO;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		this.userDAO = new UserDAO();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		switch (action) {
		case "/register":
			showRegisterView(request, response);
			break;
		case "/insert":
			try {
				registerUser(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		case "/check_login":
			try {
				loginUser(request, response);
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		case "/login":
			showLoginView(request, response);
			break;
		case "/logout":
			showLoginView(request, response);
			break;
		default:
			try {
				if (request.getSession().getAttribute("username") == null) {
					System.out.println("null point");
					response.sendRedirect("login");
				} else {
					response.sendRedirect("your_cvs");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
			break;
		}
	}

	private void showLoginView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);

	}

	private void showRegisterView(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = new User(username, password);
		userDAO.insertUser(user);
		response.sendRedirect("login");
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);

		PrintWriter out = response.getWriter();

		User user = userDAO.loginUser(username, password);

		String message;

		HttpSession session = request.getSession();

		if (user == null) {
			System.out.println("Invalid Credintials");

			message = "error";

			JSONObject obj = new JSONObject();

			obj.put("message", message);
			obj.put("data", user);

			out.println(obj.toJSONString());
			out.flush();
		} else {
			System.out.println("Login Success");

			session.setAttribute("username", username);
			session.setAttribute("password", password);

			message = "success";

			JSONObject obj = new JSONObject();
			JSONObject userObj = new JSONObject();

			userObj.put("user_id", user.getId());
			userObj.put("username", user.getUsername());

			obj.put("message", message);
			obj.put("data", userObj);

			out.println(obj.toJSONString());

			System.out.println(obj.toJSONString());

			out.flush();
		}

	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		HttpSession session = request.getSession();

		session.removeAttribute("username");
		session.invalidate();
		response.sendRedirect("login");
	}

}
