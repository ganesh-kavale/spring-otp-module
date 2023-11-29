package com.sapours.login.SendOTP.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sapours.login.SendOTP.Pojo.Email;
import com.sapours.login.SendOTP.Pojo.Logic;
import com.sapours.login.SendOTP.Pojo.Registration;
import com.sapours.login.SendOTP.Pojo.RegistrationDao;

@RestController
public class HomeController {
//	Registration object when we use requestparam
	@Autowired
	Registration r;
	@Autowired
	RegistrationDao registrationDao;
	@Autowired
	Email email;
	@Autowired
	Logic logic;

	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		return new ModelAndView("home");
	}

//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	@ResponseBody
//	public String saveFileDatabase(@RequestBody CommonsMultipartFile file1, HttpSession session) {
//
//		System.out.println("File name : " + file1.getName());
//
//		System.out.println("Original File name : " + file1.getOriginalFilename());
//
//		System.out.println("File size : " + file1.getSize());
//
//		System.out.println("File storage description : " + file1.getStorageDescription());
//
//		System.out.println("File content type: " + file1.getContentType());
//
//		byte[] fileData = file1.getBytes();
//
//		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "files"
//				+ File.separator + file1.getOriginalFilename();
//
//		System.out.println(path);
//
//		try {
//			FileOutputStream fos = new FileOutputStream(path);
//
//			fos.write(fileData);
//			
//			
//			registrationDao.saveFile(fileData);
//			
//			
//			fos.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("File is not uploaded");
//		}
//
//		return "Uploadedsuccessfully!";
//	}

//	//=================================  RequestParam for file upload from postman
//
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
//	@ResponseBody
//	public String saveFileDatabase(@RequestParam("file") CommonsMultipartFile file1, HttpSession session) {
//
//		System.out.println("File name : " + file1.getName());
//
//		System.out.println("Original File name : " + file1.getOriginalFilename());
//
//		System.out.println("File size : " + file1.getSize());
//
//		System.out.println("File storage description : " + file1.getStorageDescription());
//
//		System.out.println("File content type: " + file1.getContentType());
//
//		byte[] fileData = file1.getBytes();
//
//		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "files"
//				+ File.separator + file1.getOriginalFilename();
//
//		System.out.println(path);
//
//		try {
//			FileOutputStream fos = new FileOutputStream(path);
//
//			fos.write(fileData);
//			
//			
//			registrationDao.saveFile(fileData);
//			
//			
//			fos.close();
//
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("File is not uploaded");
//		}
//
//		return "Uploadedsuccessfully!";
//	}

	// ================================= RequestParam for file upload from postman
	/*
	 * @CrossOrigin(origins = "http://localhost:4200")
	 * 
	 * @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public String saveFileDatabase(@RequestParam("file1")
	 * CommonsMultipartFile file1, @RequestParam String email, HttpSession session)
	 * { System.out.println(email); System.out.println("File name : " +
	 * file1.getName());
	 * 
	 * System.out.println("Original File name : " + file1.getOriginalFilename());
	 * 
	 * System.out.println("File size : " + file1.getSize());
	 * 
	 * System.out.println("File storage description : " +
	 * file1.getStorageDescription());
	 * 
	 * System.out.println("File content type: " + file1.getContentType());
	 * 
	 * String filename = file1.getOriginalFilename();
	 * 
	 * byte[] fileData = file1.getBytes();
	 * 
	 * registrationDao.saveFile(fileData, email, filename);
	 * 
	 * byte[] fileData1 = file1.getBytes();
	 * 
	 * 
	 * 
	 * 
	 * String path = session.getServletContext().getRealPath("/") + "WEB-INF" +
	 * File.separator + "files" + File.separator + file1.getOriginalFilename();
	 * 
	 * System.out.println(path);
	 * 
	 * try { FileOutputStream fos = new FileOutputStream(path);
	 * 
	 * fos.write(fileData);
	 * 
	 * 
	 * registrationDao.saveFileLocalDatabase(fileData1);
	 * 
	 * 
	 * fos.close();
	 * 
	 * } catch (IOException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); System.out.println("File is not uploaded"); }
	 * 
	 * 
	 * return "Uploaded successfully!"; }
	 */

	// -----------------------------------------------------------Shared File

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String saveFileDatabase(@RequestParam("file1") CommonsMultipartFile file1, @RequestParam String email,
			@RequestParam String sharedEmail, @RequestParam String readFile, @RequestParam String writeFile, HttpSession session) {
		System.out.println(email);

		// System.out.println("email");

		System.out.println(sharedEmail);

		System.out.println("File name : " + file1.getName());

		System.out.println("Original File name : " + file1.getOriginalFilename());

		System.out.println("File size : " + file1.getSize());

		System.out.println("File storage description : " + file1.getStorageDescription());

		System.out.println("File content type: " + file1.getContentType());

		String filename = file1.getOriginalFilename();

		byte[] fileData = file1.getBytes();

		registrationDao.saveFile(fileData, email, filename, sharedEmail, readFile, writeFile);

		byte[] fileData1 = file1.getBytes();

		String path = session.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "files"
				+ File.separator + file1.getOriginalFilename();

		System.out.println(path);

		try {
			FileOutputStream fos = new FileOutputStream(path);

			fos.write(fileData);

			registrationDao.saveFileLocalDatabase(fileData1);

			fos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File is not uploaded");
		}

		return "Uploaded successfully!";
	}

	// ------------------------------------------------------------------------------------

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Registration save(@RequestBody Registration r) {

//		registration.setEmail(Email);
//		registration.setPassword(Password);
//		registration.setDesignation(Designation);
		registrationDao.save(r);

		return r;

	}

	// --------------Request body of mail for otp and password change request

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/otpValidationTest", method = RequestMethod.POST)
	@ResponseBody
	public int otpAndPasswordValidation(@RequestBody Registration r) {

		int OTP = new Random().nextInt(9999);
		String message = "Your One Time Password is :-  " + OTP + " And it will be valid for 180 sec";
		String subject = "Your One Time Password is";

		String from = "Vinayakhouse123@gmail.com";

		System.out.println(r.getEmail());

		logic.sendEmail(message, subject, r.getEmail(), from);
		return OTP;

	}

