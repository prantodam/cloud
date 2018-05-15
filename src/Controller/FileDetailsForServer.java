package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FileDao;
import Model.FileDetails;
import Model.User;

/**
 * Servlet implementation class FileDetailsForServer
 */
@WebServlet("/FileDetailsForServer")
public class FileDetailsForServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDetailsForServer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		FileDao fd=new FileDao();
		List<FileDetails> files=new ArrayList<FileDetails>();
	
		HttpSession session = request.getSession(true);
		User u = (User) session.getAttribute("user");
		
		files=(ArrayList<FileDetails>) fd.getAllfiles();
		
		 request.setAttribute("files", files);
		 
		 RequestDispatcher view = request.getRequestDispatcher("serverwelcome.jsp");
	        view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
