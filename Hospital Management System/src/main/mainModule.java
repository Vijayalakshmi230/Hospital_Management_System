package main;

import dao.IHospitalService;
import dao.IHospitalServiceImpl;
import entity.Doctor;
import entity.Patient;
import entity.Appointment;
import java.util.*;

public class mainModule {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		IHospitalService impl = new IHospitalServiceImpl();

		while (true) {
			System.out.println("\n-----------Hospital Management System------------\n");
			System.out.println("1.  Add Patient");
			System.out.println("2.  Add Doctor");
			System.out.println("3.  schedule Appointment");
			System.out.println("4.  Update Appointment");
			System.out.println("5.  Cancel Appointment");
			System.out.println("6.  Get Appointment By ID");
			System.out.println("7.  Get Appointment Of Patient");
			System.out.println("8.  Get Appointment Of Doctor");
			System.out.println("0.  Exit");
			System.out.print("\nEnter your choice: ");

			int choice = sc.nextInt();
			sc.nextLine();
			try {
				switch (choice) {
//				Add Patient
				case 1: {
					System.out.println("Enter Patient ID : ");
					int patientId = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter FirstName : ");
					String firstName = sc.nextLine();
					System.out.println("Enter LastName : ");
					String lastName = sc.nextLine();
					System.out.println("Enter Date Of Birth : ");
					String dateOfBirth = sc.nextLine();
					System.out.println("Enter Gender : ");
					String gender = sc.nextLine();
					System.out.println("Enter Contact Number : ");
					String contactNumber = sc.nextLine();
					System.out.println("Enter Adress Of the Patient :");
					String address = sc.nextLine();
					impl.addPatient(new Patient(patientId, firstName, lastName, dateOfBirth, gender, contactNumber, address));
					System.out.println("Patient was added for registration");
					break;
				}
//				Add Doctor
				case 2: {
					System.out.println("Enter Doctor ID : ");
					int doctorId = sc.nextInt();
					sc.nextLine();
					System.out.println("Enter FirstName : ");
					String firstName = sc.nextLine();
					System.out.println("Enter LastName : ");
					String lastName = sc.nextLine();
					System.out.println("Enter Specialization :");
					String specialization = sc.nextLine();
					System.out.println("Enter Contact Naumber : ");
					String contactNumber = sc.nextLine();
					impl.addDoctor(new Doctor(doctorId, firstName, lastName, specialization, contactNumber));
					System.out.println("Doctor Profile was added sucessfully");
					break;
				}
				case 3: {
				    System.out.println("Enter appointment ID : ");
				    int appointmentId = sc.nextInt(); sc.nextLine();

				    System.out.println("Enter patient ID : ");
				    int patientId = sc.nextInt(); sc.nextLine();

				    if (impl.getAppointmentsForPatient(patientId) == null) {
				        System.out.println("Patient ID not found. Please add the patient first.");
				        break;
				    }

				    System.out.println("Enter Doctor ID : ");
				    int doctorId = sc.nextInt(); sc.nextLine();

				    if (impl.getAppointmentsForDoctor(doctorId) == null) {
				        System.out.println("Doctor ID not found. Please add the doctor first.");
				        break;
				    }

				    System.out.println("Enter appointment date : ");
				    String appointmentDate = sc.nextLine();

				    System.out.println("Enter description : ");
				    String description = sc.nextLine();

				    impl.scheduleAppointment(new Appointment(appointmentId, patientId, doctorId, appointmentDate, description));
				    System.out.println("Booked an appointment for patient successfully.");
				    break;
				}

				case 4: {
//				 update appointment
					System.out.println("Enter appointment ID : ");
					int appointmentId = sc.nextInt(); sc.nextLine();
					System.out.println("Enter patient ID : ");
					int patientId = sc.nextInt(); sc.nextLine();
					System.out.println("Enter Doctro ID : ");
					int doctorId = sc.nextInt(); sc.nextLine();
					System.out.println("Enter appointment date : ");
					String appointmentDate = sc.nextLine();
					System.out.println("Enter description : ");
					String description = sc.nextLine();
					impl.updateAppointment(new Appointment(appointmentId, patientId, doctorId, appointmentDate, description));
					System.out.println("Booked any appointment for patient Sucessfully. ");
					break;
				}
//				cancel appointment
				case 5: {
					System.out.println("Enter appointmentId : ");
					int appointmentId = sc.nextInt(); sc.nextLine();
					System.out.println(impl.cancelAppointment(appointmentId));
					System.out.println("Appointment was cancelled sucessfully. ");
					break;
				}
//				get appointment by id
				case 6: {
					System.out.println("Enter appointmentId : ");
					int appointmentId = sc.nextInt(); sc.nextLine();
					System.out.println(impl.getAppointmentById(appointmentId));
					System.out.println("Appointment was displayed sucessfully");
					break;
				}
//				get appointments for patient
				case 7: {
					System.out.println("Enter patient ID : ");
					int patientId = sc.nextInt(); sc.nextLine();
					System.out.println(impl.getAppointmentsForPatient(patientId));
					System.out.println("Appointment of patient was displayed sucessfully");
					break;
				}
//				get appointment for doctor
				case 8: {
					System.out.println("Enter Doctro ID : ");
					int doctorId = sc.nextInt(); sc.nextLine();
					System.out.println(impl.getAppointmentsForDoctor(doctorId));
					System.out.println("Appointment of doctor was displayed sucessfully");
					break;
				}
//				End
				case 0: {
					System.out.println("Exiting... Bye!");
					sc.close();
					System.exit(0);
					break;
				}
				default:
					System.out.println("Invalid choice!");
				}
			} 
			catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
	}
}
