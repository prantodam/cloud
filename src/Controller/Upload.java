package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import DAO.FileDao;
import Model.FileDetails;
import Model.User;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String UPLOAD_DIRECTORY = "UploadDocs";
	private static final String ENCRYPT_DIRECTORY = "EncryptDocs";
	
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Upload() {
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

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String usrName = user.getUsername();

		// constructs the directory path to store upload file
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		// constructs the directory path to store encrypt file
		String encryptPath = getServletContext().getRealPath("") + File.separator + ENCRYPT_DIRECTORY;
	
	
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		String upload_fileName = null;
		String extension = null;
		String filePath = null;
		String encfilepath = null;
		InputStream filecontent = null;

		// creates the directory if it does not exist
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {

			// parses the request's content to extract file data
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();
		
			// iterates over form's fields
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				filecontent = item.getInputStream();
				// processes only fields that are not form fields
				if (!item.isFormField()) {
					upload_fileName = new File(item.getName()).getName();
					System.out.println("uploaded file name:" + upload_fileName);
					extension = FilenameUtils.getExtension(upload_fileName);
					System.out.println("uploaded file :" + extension);
					filePath = uploadPath + File.separator + upload_fileName;
					String compressFilePath = uploadPath + File.separator + upload_fileName+".zip";
					System.out.println(filePath);
					System.out.println(compressFilePath);
					File storeFile = new File(filePath);
					item.write(storeFile);
					System.out.println("Original File Size: "+item.getSize());
					
					FileInputStream fis=new FileInputStream(filePath);
					 
			        //Assign compressed file:file2 to FileOutputStream
			        FileOutputStream fos=new FileOutputStream(compressFilePath);
			 
			        //Assign FileOutputStream to DeflaterOutputStream
			        DeflaterOutputStream dos=new DeflaterOutputStream(fos);
			 
			        //read data from FileInputStream and write it into DeflaterOutputStream
			        int data;
			        while ((data=fis.read())!=-1)
			        {
			            dos.write(data);
			        }
			        
			 
			        //close the file
			        fis.close();
			        dos.close();
			        
			        File storeCompressFile = new File(compressFilePath);
			        System.out.println("compress File Size: "+storeCompressFile.length()  );
			        
					//------------------AES algorithm IMplementation---
					encfilepath = encryptPath+ File.separator +upload_fileName+ ".aes";
					String key = "Mary has one cat";
			        File encryptedFile = new File(encfilepath);
					
			        try {
			            CryptoUtils.encrypt(key, storeFile, encryptedFile);
			           
			        } catch (CryptoException ex) {
			            System.out.println(ex.getMessage());
			            ex.printStackTrace();
			        }
			       //------------------------------------eof AES --------------------
			        
			        
			        FileDetails ff = new FileDetails();
			        String encryptedformate = upload_fileName+ ".aes";
			        int userid = user.getUserid();
					int typeofuserid = user.getTypeofusersid();
			
					ff.setFilecontent(filecontent);
					ff.setEncryptedformate(encryptedformate);
					ff.setFilename(upload_fileName);
					ff.setUploadedby(userid);
					ff.setTypeofuserid(typeofuserid);

					DateFormat df = new SimpleDateFormat("dd/MM/yy");
					Date dateobj = new Date();
					Calendar calobj = Calendar.getInstance();
					String currentDate = df.format(calobj.getTime());
					System.out.println(currentDate);
					ff.setUploadeddate(currentDate);

					FileDao fd = new FileDao();
					int fileid = fd.insertFiles(ff);
					PrintWriter out = response.getWriter();
					String outputContent1 = "File Uploaded Successfully..";
					String outputContent2 = "Original File Size: "+item.getSize();
					String outputContent3 = "compress File Size: "+storeCompressFile.length();
					out.print(
							"<!DOCTYPE html><html><head><meta charset='ISO-8859-1'><title>Insert title here</title><script type='text/javascript'>"
									+ "function display(msg1, msg2, msg3){alert(msg1+'\\n'+msg2+'\\n'+msg3);}</script></head>"
									+ "<body><script type='text/javascript'>"
									+"display(\""+outputContent1+"\",\"" +outputContent2+"\",\""+outputContent3+ "\");"
									+"window.location='http://localhost:8080/SecureDataStorage-V3/senderwelcome.jsp';</script></body></html>");
		
				} else {
					System.out.println("it is not a form filed");

				}

			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		

	}

}
