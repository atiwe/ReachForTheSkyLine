package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Route;;

public class RoutesRepository {

	private static final String TABLE_NAME = "route";
	
	private Session session;
	
	public RoutesRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append("departure_city text,").append("arrival_city text,").append("estimated_flight_duration text,")
				.append("price text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableRoutes(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertRoute(Route route) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, departure_city, arrival_city, estimated_flight_duration, price) ")
				.append("VALUES (").append(route.getID()).append(", '").append(route.getDepartureCity()).append("', '").append(route.getArrivalCity()).append("', '")
				.append(route.getFlightDuration()).append("', '").append(route.getPrice()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Route selectByID(int id) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE id = ").append(id).append(";");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getInt("id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("estimated_flight_duration"), r.getString("price"));
			
			routes.add(route);
		}
		if(routes.size()<1) {
			return null;
		}
		return routes.get(0);
	}
	
	public Route selectByDeparture(String depart) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE departure_city = '").append(depart).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getInt("id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("estimated_flight_duration"), r.getString("price"));
			
			routes.add(route);
		}
		return routes.get(0);
	}
	
	public Route selectByArrival(String arrive) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE arrival_city = '").append(arrive).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getInt("id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("estimated_flight_duration"), r.getString("price"));
			
			routes.add(route);
		}
		return routes.get(0);
	}
	
	public List<Route> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getInt("id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("estimated_flight_duration"), r.getString("price"));
			
			routes.add(route);
		}
		return routes;
	}
	
	public void deleteRouteByDeparture(String depart) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE departure_city = '").append(depart).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteRouteByID(int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE id = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteRouteByArrival(String arrive) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE arrival_city = '").append(arrive).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
