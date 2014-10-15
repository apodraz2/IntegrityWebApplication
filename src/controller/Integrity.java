package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

/**
 * Servlet implementation class Integrity
 */
@WebServlet("/Integrity")
public class Integrity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, User> users = new HashMap<String, User>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Integrity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		User currentUser = new User();
		if (action == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		if (action.equals("login")) {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			
			currentUser.setUsername(username);
			currentUser.setPassword(password);
			
			users.put(username, currentUser);
			
			request.setAttribute("user", currentUser);
			
			request.getRequestDispatcher("/main.jsp").forward(request, response);
			
		}
		if (action.equals("add")) {
			String item = request.getParameter("item");
			
			currentUser.addItem(item);
			currentUser.sendEmail();
			
			request.setAttribute("user", currentUser);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		if (action.equals("remove")) {
			String item = request.getParameter("item");
			
			currentUser.removeItem(item);
			
			request.setAttribute("user", currentUser);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}

}
