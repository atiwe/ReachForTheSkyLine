package Domain;

import java.util.UUID;

public class ScheduledFlight {

	private UUID id;
	
	private String estimatedStart;
	
	private String estimatedLanding;
	
	private String flightTime;
	
	private String pilot;
	
	private UUID routeId;
	
	ScheduledFlight(){
	}
	
	public ScheduledFlight(UUID ID, String EstimatedStart, String EstimatedLanding, String FlightTime, String Pilot, UUID RouteID) {
		this.id = ID;
		this.estimatedStart = EstimatedStart;
		this.estimatedLanding = EstimatedLanding;
		this.flightTime = FlightTime;
		this.pilot = Pilot;
		this.routeId = RouteID;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setID(UUID id) {
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
	
	public UUID getRouteID() {
		return routeId;
	}
	
	public void setRouteID(UUID routeId) {
		this.routeId = routeId;
	}
}
