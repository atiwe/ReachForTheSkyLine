package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.ScheduledFlight;;

public class ScheduledFlightRepository {

	private static final String TABLE_NAME = "scheduledFlight";
	
	private static final String TABLE_NAME_BY_START = TABLE_NAME + "ByStartTime";
	
//	private static final String TABLE_NAME_BY_LANDING = TABLE_NAME + "ByLandingTime";
	
	private Session session;
	
	public ScheduledFlightRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id uuid PRIMARY KEY, ").append("estimated_start text,").append("estimated_landing text,").append("flightTime text,")
				.append("pilot text,").append("route_id uuid);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void createTableFlightByStart() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME_BY_START).append("(")
				.append("id uuid, ").append("estimated_start text,").append("PRIMARY KEY (estimated_start, id));");
		
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
	
	public void insertFlightByStart(ScheduledFlight flight) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME_BY_START).append("(id, estimated_start) ").append("VALUES (")
				.append(flight.getID()).append(", '").append(flight.getEstimatedStart()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertFlightBatch(ScheduledFlight flight) {
		StringBuilder sb = new StringBuilder("BEGIN BATCH ").append("INSERT INTO ").append(TABLE_NAME).append("(id, estimated_start, estimated_landing, flightTime, pilot, route_id) ")
				.append("VALUES (").append(flight.getID()).append(", '").append(flight.getEstimatedStart()).append("', '").append(flight.getEstimatedLanding()).append("', '")
				.append(flight.getFlightTime()).append("', '").append(flight.getPilot()).append("', '").append(flight.getRouteID()).append("');")
				.append("INSERT INTO ").append(TABLE_NAME_BY_START).append("(id, estimated_start) ").append("VALUES (")
				.append(flight.getID()).append(", '").append(flight.getEstimatedStart()).append("');").append("APPLY BATCH;");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public ScheduledFlight selectByStart(String start) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_START).append(" WHERE estimated_start = '").append(start).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getUUID("id"), r.getString("estimated_start"), null, null, null, null);
			
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
			ScheduledFlight flight = new ScheduledFlight(r.getUUID("id"), r.getString("estimated_start"), r.getString("estimated_landing"), r.getString("flightTime"), r.getString("pilot"), r.getUUID("route_id"));
			
			flights.add(flight);
		}
		return flights;
	}
	
	public List<ScheduledFlight> selectAllFlightsByStart(){
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_START);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<ScheduledFlight> flights = new ArrayList<ScheduledFlight>();
		
		for(Row r : rs) {
			ScheduledFlight flight = new ScheduledFlight(r.getUUID("id"), r.getString("estimated_start"), null, null, null, null);
			
			flights.add(flight);
		}
		return flights;
	}
	
	public void deleteFlightByStart(String start) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME_BY_START).append(" WHERE estimated_start = '").append(start).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
