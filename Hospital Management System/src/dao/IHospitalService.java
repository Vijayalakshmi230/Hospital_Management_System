package dao;

import java.util.List;
import entity.Doctor;
import entity.Patient;
import entity.Appointment;
import exception.PatientNumberNotFoundException;

public interface IHospitalService {

    // Add a new patient
    boolean addPatient(Patient patient);

    // Add a new doctor
    boolean addDoctor(Doctor doctor);

    // Get an appointment by its ID
    Appointment getAppointmentById(int appointmentId);

    // Get all appointments for a specific patient
    List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException;

    // Get all appointments for a specific doctor
    List<Appointment> getAppointmentsForDoctor(int doctorId);

    // Schedule a new appointment
    boolean scheduleAppointment(Appointment appointment);

    // Update an existing appointment
    boolean updateAppointment(Appointment appointment);

    // Cancel an appointment
    boolean cancelAppointment(int appointmentId);
}
