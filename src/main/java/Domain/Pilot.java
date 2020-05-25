package Domain;

import java.util.UUID;

public class Pilot {

	private UUID id;
	
	private String name;
	
	private String email;
	
	private String telephone;
	
	private String personalNumber;
	
	private String employmentDate;
	
	private String pilotLicense;
	
	private String weeklyFlightHours;
	
	private String lastFlight;
	
	private String nextFlight;
	
	Pilot(){
	}
	
	public Pilot(UUID ID, String Name, String Email, String Telephone, String PersonalNumber, String EmploymentDate, String PilotLicense, String WeeklyFlightHours, String LastFlight, String NextFlight) {
		this.id = ID;
		this.name = Name;
		this.email = Email;
		this.telephone = Telephone;
		this.personalNumber = PersonalNumber;
		this.employmentDate = EmploymentDate;
		this.pilotLicense = PilotLicense;
		this.weeklyFlightHours = WeeklyFlightHours;
		this.lastFlight = LastFlight;
		this.nextFlight = NextFlight;
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
	
	public String getPilotLicense() {
		return pilotLicense;
	}
	
	public void setPilotLicense(String pilotLicense) {
		this.pilotLicense = pilotLicense;
	}
	
	public String getWeeklyFlightHours() {
		return weeklyFlightHours;
	}
	
	public void setWeeklyFlightHours(String weeklyFlightHours) {
		this.weeklyFlightHours = weeklyFlightHours;
	}
	
	public String getLastFlight() {
		return lastFlight;
	}
	
	public void setLastFlight(String lastFlight) {
		this.lastFlight = lastFlight;
	}
	
	public String getNextFlight() {
		return nextFlight;
	}
	
	public void setNextFlight(String nextFlight) {
		this.nextFlight = nextFlight;
	}
}
