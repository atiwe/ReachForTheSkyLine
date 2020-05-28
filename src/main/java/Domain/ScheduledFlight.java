package Domain;

public class ScheduledFlight {

	private int id;
	
	private String estimatedStart;
	
	private String estimatedLanding;
	
	private String flightTime;
	
	private String pilot;
	
	private int routeId;
	
	ScheduledFlight(){
	}
	
	public ScheduledFlight(int ID, String EstimatedStart, String EstimatedLanding, String FlightTime, String Pilot, int RouteID) {
		this.id = ID;
		this.estimatedStart = EstimatedStart;
		this.estimatedLanding = EstimatedLanding;
		this.flightTime = FlightTime;
		this.pilot = Pilot;
		this.routeId = RouteID;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getEstimatedStart() {
		return estimatedStart;
	}
	
	public void setEstimatedStart(String estimatedStart) {
		this.estimatedStart = estimatedStart;
	}
	
	public String getEstimatedLanding() {
		return estimatedLanding;
	}
	
	public void setEstimatedLanding(String estimatedLanding) {
		this.estimatedLanding = estimatedLanding;
	}
	
	public String getFlightTime() {
		return flightTime;
	}
	
	public void setFlightTime(String flightTime) {
		this.flightTime = flightTime;
	}
	
	public String getPilot() {
		return pilot;
	}
	
	public void setPilot(String pilot) {
		this.pilot = pilot;
	}
	
	public int getRouteID() {
		return routeId;
	}
	
	public void setRouteID(int routeId) {
		this.routeId = routeId;
	}
}
