package com.amdocs;

import java.sql.SQLException;
import java.util.Scanner;

import com.amdocs.dao.DoctorDao;
import com.amdocs.dao.impl.DoctorDaoImpl;
import com.amdocs.entity.Customer;
import com.amdocs.exception.CustomerNotFoundException;

public class CustomerProfile {
	
	private static Scanner sc = new Scanner(System.in);
	private static DoctorDao dao = new DoctorDaoImpl();
	
	CustomerProfile() {
		try {
			String continueFlag;
			do {
				System.out.println("\n-------------------------Customer Profile-------------------------");
				System.out.println("Enter your choice");
				System.out.println("1. New Registration \n2. Modify details \n3. Delete customer records\n4. View your profile \n 0. Exit");
			
			
				int choice = Integer.parseInt(sc.nextLine());
				switch(choice) {
				case 1:
					System.out.println("----New Registration----");
					InputCustomers();
					System.out.println("----New Customer Added----");
					break;
				
				case 2:
					System.out.println("----Modify Your Details ");
					ModifyCustomers(); //login page required
					System.out.println("----Details Modified----");
					break;
					
				case 3:
					System.out.println("----Deleting Customer Record----");
					DeleteCustomers(); //login page required
					System.out.println("----Your details have been deleted");
					break;
					
				case 4:
					DisplayCustomer(); //login page required
					break;
					
				case 0:
					System.exit(0);
					break;
				default:
					System.out.println("Invalid option, try again");
					
				}
				
				System.out.println("Do you want to enter this menu again: Y/N");
				continueFlag = sc.nextLine();
				
			}while(continueFlag == "Y" || continueFlag == "y");
		}catch(NumberFormatException e) {
			System.out.println(e);
		}
		
		
	}
	
	private static Customer createCustomer() {
		Customer obj = new Customer();
		try {
			System.out.println("Enter the First Name: ");
			String firstName = sc.nextLine(); 
			obj.setFirstName(firstName);
			
			System.out.println("Enter the Last Name: ");
			String lastName = sc.nextLine();
			obj.setLastName(lastName);
			
			System.out.println("Enter the Age: "); //-ve exception
			int age = Integer.parseInt(sc.nextLine());
			obj.setAge(age);
			
			System.out.println("Enter your Gender: ");
			String gender = sc.nextLine();
			obj.setGender(gender);
			
			System.out.println("Enter your Phone Number: "); //phone format exception
			long phone = Long.parseLong(sc.nextLine());
			obj.setPhoneNumber(phone);
			
			System.out.println("Enter your email Id: ");
			String mail = sc.nextLine();
			obj.setEmail(mail);
			
			System.out.println("Enter your city/town/village name: ");
			String place = sc.nextLine();
			obj.setPlace(place);
			
			System.out.println("Set your password");
			String pass = sc.nextLine();
			obj.setPassword(pass);
			
		} catch(NumberFormatException e) {
			System.out.println();
		}
		return obj;
	}
	
	private static void InputCustomers() {
		Customer createCustomer;
		createCustomer = createCustomer();
		try {
			dao.addCustomer(createCustomer);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private static void ModifyCustomers() {
		DisplayCustomer();
		
		System.out.println("Modify the details in the required fields");
		try {
			Customer modifyCustomer;
			modifyCustomer = createCustomer();
			dao.modifyCustomer(modifyCustomer);
		}catch (CustomerNotFoundException e) {
			System.out.println(e);
		}
		catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private static void DeleteCustomers() {
		System.out.println("Enter phone number");
		long phone = Long.parseLong(sc.nextLine());
		try {
			dao.deleteCustomer(phone);
		} catch (CustomerNotFoundException e) {
			System.out.println(e);
		}
		  catch (SQLException e) {
			System.out.println(e);
		}
	}
	
	private static void DisplayCustomer() {
		System.out.println("Enter phone number");
		long phone = Long.parseLong(sc.nextLine());
		try {
			System.out.println(dao.displayCustomer(phone));
		}catch (CustomerNotFoundException e) {
			System.out.println(e);
		}
		  catch (SQLException e) {
			System.out.println(e);
		}
	}
}
