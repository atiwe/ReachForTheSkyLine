package Domain;

import java.util.UUID;

public class Customer {

	private int id;
	
	private String name;
	
	private String email;
	
	private String telephone;
	
	private String personalNumber;
	
	private String bank;
	
	private String discountCode;
	
	private int scheduledFlightID;
	
	Customer(){
	}
	
	public Customer(int ID, String Name, String Email, String Telephone, String PersonalNumber, String Bank, String DiscountCode, int ScheduledFlightID) {
		this.id = ID;
		this.name = Name;
		this.email = Email;
		this.telephone = Telephone;
		this.personalNumber = PersonalNumber;
		this.bank = Bank;
		this.discountCode = DiscountCode;
		this.scheduledFlightID = ScheduledFlightID;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
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
	
	public String getBank() {
		return bank;
	}
	
	public void setBank(String bank) {
		this.bank = bank;
	}
	
	public String getDiscountCode() {
		return discountCode;
	}
	
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
	
	public int getScheduledFlightID() {
		return scheduledFlightID;
	}
	
	public void setScheduledFlightID(int scheduledFlightID) {
		this.scheduledFlightID = scheduledFlightID;
	}
}
