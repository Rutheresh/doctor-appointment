package com.amdocs.dao;

import java.sql.SQLException;
import java.util.List;
import com.amdocs.entity.*;
import com.amdocs.exception.AppointmentNotFoundException;
import com.amdocs.exception.CustomerNotFoundException;

public interface DoctorDao {
	/**
	 * Adds customer details
	 * @param patient
	 * @return
	 * @throws SQLException 
	 */
	boolean addCustomer(Customer patient) throws SQLException;
	
	/**
	 * Returns the required customer details
	 * @return
	 */
	Customer displayCustomer(long phone) throws CustomerNotFoundException, SQLException ;
	
	/**
	 * Returns all patients
	 * @return
	 * @throws SQLException 
	 */
	List<Customer> displayAllCustomers() throws SQLException;
	
	
	/**
	 * 
	 * @param phone
	 * @throws CustomerNotFoundException
	 * @throws SQLException
	 */
	void deleteCustomer(long phone) throws CustomerNotFoundException, SQLException;
	
	/**
	 * Returns all appointment details
	 * @return
	 * @throws SQLException 
	 */
	List<Appointment> displayAllAppointments() throws SQLException;
	/**
	 * 
	 * @param appointment
	 * @return
	 * @throws SQLException 
	 */
	boolean bookAppointment(Appointment appointment) throws SQLException;
	
	/**
	 * Returns the required appointment details
	 * @return
	 */
	Appointment displayAppointment(int appointmentId)throws AppointmentNotFoundException, SQLException;
	
	/**
	 * 
	 * @param modifyAppointment
	 * @return
	 * @throws AppointmentNotFoundException
	 * @throws SQLException
	 */
	void modifyAppointment(Appointment modifyAppointment) throws AppointmentNotFoundException, SQLException;
	
	/**
	 * 
	 * @param id
	 * @throws AppointmentNotFoundException
	 * @throws SQLException
	 */
	void deleteAppointment(int id) throws AppointmentNotFoundException, SQLException;
	
	
	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	
	List<Doctor> displayAllDoctors() throws SQLException;

	/**
	 * 
	 * @param patient
	 * @throws CustomerNotFoundException
	 * @throws SQLException
	 */
	void modifyCustomer(Customer patient) throws CustomerNotFoundException, SQLException;

	

}