//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/sendEmail", method = RequestMethod.POST)
//	@ResponseBody
//	public int login(@RequestParam String to) {
//
//		int OTP = new Random().nextInt(9999);
//		String message = "Your One Time Password is :-  " + OTP + " And it will be valid for 180 sec";
//		String subject = "Your One Time Password is";
//
//		String from = "Vinayakhouse123@gmail.com";
//		logic.sendEmail(message, subject, to, from);
//		return OTP;
//
//	}

	// --------------------------------------------Display
	// Data--------------------------------------------//

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public List<Registration> userValidation(Model m) {
		List<Registration> list = registrationDao.getEmployees();

		return list;
	}

	// -------------------------------------------- Display File

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/fileDisplay", method = RequestMethod.GET)
	public List<Registration> fileDisplayMethod(@RequestParam String email) {
		List<Registration> list = registrationDao.getFileData(email);

		return list;
	}

	// --------------------------------Test

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/otpValidationTest1", method = RequestMethod.POST)
	public int otpValidation() {

		return 3333;
	}

	// ------------------------------------------------ Password
	// Validation----------------------------//

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/passwordValidation", method = RequestMethod.POST)
	public int passValidation(@RequestBody Registration registration) {

		return 3333;
	}

//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/passwordValidation", method = RequestMethod.PUT)
//	public Registration passValidatio(@RequestParam String email,String password) {
//
//		registration.setEmail(email);
//		registration.setPassword(password);
////		registration.setDesignation(Designation);
//		registrationDao.updatePassword(registration);
//
//		return registration;
//
//	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/password1", method = RequestMethod.POST)
	public Registration passwordUpdate(@RequestBody Registration registration) {

//		registration.setDesignation(Designation);
		registrationDao.updatePassword(registration);

		return registration;

	}

	// ================================== FOR REQUEST PARAM SENDING DATA FROM THE
	// POSTMAN

