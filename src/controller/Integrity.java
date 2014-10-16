package controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

/**
 * Servlet implementation class Integrity
 */
@WebServlet("/Integrity")
public class Integrity extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private HashMap<String, User> users = new HashMap<String, User>();
	
	User currentUser;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Integrity() {
        super();
        // TODO Auto-generated constructor stub
        
        User adam = new User();
        
        adam.setUsername("AdamPodraza");
        adam.setPassword("Sue22sue");
        adam.setEmail("apodra86@gmail.com");
        users.put(adam.getUsername(), adam);
        
        User steve = new User();
        
        steve.setUsername("SteveLunsford");
        steve.setPassword("coolinchicago");
        steve.setEmail("coolinchicago2003@yahoo.com");
        
        users.put(steve.getUsername(), steve);
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
		if (action == null) {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		if (action.equals("login")) {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			
			if(users.containsKey(username) && users.get(username).getPassword().equals(password)) {
				
				currentUser = users.get(username);
				
				HttpSession session = request.getSession();
				
				session.setAttribute("user", currentUser);
				
				//request.setAttribute("user", currentUser);
			
				request.getRequestDispatcher("/main.jsp").forward(request, response);
			}
			else
				
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
		if (action.equals("add")) {
			String item = request.getParameter("item");
			
			HttpSession session = request.getSession();
			
			currentUser = (User) session.getAttribute("user");
			
			currentUser.addItem(item);
			currentUser.sendEmail();
			
			session.setAttribute("user", currentUser);
			
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
		if (action.equals("remove")) {
			String item = request.getParameter("item");
			
			HttpSession session = request.getSession();
			
			currentUser = (User) session.getAttribute("user");
			
			currentUser.removeItem(item);
			
			session.setAttribute("user", currentUser);
			request.getRequestDispatcher("/main.jsp").forward(request, response);
		}
	}

}
