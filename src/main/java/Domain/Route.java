package Domain;

public class Route {

	private int id;
	
	private String departureCity;
	
	private String arrivalCity;
	
	private String flightDuration;
	
	private String price;
	
	Route(){
	}
	
	public Route(int ID, String DepartureCity, String ArrivalCity, String FlightDuration, String Price) {
		this.id = ID;
		this.departureCity = DepartureCity;
		this.arrivalCity = ArrivalCity;
		this.flightDuration = FlightDuration;
		this.price = Price;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
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
	
	public String getFlightDuration() {
		return flightDuration;
	}
	
	public void setFlightDuration(String flightDuration) {
		this.flightDuration = flightDuration;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

}
