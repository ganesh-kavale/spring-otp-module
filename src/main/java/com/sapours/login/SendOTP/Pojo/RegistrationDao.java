package com.sapours.login.SendOTP.Pojo;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class RegistrationDao {

	JdbcTemplate template;

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public int save(Registration registartion) {

		String sql = "insert into registration(Email,Password,Designation) values('" + registartion.getEmail() + "','"
				+ registartion.getPassword() + "','" + registartion.getDesign() + "')";

		return template.update(sql);

	}

	// --------------------------------------update---------------------

	public void updatePassword(Registration registration) {

		String sql = "update registration set Password='" + registration.getPassword() + "' where Email='"
				+ registration.getEmail() + "' ";

		template.update(sql);

		System.out.println(sql);
		System.out.println(registration.getPassword());
		System.out.println(registration.getEmail());

	}

	public List<Registration> getEmployees() {
		return template.query("select * from registration", new RowMapper<Registration>() {
			public Registration mapRow(ResultSet rs, int row) throws SQLException {
				Registration e = new Registration();
				e.setId(rs.getInt(1));
				e.setEmail(rs.getString(2));
				e.setPassword(rs.getString(3));
				e.setDesign(rs.getString(4));
				return e;
			}

		});
	}

	// ------------------------- FILE UPLOAD

	public int saveFile(byte[] fileSave, String email, String filename, String sharedEmail, String readFile,
			String writeFile) {

		String sql = "insert into filedatabase(Email,filename,file,sharedEmail,readFile,writeFile) values('" + email
				+ "','" + filename + "','" + fileSave + "','" + sharedEmail + "','" + readFile + "','" + writeFile
				+ "')";

		return template.update(sql);

	}

	// --------------------------------Display file Data

	public List<Registration> getFileData(String email) {
		return template.query("select * from filedatabase where Email='" + email + "'", new RowMapper<Registration>() {
			public Registration mapRow(ResultSet rs, int row) throws SQLException {
				Registration e = new Registration();

//				e.setB(rs.getBytes(1));				

				e.setId(rs.getInt(1));

				e.setEmail(rs.getString(2));

				e.setFileName(rs.getString(3));

				e.setB(rs.getBytes(4));
				
				e.setSharedEmail(rs.getString(5));

				e.setReadFile(rs.getString(6));

				e.setWriteFile(rs.getString(7));

				e.setShareFile(rs.getString(8));


				return e;
			}

		});
	}

	// -------------------- save file to computer

	public void saveFileLocalDatabase(byte[] fileData1) {
		// TODO Auto-generated method stub

		String sql = "insert into filelocaldatabase(file) values('" + fileData1 + "')";

		template.update(sql);

	}

	// ---------------------------------------- Shared File Given access

	public void sharedFile(Registration registration) {

//		String sql = "update filedatabase set sharedEmail='" + registration.getSharedEmail() + "' where Email='"
//				+ registration.getEmail() + "' && filename = '" + registration.getFileName() + "' ";
//
//		template.update(sql);

		String sql = "update filedatabase set sharedEmail='" + registration.getSharedEmail() + "',readFile = '"
				+ registration.getReadFile() + "', writeFile = '" + registration.getWriteFile() + "', shareFile = '" + registration.getShareFile() + "'   where Email='"
				+ registration.getEmail() + "' && filename = '" + registration.getFileName() + "'   ";

		template.update(sql);

		System.out.println(sql);
		System.out.println(registration.getSharedEmail());
		System.out.println(registration.getEmail());

	}

	// ---------------------------------------- Shared File Display (Sender) not using deprecated

	public List<Registration> getSharedFileData(String email, String design, String readFile, String writeFile) {

		return template.query(
				"select * from filedatabase where Email='" + email + "' && sharedEmail='" + design + "'  ",
				new RowMapper<Registration>() {
					public Registration mapRow(ResultSet rs, int row) throws SQLException {
						Registration e = new Registration();

						if (rs.getString(6).equals("true")) {
							e.setId(rs.getInt(1));

							e.setEmail(rs.getString(2));

							e.setFileName(rs.getString(3));

							e.setB(rs.getBytes(4));

							e.setSharedEmail(rs.getString(5));

							e.setFileName(rs.getString(3));
						} else if (rs.getString(6).equals("true")) {

							e.setId(rs.getInt(1));

							e.setEmail(rs.getString(2));

							e.setFileName(rs.getString(3));

							e.setB(rs.getBytes(4));

							e.setSharedEmail(rs.getString(5));

							e.setFileName(rs.getString(3));

						} else {

							e.setId(rs.getInt(1));

							e.setEmail("No Access");

							e.setFileName("No Access");

							e.setB(rs.getBytes(4));

							e.setSharedEmail(rs.getString(5));

							e.setFileName("No Access");

						}
						return e;

					}

				});

	}

	// ---------------------------------------- Shared File Display

	/*
	 * public List<Registration> getReadFileData(String email, String design,String
	 * readFile,String writeFile) {
	 * 
	 * return template.query("select * from filedatabase where Email='" + email +
	 * "' && sharedEmail='" + design + "' && readFile='" + "true" +
	 * "' && writeFile='" + "false" + "' ", new RowMapper<Registration>() { public
	 * Registration mapRow(ResultSet rs, int row) throws SQLException { Registration
	 * e = new Registration();
	 * 
	 * e.setId(rs.getInt(1));
	 * 
	 * e.setEmail(rs.getString(2));
	 * 
	 * e.setFileName(rs.getString(3));
	 * 
	 * e.setB(rs.getBytes(4));
	 * 
	 * e.setSharedEmail(rs.getString(5));
	 * 
	 * e.setFileName(rs.getString(3));
	 * 
	 * return e; }
	 * 
	 * });
	 * 
	 * }
	 */

	// ---------------------------------------- Remove Shared File Access

	public void removeSharedFile(Registration registration) {

		String sql = "update filedatabase set sharedEmail='" + " " + "',readFile = '" + registration.getReadFile()
				+ "', writeFile = '" + registration.getWriteFile() + "'   where Email='" + registration.getEmail()
				+ "' && filename = '" + registration.getFileName() + "'   ";

		template.update(sql);

		System.out.println(sql);
		System.out.println(registration.getSharedEmail());
		System.out.println(registration.getEmail());

	}

	// ---------------------------------------- Received File Display

	public List<Registration> getReceivedFileData(String email) {

		return template.query(
				"select * from filedatabase where sharedEmail='" + email + "'  ",
				new RowMapper<Registration>() {
					public Registration mapRow(ResultSet rs, int row) throws SQLException {
						Registration e = new Registration();

//						if (rs.getString(6).equals("true") ) {
//							e.setId(rs.getInt(1));
//
//							e.setEmail(rs.getString(2));
//
//							e.setFileName(rs.getString(3));
//
//							e.setB(rs.getBytes(4));
//
//							e.setSharedEmail(rs.getString(5));
//
//							e.setFileName(rs.getString(3));
//						
//
//						} else {
//
//							e.setId(rs.getInt(1));
//
//							e.setEmail("No Access");
//
//							e.setFileName("No Access");
//
//							e.setB(rs.getBytes(4));
//
//							e.setSharedEmail(rs.getString(5));
//
//							e.setFileName("No Access");
//
//						}
						
						e.setId(rs.getInt(1));

						e.setEmail(rs.getString(2));

						e.setFileName(rs.getString(3));

						e.setB(rs.getBytes(4));

						e.setSharedEmail(rs.getString(5));

						e.setFileName(rs.getString(6));
						
						e.setSharedEmail(rs.getString(7));

						e.setFileName(rs.getString(8));
						return e;
					}

				});

	}

	public String getText(String fileName) throws IOException {
		// TODO Auto-generated method stub

		String file = "C:\\Users\\Admin\\SapoursTechnologies_Test_New\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SendOTP\\WEB-INF\\files\\"
				+ fileName;

		Path path = Paths.get(file);

		String text = "";

		BufferedReader buff = Files.newBufferedReader(path);

		String curLine;

		while ((curLine = buff.readLine()) != null) {

			text = text + curLine + ('\n');

		}
		buff.close();

		return text;

	}

	public void setText(String fileName, String txt) throws IOException {
		// TODO Auto-generated method stub

		FileWriter fileWriter = new FileWriter(
				"C:\\Users\\Admin\\SapoursTechnologies_Test_New\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\SendOTP\\WEB-INF\\files\\"
						+ fileName);

		fileWriter.write(txt);

		fileWriter.close();

		System.out.println("Succeffully done!");

	}

	// -------------------------------- Access To Be Given To Read File
//not used yet
	public void readOnlyFile(Registration registration) {

		String sql = "update filedatabase set sharedEmail='" + registration.getSharedEmail() + "' where Email='"
				+ registration.getEmail() + "' && filename = '" + registration.getFileName() + "' && readFile = '"
				+ registration.getReadFile() + "' && writeFile = '" + registration.getWriteFile() + "'  ";

		template.update(sql);

		System.out.println(sql);
		System.out.println(registration.getSharedEmail());
		System.out.println(registration.getEmail());

	}

	public void co_OwnerAuthorityAccess(Registration registartion) {
		// TODO Auto-generated method stub

		String sql = "insert into filedatabase(Email,filename,sharedEmail,readFile,writeFile,shareFile) values('"
				+ registartion.getSharedEmail() + "','" + registartion.getFileName() + "','" + registartion.getEmail()
				+ "','" + registartion.getReadFile() + "','" + registartion.getWriteFile() + "','"
				+ registartion.getShareFile() + "')";

		template.update(sql);

	}

	public void co_OwnerAuthorityRemove(Registration registartion) {
		// TODO Auto-generated method stub

		String sql = "delete from filedatabase where Email='" + registartion.getSharedEmail() + "' && filename='"
				+ registartion.getFileName() + "'";

		template.update(sql);

	}

	public String deleteOwnerFile(Registration registartion) {
		// TODO Auto-generated method stub
		
		String sql = "delete from filedatabase where Email='" + registartion.getEmail() + "' && filename='"
				+ registartion.getFileName() + "'";

		template.update(sql);
		
		
		return "Deleted Successfully!";
	}

}
