package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.FlightRequest;;

public class FlightRequestRepository {

	private static final String TABLE_NAME = "request";
	
	private Session session;
	
	public FlightRequestRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append("customer_id int,").append("departure_city text,").append("arrival_city text,")
				.append("start_time text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableRequest(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertRequest(FlightRequest request) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, customer_id, departure_city, arrival_city, start_time) ")
				.append("VALUES (").append(request.getID()).append(", '").append(request.getCustomerID()).append("', '").append(request.getDepartureCity()).append("', '")
				.append(request.getArrivalCity()).append("', '").append(request.getStartTime()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public FlightRequest selectByCustomer(int customer) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE customer_id = '").append(customer).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<FlightRequest> requests = new ArrayList<FlightRequest>();
		
		for(Row r : rs) {
			FlightRequest request = new FlightRequest(r.getInt("id"), r.getInt("customer_id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("start_time"));
			
			requests.add(request);
		}
		return requests.get(0);
	}
	
	public List<FlightRequest> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<FlightRequest> requests = new ArrayList<FlightRequest>();
		
		for(Row r : rs) {
			FlightRequest request = new FlightRequest(r.getInt("id"), r.getInt("customer_id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("start_time"));
			
			requests.add(request);
		}
		return requests;
	}
	
	public void deleteRequestByID(int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE id = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteRequestByCustomer(int customer) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE customer_id = ").append(customer).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
