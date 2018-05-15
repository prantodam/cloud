package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.FileReqDao;
import DAO.UserDao;
import Model.FileDetails;
import Model.FileRequestModel;
import Model.User;

/**
 * Servlet implementation class FileRequest
 */
@WebServlet("/FileRequest")
public class FileRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int fileId = Integer.parseInt(request.getParameter("fileid"));
		String filename = request.getParameter("filename");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String usrName = user.getUsername();
		System.out.println("consumer name:" + usrName);

		String usrEmail = user.getEmail();
		System.out.println("consumer email:" + usrEmail);
		
				
		// --------RSA KEY GENERATION ALGORITHM---------------
		KeyPairGenerator keyPairGenerator = null;
		try {
			keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.genKeyPair();

		byte[] secreteKey = keyPair.getPublic().getEncoded();
		
		String key = secreteKey.toString();

		System.out.println("key: " + secreteKey);
		// -------------------------------EOF RSA----------------------------
		
		//-----file request  value stored in db------
		
		FileRequestModel filereq = new FileRequestModel();
		
		filereq.setFilename(filename);
		filereq.setSceretekey(key);
		
		FileReqDao filereqdao = new FileReqDao();
		
		try {
			filereqdao.insertFileReq(filereq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//-------------
		

		// ------------------Email Setup--------------------------

		// SMTP server information
		String host = "smtp.gmail.com";
		String port = "587";
		String mailFrom = "raystechno1234@gmail.com";
		String pass = "rays@2017";

		// outgoing message information
		String mailTo = usrEmail;
		String subject = "Hello " + usrName;
		String message = "File Name:" + filename + " of  Secrete Key is:" + secreteKey;

		String resultMessage = "";

		try {
			EmailUtility.sendEmail(host, port, mailFrom, pass, mailTo, subject, message);
			resultMessage = "The e-mail was sent successfully";
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "There were an error: " + ex.getMessage();
		} finally {

			System.out.println("Secrete Key sent to your mail id..");
		}

		// -----------------eof email setup------------------------------

		PrintWriter out = response.getWriter();

		out.print(
				"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>TPA</title><script type='text/javascript'>"
						+ "function display(msg){alert(msg);}</script></head>"
						+ "<body><script type='text/javascript'>display('Done File Request..Pls Check the Your Mailid once and Use secrete key for download the file... ');window.location='http://localhost:8080/SecureDataStorage-V3/FileDetailsForReceiver';</script></body></html>");

	}

}
