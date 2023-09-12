package com.amdocs;

import java.util.Scanner;

/**
 * 
 * Main Doctor Appointment App
 *
 */
public class DoctorAppointmentApp {	
	
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		
		while(true) {
			System.out.println("-------------------------Doctor Appointment Hub-------------------------");
			System.out.println("Enter your choice");
			System.out.println("1. Customer Profile \n2. Doctor Login \n3. Appointment\n0. Exit");
			
			int choice = Integer.parseInt(sc.nextLine());
			switch(choice) {
			case 1:
				CustomerProfile customerProfile = new CustomerProfile();
				break;
				
			case 2:
				DoctorLogin dl = new DoctorLogin();
				break;
				
			case 3:
				AppointmentSection appointment = new AppointmentSection();
				break;
				
			case 0:
				System.exit(0);
			
			default:
				System.out.println("Invalid option, try again");
				
			}
		}

	}

}
