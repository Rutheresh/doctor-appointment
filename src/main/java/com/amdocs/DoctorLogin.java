package com.amdocs;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.amdocs.dao.DoctorDao;
import com.amdocs.dao.impl.DoctorDaoImpl;
import com.amdocs.entity.Appointment;
import com.amdocs.entity.Customer;

public class DoctorLogin {
	
	private Scanner sc = new Scanner(System.in);
	private static DoctorDao dao = new DoctorDaoImpl();
	
	DoctorLogin() {
		while(true) {
			System.out.println("\n-------------------------Doctor Login-------------------------");
			System.out.println("Enter your choice");
			System.out.println("1. View all customer details \n2. View all appointments\n 0.Exit");
		
			int choice = Integer.parseInt(sc.nextLine());
			
			switch(choice) {
			case 1:
				System.out.println("----All Customer Details----");
				displayEveryCustomer();
				break;
			
			case 2:
				System.out.println("----All Appointments----");
				displayEveryAppointment();
				break;
				
			case 0:
				System.exit(0);
				break;
				
			default:
				System.out.println("Invalid option, please try again");
				break;
			}
		}
		
	}
	
	private static void displayEveryCustomer() {
		try {
			List<Customer> CustomerList = dao.displayAllCustomers();
			Iterator<Customer> iterator = CustomerList.iterator();
			while(iterator.hasNext())
				System.out.println(iterator.next());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private static void displayEveryAppointment() {
		try {
			List<Appointment> appointmentList = dao.displayAllAppointments();
			Iterator<Appointment> iterator = appointmentList.iterator();
			while(iterator.hasNext())
				System.out.println(iterator.next());
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}
