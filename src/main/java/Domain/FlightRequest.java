package Domain;

import java.util.UUID;

public class FlightRequest {

	private UUID id;
	
	private UUID customerId;
	
	private String departureCity;
	
	private String arrivalCity;
	
	private String startTime;
	
	FlightRequest(){
	}
	
	public FlightRequest(UUID ID, UUID CustomerID, String DepartureCity, String ArrivalCity, String StartTime) {
		this.id = ID;
		this.customerId = CustomerID;
		this.departureCity = DepartureCity;
		this.arrivalCity = ArrivalCity;
		this.startTime = StartTime;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setID(UUID id) {
		this.id = id;
	}
	
	public UUID getCustomerID() {
		return customerId;
	}
	
	public void setCustomerID(UUID customerId) {
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
