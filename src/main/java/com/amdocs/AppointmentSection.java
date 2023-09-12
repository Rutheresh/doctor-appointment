package com.amdocs;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.DoctorDao;
import com.amdocs.dao.impl.DoctorDaoImpl;
import com.amdocs.entity.Appointment;
import com.amdocs.entity.Doctor;
import com.amdocs.exception.AppointmentNotFoundException;

public class AppointmentSection {
	private static Scanner sc = new Scanner(System.in);
	private static DoctorDao dao = new DoctorDaoImpl();
	
	AppointmentSection(){
		try {
			String continueFlag;
			do {
				System.out.println("\n-------------------------Appointment-------------------------");
				System.out.println("Enter a choice");
				System.out.println("1. Book an appointment \n2.Modify appointment \n3.View your appointment \n4. Delete your appointment\n0.Exit");
			
				int choice = Integer.parseInt(sc.nextLine());
				
				switch(choice) {
				case 1:
					System.out.println("----Book an Appointment----");
					displayAllDoctor();
					bookAppointment(); //login required
					break;
					
				case 2:
					System.out.println("----Modify Appointment----");
					modifyAppointment();//login required
					break;
					
				case 3:
					System.out.println("----View your Appointment----");
					displayAppointment();//login required
					break;
					
				case 4:
					System.out.println("----Delete your Appointment----");
					deleteAppointment();
					
				case 0:
					System.exit(0);
					break;
					
				default:
					System.out.println("Invalid option");
					break;
				}
				
				System.out.println("Do you want to enter this menu again: Y/N");
				continueFlag = sc.nextLine();
			}while(continueFlag == "n" || continueFlag == "N");
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		
		
	}
	
	private static Appointment createAppointment() {
		Appointment obj = new Appointment();
		try {
			System.out.println("Enter your Id");
			int patientId = Integer.parseInt(sc.nextLine());
			obj.setPatientId(patientId);
			
			System.out.println("Choose your desired doctor from the above table and enter their Id");
			int doctorId = Integer.parseInt(sc.nextLine());
			obj.setDoctorId(doctorId);
			
			System.out.println("State your reason for appointment");
			String reason = sc.nextLine();
			obj.setReasonForAppointment(reason);
		} catch(NumberFormatException e) {
			System.out.println(e);
		}
		return obj;
	}
	
	private static void bookAppointment() {
		Appointment createAppointment;
		createAppointment = createAppointment();
		try {
			dao.bookAppointment(createAppointment);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private static void modifyAppointment() {
		displayAppointment();
		System.out.println("Modify the details in the required fields");
		try {
			Appointment modifyAppointment = createAppointment();
			dao.modifyAppointment(modifyAppointment);
		} catch(AppointmentNotFoundException e){ 
			System.out.println(e);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private static void deleteAppointment() {
		System.out.println("Enter your id");
		int id = Integer.parseInt(sc.nextLine());
		try {
			dao.deleteAppointment(id);
		} catch(AppointmentNotFoundException e) {
			System.out.println(e);
		}
		  catch(SQLException e) {
			  System.out.println(e);
		  }
	}
	
	private static void displayAppointment() {
		System.out.println("Enter patient id");
		int id = Integer.parseInt(sc.nextLine());
		try {
			System.out.println(dao.displayAppointment(id));
		}catch(AppointmentNotFoundException e) {
			System.out.println(e);
		}
		  catch(SQLException e) {
			  System.out.println(e);
		  }
	}
	
	private static void displayAllDoctor() {
		try {
			List<Doctor> doctorList = dao.displayAllDoctors();
			Iterator<Doctor> iterator = doctorList.iterator();
			while(iterator.hasNext())
				System.out.println(iterator.next());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	
	
}
