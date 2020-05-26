package Domain;

import java.util.UUID;

public class Airplane {

	private UUID id;
	
	private String model;
	
	private String producer;
	
	private String capacity;
	
	private String flightHours;
	
	Airplane(){
	}
	
	public Airplane(UUID ID, String Model, String Producer, String Capacity, String FlightHours) {
		this.id = ID;
		this.model = Model;
		this.producer = Producer;
		this.capacity = Capacity;
		this.flightHours = FlightHours;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setID(UUID id) {
		this.id = id;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getProducer() {
		return producer;
	}
	
	public void setProducer(String producer) {
		this.producer = producer;
	}
	
	public String getCapacity() {
		return capacity;
	}
	
	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}
	
	public String getFlightHours() {
		return flightHours;
	}
	
	public void setFlightHours(String flightHours) {
		this.flightHours = flightHours;
	}
}
