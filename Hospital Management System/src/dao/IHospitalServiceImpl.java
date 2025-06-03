package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.Appointment;
import entity.Doctor;
import exception.PatientNumberNotFoundException;
import db.DBUtil;

public class IHospitalServiceImpl implements IHospitalService {

	private Connection con;

	public IHospitalServiceImpl() {
		con = DBUtil.getDBConn();
	}

//	add Patient
	@Override
	public boolean addPatient(entity.Patient Patient) {
//		query
		String sql = "insert into patient (patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address) values (?, ?, ?, ?, ?, ?, ?) ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, Patient.getPatientId());
			stmt.setString(2, Patient.getFirstName());
			stmt.setString(3, Patient.getLastName());
			stmt.setString(4, Patient.getDateOfBirth());
			stmt.setString(5, Patient.getGender());
			stmt.setString(6, Patient.getContactNumber());
			stmt.setString(7, Patient.getAddress());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//   add Doctor
	@Override
	public boolean addDoctor(Doctor doctor) {
//		query
		String sql = "insert into doctor (doctorId, firstName, lastName, specialization, contactNumber)  values (?, ?, ?, ?, ?) ";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, doctor.getDoctorId());
			stmt.setString(2, doctor.getFirstName());
			stmt.setString(3, doctor.getLastName());
			stmt.setString(4, doctor.getSpecialization());
			stmt.setString(5, doctor.getContactNumber());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public boolean scheduleAppointment(Appointment appointment) {
	    String sql = "INSERT INTO appointment (appointmentId, doctorId, patientId, appointmentDate, description) VALUES (?, ?, ?, ?, ?)";
	    try (PreparedStatement stmt = con.prepareStatement(sql)) {
	        stmt.setInt(1, appointment.getAppointmentId());
	        stmt.setInt(2, appointment.getDoctorId());
	        stmt.setInt(3, appointment.getPatientId());
	        stmt.setString(4, appointment.getAppointmentDate());
	        stmt.setString(5, appointment.getDescription());

	        int rowsInserted = stmt.executeUpdate();
	        return rowsInserted > 0;
	    } catch (SQLException e) {
	        e.printStackTrace(); // Show full DB error
	        throw new RuntimeException("Error scheduling appointment", e);
	    }
	}

//  update appointment
	@Override
	public boolean updateAppointment(Appointment appointment) {
//		query
		String sql = "UPDATE appointments SET doctorId = ?, patientId = ?, appointmentDate = ?, description = ? WHERE appointmentId = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, appointment.getDoctorId());
			stmt.setInt(2, appointment.getPatientId());
			stmt.setString(3, appointment.getAppointmentDate());
			stmt.setString(4, appointment.getDescription());
			stmt.setInt(5, appointment.getAppointmentId());

			int rowsUpdated = stmt.executeUpdate();
			return rowsUpdated > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Error updating appointment", e);
		}
	}

// cancel appointment
	@Override
	public boolean cancelAppointment(int appointmentId) {
//		query
		String sql = "DELETE FROM appointments WHERE appointmentId = ?";
		try (PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, appointmentId);
			int rowsDeleted = stmt.executeUpdate();
			return rowsDeleted > 0;
		} catch (SQLException e) {
			throw new RuntimeException("Error canceling appointment with ID: " + appointmentId, e);
		}
	}

//  get appointment by id
	@Override
	public Appointment getAppointmentById(int appointmentId) {
		Appointment appointment = null;
		try {
//			query
			String sql = "SELECT * FROM appointments WHERE appointmentId = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, appointmentId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				appointment = new Appointment(rs.getInt("appointmentId"), rs.getInt("doctorId"), rs.getInt("patientId"),
						rs.getString("appointmentDate"), rs.getString("description"));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Database Error while fetching the appointmentId: " + appointmentId, e);
		}
		return appointment;
	}

//	get appointments for patient
	@Override
	public List<Appointment> getAppointmentsForPatient(int patientId) throws PatientNumberNotFoundException {
		List<Appointment> appointments = new ArrayList<>();
		try {
//			query
			String sql = "SELECT * FROM appointments WHERE patientId = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, patientId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				appointments.add(new Appointment(rs.getInt("appointmentId"), rs.getInt("doctorId"),
						rs.getInt("patientId"), rs.getString("appointmentDate"), rs.getString("description")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Database Error while fetching patientId: " + patientId, e);
		}

		if (appointments.isEmpty()) {
			throw new PatientNumberNotFoundException("No appointments found for patient ID: " + patientId);
		}

		return appointments;
	}

//	get appointments for doctors
	@Override
	public List<Appointment> getAppointmentsForDoctor(int doctorId) {
		List<Appointment> appointments = new ArrayList<>();
		try {
//			query
			String sql = "SELECT * FROM appointments WHERE doctorId = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, doctorId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				appointments.add(new Appointment(rs.getInt("appointmentId"), rs.getInt("doctorId"),
						rs.getInt("patientId"), rs.getString("appointmentDate"), rs.getString("description")));
			}
		} catch (SQLException e) {
			throw new RuntimeException("Database Error while fetching doctorId: " + doctorId, e);
		}

		return appointments;
	}
}
