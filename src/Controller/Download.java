package Controller;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FileDao;
import DAO.Validate;
import Model.FileDetails;
import Model.FileRequestModel;

/**
 * Servlet implementation class Download
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ENCRYPT_DIRECTORY = "EncryptDocs";
	private static final String DECRYPT_DIRECTORY = "Decrypted";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Download() {
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
		String encryptPath = getServletContext().getRealPath("") + File.separator + ENCRYPT_DIRECTORY;
		String decryPath = getServletContext().getRealPath("") + File.separator + DECRYPT_DIRECTORY;

		String secKey = request.getParameter("key");
		int fileId = Integer.parseInt(request.getParameter("fileid"));

		Validate vu = new Validate();

		FileRequestModel frmodel = vu.checkKey(secKey);

		String dbkey = frmodel.getSceretekey();

		if (secKey.equals(dbkey)) {

			FileDao fn = new FileDao();

			FileDetails f1 = fn.getFileContent(fileId);
			String downloadFilename = f1.getFilename();
			String encFile = f1.getEncryptedformate();

			System.out.println("download file name:" + encFile);

			String encfilePath = encryptPath + File.separator + encFile;
			String decfilepath = decryPath + File.separator + downloadFilename + ".dec";
			File encInFile = new File(encfilePath);
			String key = "Mary has one cat";
			File decOutFile = new File(decfilepath);

			try {

				CryptoUtils.decrypt(key, encInFile, decOutFile);
			} catch (CryptoException ex) {
				System.out.println(ex.getMessage());
				ex.printStackTrace();
			}

			if (!(decOutFile.exists() && decOutFile.isFile())) {
				// throw HTTP ERROR 404
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "File to download not found.");
				return;
			} else {
				int length = (int) decOutFile.length();
				ServletOutputStream sostream = response.getOutputStream();

				// Set the response headers

				// String mimetype =
				// request.getSession().getServletContext().getMimeType(
				// filename );
				response.setContentType("application/x-download");
				response.setHeader("Content-Disposition", "attachment; filename=" + decOutFile.getName());
				response.setContentLength(length);
				byte[] bbuf = new byte[1024];
				DataInputStream in = null;

				// Stream the file to the requester.

				try {
					in = new DataInputStream(new FileInputStream(decOutFile));

					while ((in != null) && ((length = in.read(bbuf)) != -1)) {
						sostream.write(bbuf, 0, length);
					}
					sostream.flush();
				} catch (FileNotFoundException e) {
					throw e;
				} catch (IOException e) {
					throw e;
				}

				finally {
					if (in != null) {
						in.close();
					}
					;
					sostream.close();
				}
			}
		}//if key 
		else
		{
			request.setAttribute("error", "error");
			 RequestDispatcher rs = request.getRequestDispatcher("download.jsp");
	            rs.forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		doGet(request, response);

	}

}
