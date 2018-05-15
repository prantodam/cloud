package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.FileDetails;

import Util.DbUtil;

public class FileDao {
	
private Connection connection;
	public FileDao() {
		connection = DbUtil.getConnection();
	}
	public int insertFiles(FileDetails file) throws SQLException
	{
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into file ( uploadedby, filename, filecontent, uploadeddate, encryptedformate,typeofuserid)values( ?, ?, ?, ?, ?,?)");
		
		preparedStatement.setInt(1,file.getUploadedby() );
		preparedStatement.setString(2, file.getFilename());
		preparedStatement.setBlob(3, file.getFilecontent());
		preparedStatement.setString(4, file.getUploadeddate());
		preparedStatement.setString(5, file.getEncryptedformate());
		preparedStatement.setInt(6, file.getTypeofuserid());
		preparedStatement.executeUpdate();
		
		preparedStatement=null;
		 preparedStatement = connection
					.prepareStatement("select max(fileid) as id from file");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() && rs!=null) {
				
				return rs.getInt("id");
				
			}
			
			else{
				return 0;
			}
		
	}

	public List <FileDetails>	getAllfiles(){
		List<FileDetails> files=new ArrayList<FileDetails>();
		
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from file as a join user as b on b.userid=a.uploadedby join userrolemapping c on c.userid=b.userid join typeofuser d on d.typeofuserid=c.typeofuserid");
		
			ResultSet rs = preparedStatement.executeQuery();
		
			while (rs.next() && rs!=null) {
				FileDetails f=new FileDetails();
				f.setEncryptedformate(rs.getString("encryptedformate"));
				f.setFileid(rs.getInt("fileid"));
				f.setFilename(rs.getString("filename"));
				f.setOwnerName(rs.getString("username"));
				f.setUploadeddate(rs.getString("uploadeddate"));
				
				
				files.add(f);
				
			}
			
			return files;
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return files;
	}

	public FileDetails getFileContent(int fileId)
	{
		
		FileDetails f=new FileDetails();
		//select * from file where fileid=?
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * from file where fileid=?");
			preparedStatement.setInt(1,fileId);
			
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() && rs!=null) {
				
				f.setFilename(rs.getString("filename"));
				f.setEncryptedformate(rs.getString("encryptedformate"));
			}
			
			return f;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
			
		}
		
	}
	


}


