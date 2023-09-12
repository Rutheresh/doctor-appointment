package com.amdocs.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.amdocs.dao.DoctorDao;
import com.amdocs.entity.*;
import com.amdocs.util.DbUtil;
import com.amdocs.exception.*;



public class DoctorDaoImpl implements DoctorDao{
	private final static String selectAllCustomers = "SELECT *FROM PATIENT";
	private final static String selectCustomerByPhone = "SELECT *FROM PATIENT WHERE PHONENUMBER = ? ";
	private final static String insertCustomer = "INSERT INTO PATIENT(firstName, lastName, age, gender, phoneNumber, email, place, password ) VALUES(?,?,?,?,?,?,?,?)";
	private final static String modifyCustomer = "UPDATE PATIENT SET firstName = ?, lastName = ?, age = ?, gender = ?, phoneNumber = ?, email = ?, place = ?, password = ? where phoneNumber = ?";
	private final static String deleteCustomer = "DELETE FROM PATIENT WHERE phoneNumber = ? ";
	
	private final static String selectAllAppointments = "SELECT *FROM APPOINTMENT";
	private final static String selectAppointment = "SELECT *FROM APPOINTMENT WHERE PATIENTID = ?";
	private final static String insertAppointment = "INSERT INTO APPOINTMENT(patientId, doctorId, reasonForAppointment) VALUES(?,?,?)";
	private final static String modifyAppointment = "UPDATE APPOINTMENT SET patientId = ?, doctorId = ?, reasonForAppointment = ?";
	private final static String deleteAppointment = "DELETE FROM APPOINTMENT WHERE id = ?";
	
	private final static String selectAllDoctors = "SELECT *FROM DOCTOR";
	private Connection connection = DbUtil.getConnection();

	@Override
	public boolean addCustomer(Customer patient) throws SQLException {
		boolean result = false;
		PreparedStatement stmt = connection.prepareStatement(insertCustomer);
		stmt.setString(1, patient.getFirstName());
		stmt.setString(2, patient.getLastName());
		stmt.setLong(3, patient.getAge());
		stmt.setString(4, patient.getGender());
		stmt.setLong(5, patient.getPhoneNumber());
		stmt.setString(6, patient.getEmail());
		stmt.setString(7, patient.getPlace());
		stmt.setString(8, patient.getPassword());
		
		if(stmt.executeUpdate() > 0)
			result = true;
		stmt.close();
		return result;
	}
	
	@Override
	public Customer displayCustomer(long phone) throws CustomerNotFoundException, SQLException {
		Customer patient = null;
		PreparedStatement stmt = connection.prepareStatement(selectCustomerByPhone);
		stmt.setLong(1, phone);
		ResultSet rs = stmt.executeQuery();
		//System.out.println("Rs : " + rs);
		if(rs.next()) {
			patient = new Customer();
			
			patient.setId(rs.getInt("id"));
			patient.setPassword(rs.getNString("password"));
			patient.setFirstName(rs.getString("firstName"));
			patient.setLastName(rs.getString("lastName"));
			patient.setAge(rs.getInt("age"));
			patient.setGender(rs.getString("gender"));
			patient.setPhoneNumber(rs.getLong("phoneNumber"));
			patient.setEmail(rs.getString("email"));
			patient.setPlace(rs.getString("place"));
		}
		else {
			throw new CustomerNotFoundException("Phone Number is incorrect");
		}
		rs.close();
		stmt.close();
		return patient;
	}

	@Override
	public List<Customer> displayAllCustomers() throws SQLException{
		List<Customer> patients = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(selectAllCustomers);
		while (rs.next()) {
			Customer patient = new Customer();
			
			patient.setId(rs.getInt("id"));
			patient.setPassword(rs.getNString("password"));
			patient.setFirstName(rs.getString("firstName"));
			patient.setLastName(rs.getString("lastName"));
			patient.setAge(rs.getInt("age"));
			patient.setGender(rs.getString("gender"));
			patient.setPhoneNumber(rs.getLong("phoneNumber"));
			patient.setEmail(rs.getString("email"));
			patient.setPlace(rs.getString("place"));
			//Adding to the list
			patients.add(patient);
		}
		rs.close();
		stmt.close();
		return patients;
	}

