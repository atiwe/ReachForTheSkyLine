package Domain;

public class FlightRequest {

	private int id;
	
	private int customerId;
	
	private String departureCity;
	
	private String arrivalCity;
	
	private String startTime;
	
	FlightRequest(){
	}
	
	public FlightRequest(int ID, int CustomerID, String DepartureCity, String ArrivalCity, String StartTime) {
		this.id = ID;
		this.customerId = CustomerID;
		this.departureCity = DepartureCity;
		this.arrivalCity = ArrivalCity;
		this.startTime = StartTime;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getCustomerID() {
		return customerId;
	}
	
	public void setCustomerID(int customerId) {
		this.customerId = customerId;
	}
	
	public String getDepartureCity() {
		return departureCity;
	}
	
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	
	public String getArrivalCity() {
		return arrivalCity;
	}
	
	public void setArrivalCity(String arrivalCity) {
		this.arrivalCity = arrivalCity;
	}
	
	public String getStartTime() {
		return startTime;
	}
	
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
