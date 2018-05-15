package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDao;
import DAO.Validate;
import Model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 PrintWriter out = response.getWriter();
		String username = request.getParameter("un");
		String password = request.getParameter("pass");
	
		
		UserDao dao = new UserDao();
		User typeofuser = dao.typeofUser(username, password);
		
		if(typeofuser.getTypeofuser()!= null)
		{
			HttpSession session = request.getSession(true);
			session.setAttribute("user", typeofuser);
			int typeofuserid = typeofuser.getTypeofusersid();
			
			if(typeofuserid == 1)
			{
				System.out.println("Data Owner");
				Validate vu = new Validate();
				
				User user = vu.checkUser(username, password);
				
				String dbUN = user.getUsername();
				String dbPSW = user.getPassword();
				
				
				if(username.equals(dbUN) && password.equals(dbPSW) )
				{
					
					 RequestDispatcher rs = request.getRequestDispatcher("senderwelcome.jsp");
			            rs.forward(request, response);
				}
				else
				{
					request.setAttribute("error", "error");
					 RequestDispatcher rs = request.getRequestDispatcher("userlogin.jsp");
			            rs.forward(request, response);
			       
				}
			}
			else if(typeofuserid == 2)
			{
				System.out.println("Data Consumer");
				Validate vu = new Validate();
				
				User user = vu.checkUser(username, password);
				
				String dbUN = user.getUsername();
				String dbPSW = user.getPassword();
		
				
				if(username.equals(dbUN) && password.equals(dbPSW) )
				{
					
					
					 RequestDispatcher rs = request.getRequestDispatcher("FileDetailsForReceiver");
			            rs.forward(request, response);
				}
				else
				{
					request.setAttribute("error", "error");
					 RequestDispatcher rs = request.getRequestDispatcher("userlogin.jsp");
			            rs.forward(request, response);
			       
			       
				}
			}
			
		}
		else
		{
			request.setAttribute("error", "error");
			 RequestDispatcher rs = request.getRequestDispatcher("userlogin.jsp");
	            rs.forward(request, response);
			
		}
		
	}

}
