package exception;

public class PatientNumberNotFoundException extends Exception{
	 public PatientNumberNotFoundException(String message) {
	        super("The Patient ID is not Found");
	    }
}
