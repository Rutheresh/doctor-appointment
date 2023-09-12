package com.amdocs.entity;

import java.util.Date;

public class Appointment {
	private int appointmentId; //primary key
	private int patientId; //foreign key to customer table
	private int doctorId; // foreign key to doctor table(only sql)
	private String reasonForAppointment;
	
	
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getReasonForAppointment() {
		return reasonForAppointment;
	}
	public void setReasonForAppointment(String reasonForAppointment) {
		this.reasonForAppointment = reasonForAppointment;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", patientId=" + patientId + ", doctorId=" + doctorId
				+ ", reasonForAppointment=" + reasonForAppointment + "]";
	}
}
