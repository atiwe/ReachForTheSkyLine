package Domain;

import java.util.UUID;

public class Employee {

	private UUID id;
	
	private String name;
	
	private String email;
	
	private String telephone;
	
	private String personalNumber;
	
	private String employmentDate;
	
	Employee(){
	}
	
	public Employee(UUID ID, String Name, String Email, String Telephone, String PersonalNumber, String EmploymentDate) {
		this.id = ID;
		this.name = Name;
		this.email = Email;
		this.telephone = Telephone;
		this.personalNumber = PersonalNumber;
		this.employmentDate = EmploymentDate;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setID(UUID id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getPersonalNumber() {
		return personalNumber;
	}
	
	public void setPersonalNumber(String personalNumber) {
		this.personalNumber = personalNumber;
	}
	
	public String getEmploymentDate() {
		return employmentDate;
	}
	
	public void setEmploymentDate(String employmentDate) {
		this.employmentDate = employmentDate;
	}
}
