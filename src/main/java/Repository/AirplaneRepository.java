package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Airplane;

public class AirplaneRepository {

	private static final String TABLE_NAME = "airplane";
	
	private Session session;
	
	public AirplaneRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append("model text,").append("producer text,").append("capacity text,")
				.append("flight_hours text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableAirplanes(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertAirplane(Airplane airplane) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, model, producer, capacity, flight_hours) ")
				.append("VALUES (").append(airplane.getID()).append(", '").append(airplane.getModel()).append("', '").append(airplane.getProducer()).append("', '")
				.append(airplane.getCapacity()).append("', '").append(airplane.getFlightHours()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Airplane selectByModel(String model) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE model = '").append(model).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Airplane> airplanes = new ArrayList<Airplane>();
		
		for(Row r : rs) {
			Airplane airplane = new Airplane(r.getInt("id"), r.getString("model"), r.getString("producer"), r.getString("capacity"), r.getString("flight_hours"));
			
			airplanes.add(airplane);
		}
		return airplanes.get(0);
	}
	
	public List<Airplane> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Airplane> airplanes = new ArrayList<Airplane>();
		
		for(Row r : rs) {
			Airplane airplane = new Airplane(r.getInt("id"), r.getString("model"), r.getString("producer"), r.getString("capacity"), r.getString("flight_hours"));
			
			airplanes.add(airplane);
		}
		return airplanes;
	}
	
	public void deleteAirplaneByID(int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE id = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteAirplaneByModel(String model) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE model = '").append(model).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
