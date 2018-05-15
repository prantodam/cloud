package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDao;
import Model.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
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
		
		User user = new User();
		
		String username = request.getParameter("un");
		String password = request.getParameter("pass");
		String typeofuserid = request.getParameter("typeofusers");
		String phone = request.getParameter("mob");
		String mailid = request.getParameter("eid");
		
		
		user.setUsername(username);
		user.setPassword(password);
		user.setTypeofusersid(Integer.parseInt(typeofuserid));
		user.setPhone(phone);
		user.setEmail(mailid);
		
		UserDao userdao = new UserDao();
		userdao.Register(user);
		
		/*HttpSession session = request.getSession(true);
		session.setAttribute("User", username);
		session.setAttribute("Email", mailid);*/
		

		PrintWriter out = response.getWriter();

		out.print(
				"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>TPA</title><script type='text/javascript'>"
						+ "function display(msg){alert(msg);}</script></head>"
						+ "<body><script type='text/javascript'>display('User Registered Successfully... ');window.location='http://localhost:8080/SecureDataStorage-V3/userlogin.jsp';</script></body></html>");

	}

}
