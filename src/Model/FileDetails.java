package Model;

import java.io.InputStream;

public class FileDetails {
	
	private int fileid;
	private int uploadedby;
	private int typeofuserid;
	private String filename;
	private InputStream filecontent;
	private String uploadeddate;
	private String encryptedformate;
	private String ownerName;
	private String typeofuser;
	
	
	public int getFileid() {
		return fileid;
	}
	public void setFileid(int fileid) {
		this.fileid = fileid;
	}
	public int getUploadedby() {
		return uploadedby;
	}
	public void setUploadedby(int uploadedby) {
		this.uploadedby = uploadedby;
	}
	public int getTypeofuserid() {
		return typeofuserid;
	}
	public void setTypeofuserid(int typeofuserid) {
		this.typeofuserid = typeofuserid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public InputStream getFilecontent() {
		return filecontent;
	}
	public void setFilecontent(InputStream filecontent) {
		this.filecontent = filecontent;
	}
	public String getUploadeddate() {
		return uploadeddate;
	}
	public void setUploadeddate(String uploadeddate) {
		this.uploadeddate = uploadeddate;
	}
	public String getEncryptedformate() {
		return encryptedformate;
	}
	public void setEncryptedformate(String encryptedformate) {
		this.encryptedformate = encryptedformate;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getTypeofuser() {
		return typeofuser;
	}
	public void setTypeofuser(String typeofuser) {
		this.typeofuser = typeofuser;
	}
	
}