//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/password1", method = RequestMethod.POST)
//	public Registration passwordUpdate(@RequestParam String Email, String Password) {
//
////		registration.setDesignation(Designation);
//		registration.setEmail(Email);
//		registration.setPassword(Password);
//		registrationDao.updatePassword(registration);
//
//		return registration;
//
//	}

	// ------------------------------------------------ Access to be given to other
	// user.

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/sharedFile", method = RequestMethod.POST)
	public Registration shareFileDatabase(@RequestParam String email, @RequestParam String sharedEmail,
			@RequestParam String fileName, @RequestParam String readFile, @RequestParam String writeFile,@RequestParam String shareFile) {

		r.setEmail(email);
		r.setSharedEmail(sharedEmail);
		r.setFileName(fileName);

		r.setReadFile(readFile);

		r.setWriteFile(writeFile);
		
		r.setShareFile(shareFile);
		
		System.out.println("Share file status: " + shareFile);

		System.out.println(readFile);
		System.out.println(writeFile);
		
		if(shareFile.equals("true")) {
			
			registrationDao.co_OwnerAuthorityAccess(r);
			
			
		}	else {
			
			registrationDao.co_OwnerAuthorityRemove(r);
			
		}

		registrationDao.sharedFile(r);

		return r;
	}
	// ---------------------------------------- Remove Shared File Access

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/removeSharedFile", method = RequestMethod.POST)
	public Registration removeShareFileDatabase(@RequestParam String email, @RequestParam String sharedEmail,
			@RequestParam String fileName, @RequestParam boolean readFile, @RequestParam boolean writeFile) {

		r.setEmail(email);
		r.setSharedEmail(sharedEmail);
		r.setFileName(fileName);

		System.out.println(readFile);
		System.out.println(writeFile);
		registrationDao.removeSharedFile(r);

		return r;
	}

//	//--------------------------------------- Display shared File
//	
//	@CrossOrigin(origins = "http://localhost:4200")
//	@RequestMapping(value = "/sharedFileDisplay", method = RequestMethod.GET)
//	public List<Registration> sharedFileDisplayMethod(@RequestParam String email, @RequestParam String design,@RequestParam String readFile, @RequestParam String writeFile) {
//		List<Registration> list = registrationDao.getSharedFileData(email,design,readFile,writeFile);
//
//		return list;
//	}

	// --------------------------------------- Display shared File

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/sharedFileDisplay", method = RequestMethod.GET)
	public List<Registration> sharedFileDisplayMethod(@RequestParam String email, @RequestParam String design,
			@RequestParam String readFile, @RequestParam String writeFile) {
		List<Registration> list = registrationDao.getSharedFileData(email, design, readFile, writeFile);

		return list;
	}

	// ------------------------------------------- Display Received Files

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/receivedFileDisplay", method = RequestMethod.GET)
	public List<Registration> receivedFileDisplay(@RequestParam String email) {

		List<Registration> list = registrationDao.getReceivedFileData(email);

		return list;
	}

	// -------------------------------------------- getText

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/getTextData", method = RequestMethod.GET)
	public @ResponseBody String getText1(@RequestParam String fileName) throws IOException {
		String txt = registrationDao.getText(fileName);

		return txt;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/setTextUpdatedData", method = RequestMethod.GET)
	public @ResponseBody String setText1(@RequestParam String fileName, @RequestParam String updatedData)
			throws IOException {

		System.out.println(updatedData);

		registrationDao.setText(fileName, updatedData);

		return "done";
	}
	
	//-------------------------------  Delete File Owner

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/deleteFile", method = RequestMethod.GET)
	public @ResponseBody String deleteFile(@RequestParam String fileName,@RequestParam String email) throws IOException {
		
		r.setFileName(fileName);
		
		r.setEmail(email);
		
		String deletedConfirm = registrationDao.deleteOwnerFile(r);

		return deletedConfirm ;
	}
}
