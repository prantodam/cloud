package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.FileRequestModel;
import Util.DbUtil;

public class FileReqDao {
	
	private Connection connection;
	public FileReqDao() {
		connection = DbUtil.getConnection();
	}
	
	public int insertFileReq(FileRequestModel frm) throws SQLException
	{
		PreparedStatement preparedStatement = connection
				.prepareStatement("insert into filerequest (filename, secretekey)values(?, ?)");
		
		preparedStatement.setString(1, frm.getFilename());
		preparedStatement.setString(2, frm.getSceretekey());
		preparedStatement.executeUpdate();
		
		preparedStatement=null;
		 preparedStatement = connection
					.prepareStatement("select max(filerequestid) as id from filerequest");
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next() && rs!=null) {
				
				return rs.getInt("id");
				
			}
			
			else{
				return 0;
			}
	}

}