	@Override
	public List<Appointment> displayAllAppointments() throws SQLException{
		List<Appointment> appointments = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(selectAllAppointments);
		while(rs.next()) {
			Appointment appointment = new Appointment();
			
			appointment.setAppointmentId(rs.getInt("id"));
			appointment.setPatientId(rs.getInt("patientId"));
			appointment.setDoctorId(rs.getInt("doctorId"));
			appointment.setReasonForAppointment(rs.getString("reasonForAppointment"));
			
			appointments.add(appointment);
			
		}
		rs.close();
		stmt.close();
		return appointments;
	}

	@Override
	public boolean bookAppointment(Appointment appointment) throws SQLException {
		boolean result = false;
		PreparedStatement stmt = connection.prepareStatement(insertAppointment);
		stmt.setInt(1, appointment.getPatientId());
		stmt.setInt(2, appointment.getDoctorId());
		stmt.setString(3, appointment.getReasonForAppointment());
		if(stmt.executeUpdate()>0) {
			result = true;
		}
		stmt.close();
		return result;
	}

	@Override
	public Appointment displayAppointment(int patientId) throws AppointmentNotFoundException, SQLException{
		Appointment appointment = null;
		PreparedStatement stmt = connection.prepareStatement(selectAppointment);
		stmt.setInt(1, patientId);
		ResultSet rs = stmt.executeQuery();
		//System.out.println("Rs  : "  + rs);
		if(rs.next()) {
			appointment = new Appointment();
			// Reading the data from the row and setting the data to student object
			appointment.setAppointmentId(rs.getInt("id"));
			appointment.setPatientId(rs.getInt("patientId"));
			appointment.setDoctorId(rs.getInt("doctorId"));
			appointment.setReasonForAppointment(rs.getString("reasonForAppointment"));			
		}else {
			throw new AppointmentNotFoundException("Appointment not found with the id " + patientId);
		}
		rs.close();
		stmt.close();
		return appointment;
	}

	@Override
	public void modifyCustomer(Customer patient) throws CustomerNotFoundException, SQLException {
		PreparedStatement stmt = connection.prepareStatement(modifyCustomer);
		
		stmt.setString(1, patient.getFirstName());
		stmt.setString(2, patient.getLastName());
		stmt.setLong(3, patient.getAge());
		stmt.setString(4, patient.getGender());
		stmt.setLong(5, patient.getPhoneNumber());
		stmt.setString(6, patient.getEmail());
		stmt.setString(7, patient.getPlace());
		stmt.setString(8, patient.getPassword());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public void deleteCustomer(long phone) throws CustomerNotFoundException, SQLException {
		PreparedStatement stmt = connection.prepareStatement(deleteCustomer);
		stmt.setLong(1, phone);
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public void modifyAppointment(Appointment appointment) throws AppointmentNotFoundException, SQLException {
		PreparedStatement stmt = connection.prepareStatement(modifyAppointment);
		
		stmt.setInt(1, appointment.getPatientId());
		stmt.setInt(2, appointment.getDoctorId());
		stmt.setString(3, appointment.getReasonForAppointment());
		
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public List<Doctor> displayAllDoctors() throws SQLException {
		List<Doctor> doctors = new ArrayList<>();
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(selectAllDoctors);
		while(rs.next()) {
			Doctor doctor = new Doctor();
			
			doctor.setDoctorId(rs.getInt("id"));
			doctor.setDoctorName(rs.getString("doctorName"));
			doctor.setSpecialisation(rs.getString("specialisation"));
			doctor.setPhoneNumber(rs.getLong("phoneNumber"));
			
			doctors.add(doctor);
			
		}
		rs.close();
		stmt.close();
		return doctors;
	}
 
	@Override
	public void deleteAppointment(int id) throws AppointmentNotFoundException, SQLException {
		PreparedStatement stmt = connection.prepareStatement(deleteAppointment);
		stmt.setInt(1, id);
		stmt.executeUpdate();
		stmt.close();
		
	}

	

	

}
