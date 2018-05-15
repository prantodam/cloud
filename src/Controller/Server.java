package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FileDao;
import Model.FileDetails;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Server extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Server() {
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
		
		String username = request.getParameter("un");
		String password = request.getParameter("pass");
		RequestDispatcher rd = null;
		
		
		if(username.equals("server") && password.equals("server"))
		{
			
			FileDao fd=new FileDao();
			
			ArrayList<FileDetails> files=new ArrayList<FileDetails>();
		
			
			
			files=(ArrayList<FileDetails>) fd.getAllfiles();
			
			request.setAttribute("files", files);
			rd = request.getRequestDispatcher("FileDetailsForServer");
			rd.forward(request, response);
		}
		else
		{
			request.setAttribute("error", "error");
			 RequestDispatcher rs = request.getRequestDispatcher("serverlogin.jsp");
	            rs.forward(request, response);
	       
		}
	}

}
