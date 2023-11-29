package com.sapours.login.SendOTP.Pojo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class Registration {
 
	private int id;
	public Registration(String email, String password, String design) {
		super();
		this.email = email;
		this.password = password;
		this.design = design;
	}
	public Registration() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDesign() {
		return design;
	}
	public void setDesign(String design) {
		this.design = design;
	}
	private String email;
	private String password;
	private String design;
	
	
	
	
	private CommonsMultipartFile file;
	public CommonsMultipartFile getFile() {
		return file;
	}
	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}
	public Registration(CommonsMultipartFile file,int id) {
		super();
		this.file = file;
		this.id=id;
	}
	
	private byte[] b;
	public byte[] getB() {
		return b;
	}
	public void setB(byte[] b) {
		this.b = b;
	}
	
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Registration(int id, String email, byte[] b, String fileName) {
		super();
		this.id = id;
		this.email = email;
		this.b = b;
		this.fileName = fileName;
	}
	
	
	//--------------- shared file mail
	
	private String sharedEmail;
	public Registration(int id, String email, String sharedEmail) {
		super();
		this.id = id;
		this.email = email;
		this.sharedEmail = sharedEmail;
	}
	public String getSharedEmail() {
		return sharedEmail;
	}
	public void setSharedEmail(String sharedEmail) {
		this.sharedEmail = sharedEmail;
	}
	
	
	//------------------ Read and Write File
	
	
	private String readFile;
	
	private String writeFile;
	public String getReadFile() {
		return readFile;
	}
	public void setReadFile(String readFile) {
		this.readFile = readFile;
	}
	public String getWriteFile() {
		return writeFile;
	}
	public void setWriteFile(String writeFile) {
		this.writeFile = writeFile;
	}
	public Registration(int id, String email, String password, String design, CommonsMultipartFile file, byte[] b,
			String fileName, String sharedEmail, String readFile, String writeFile) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.design = design;
		this.file = file;
		this.b = b;
		this.fileName = fileName;
		this.sharedEmail = sharedEmail;
		this.readFile = readFile;
		this.writeFile = writeFile;
	}	
	
	//----------------- Share file with coowner
	
	private String shareFile;
	public String getShareFile() {
		return shareFile;
	}
	public void setShareFile(String shareFile) {
		this.shareFile = shareFile;
	}
	public Registration(int id, String email, String password, String design, CommonsMultipartFile file, byte[] b,
			String fileName, String sharedEmail, String readFile, String writeFile, String shareFile) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.design = design;
		this.file = file;
		this.b = b;
		this.fileName = fileName;
		this.sharedEmail = sharedEmail;
		this.readFile = readFile;
		this.writeFile = writeFile;
		this.shareFile = shareFile;
	}	
	
}
