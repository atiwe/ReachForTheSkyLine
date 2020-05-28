package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Pilot;;

public class PilotRepository {

	private static final String TABLE_NAME = "pilots";
	
	private Session session;
	
	public PilotRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append("name text,").append("email text,").append("telephone text,")
				.append("social_security_number text,").append("employment_date text,").append("pilot_license text,")
				.append("weekly_flight_hours text,").append("last_flight text,").append("next_flight text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTablePilots(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertPilot(Pilot pilot) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, name, email, telephone, social_security_number, employment_date, pilot_license, weekly_flight_hours, last_flight, next_flight) ")
				.append("VALUES (").append(pilot.getID()).append(", '").append(pilot.getName()).append("', '").append(pilot.getEmail()).append("', '")
				.append(pilot.getTelephone()).append("', '").append(pilot.getPersonalNumber()).append("', '").append(pilot.getEmploymentDate()).append("', '").append(pilot.getPilotLicense()).append("', '")
				.append(pilot.getWeeklyFlightHours()).append("', '").append(pilot.getLastFlight()).append("', '").append(pilot.getNextFlight())
				.append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Pilot selectByName(String name) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Pilot> pilots = new ArrayList<Pilot>();
		
		for(Row r : rs) {
			Pilot pilot = new Pilot(r.getInt("id"), r.getString("name"), r.getString("email"), r.getString("telephone"), r.getString("social_security_number"),
					r.getString("employment_date"), r.getString("pilot_license"), r.getString("weekly_flight_hours"), r.getString("last_flight"), r.getString("next_flight"));
			
			pilots.add(pilot);
		}
		return pilots.get(0);
	}
	
	public List<Pilot> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Pilot> pilots = new ArrayList<Pilot>();
		
		for(Row r : rs) {
			Pilot pilot = new Pilot(r.getInt("id"), r.getString("name"), r.getString("email"), r.getString("telephone"), r.getString("social_security_number"),
					r.getString("employment_date"), r.getString("pilot_license"), r.getString("weekly_flight_hours"), r.getString("last_flight"), r.getString("next_flight"));
			
			pilots.add(pilot);
		}
		return pilots;
	}
	
	public void deletePilotByName(String name) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
