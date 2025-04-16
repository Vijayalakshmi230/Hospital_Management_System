package entity;

public class Appointment {
    private int appointmentId;
    private int doctorId;
    private int patientId;
    private String appointmentDate;
    private String description;

    public Appointment(int appointmentId, int doctorId, int patientId, String appointmentDate, String description) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.appointmentDate = appointmentDate;
        this.description = description;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AppointmentID: " + appointmentId +
               ", DoctorID: " + doctorId +
               ", PatientID: " + patientId +
               ", AppointmentDate: " + appointmentDate +
               ", Description: " + description;
    }
}
