package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.ScheduledFlight;;

public class ScheduledFlightRepository {

	private static final String TABLE_NAME = "scheduledFlight";
	
	private Session session;
	
	public ScheduledFlightRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append("estimated_start text,").append("estimated_landing text,").append("flightTime text,")
				.append("pilot text,").append("route_id int);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableFlight(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertFlight(ScheduledFlight flight) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, estimated_start, estimated_landing, flightTime, pilot, route_id) ")
				.append("VALUES (").append(flight.getID()).append(", '").append(flight.getEstimatedStart()).append("', '").append(flight.getEstimatedLanding()).append("', '")
				.append(flight.getFlightTime()).append("', '").append(flight.getPilot()).append("', ").append(flight.getRouteID()).append(");");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public ScheduledFlight selectByStart(String start) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE estimated_start = '").append(start).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getInt("id"), r.getString("estimated_start"), r.getString("estimated_landing"), r.getString("flightTime"), r.getString("pilot"), r.getInt("route_id"));			
			flights.add(flight);
		}
		return flights.get(0);
	}
	
	public ScheduledFlight selectByEnd(String end) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE estimated_landing = '").append(end).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getInt("id"), r.getString("estimated_start"), r.getString("estimated_landing"), r.getString("flightTime"), r.getString("pilot"), r.getInt("route_id"));			
			flights.add(flight);
		}
		return flights.get(0);
	}
	
	public ScheduledFlight selectByRoute(int route) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE route_id = '").append(route).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getInt("id"), r.getString("estimated_start"), r.getString("estimated_landing"), r.getString("flightTime"), r.getString("pilot"), r.getInt("route_id"));			
			flights.add(flight);
		}
		return flights.get(0);
	}
	
	public List<ScheduledFlight> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getInt("id"), r.getString("estimated_start"), r.getString("estimated_landing"), r.getString("flightTime"), r.getString("pilot"), r.getInt("route_id"));
			
			flights.add(flight);
		}
		return flights;
	}
	
	public ScheduledFlight selectByHighestID() {
		StringBuilder sb = new StringBuilder("SELECT MAX('id') AS highest FROM ").append(TABLE_NAME).append(";");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getInt("id"), r.getString("estimated_start"), r.getString("estimated_landing"), r.getString("flightTime"), r.getString("pilot"), r.getInt("route_id"));			
			flights.add(flight);
		}
		return flights.get(0);
	}
	
	public void deleteFlightByStart(String start) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE estimated_start = '").append(start).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteFlightByEnd(String end) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE estimated_landing = '").append(end).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteFlightByID(int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE id = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteFlightByRoute(int route) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE route_id = '").append(route).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
