package entity;

public class Doctor {
	private int doctorId;
	private String firstname;
	private String lastname;
	private String specialization;
	private String contactNumber;
	
	public Doctor(int doctorId, String firstname, String lastname, String specialization, String contactNumber)
	{
		this.doctorId=doctorId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.specialization=specialization;
		this.contactNumber=contactNumber;
	}
	
	public int getDoctorId() {return doctorId;}
	public void setDoctorId(int doctorId) {this.doctorId=doctorId;}
	
	public String getFirstName() {return firstname;}
	public void setFirstName(String firstname) {this.firstname=firstname;}
	
	public String getLastName() {return lastname;}
	public void setLastName(String lastname) {this.lastname = lastname;}
	
	public String getSpecialization() {return specialization;}
	public void setSpecialization(String specialization) {this.specialization=specialization;}
	
	public String getContactNumber() {return contactNumber;}
	public void setContactNumber(String contactNumber) {this.contactNumber=contactNumber;}
	
	@Override
	public String toString() {
		return "DoctorId: " + doctorId +
		", FirstName: " + firstname +
		", LastName: " + lastname +
		", Specialization: "+ specialization +
		", ContactNumber: "+ contactNumber;
	}
}
