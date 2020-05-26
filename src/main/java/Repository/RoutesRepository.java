package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Route;;

public class RoutesRepository {

	private static final String TABLE_NAME = "route";
	
	private static final String TABLE_NAME_BY_DEPARTURE = TABLE_NAME + "ByDepartureCity";
	
	private static final String TABLE_NAME_BY_ARRIVAL = TABLE_NAME + "ByArrivalCity";
	
	private Session session;
	
	public RoutesRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id uuid PRIMARY KEY, ").append("departure_city text,").append("arrival_city text,").append("estimated_flight_duration text,")
				.append("price text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void createTableRouteByDeparture() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME_BY_DEPARTURE).append("(")
				.append("id uuid, ").append("departure_city text,").append("PRIMARY KEY (departure_city, id));");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void createTableRouteByArrival() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME_BY_ARRIVAL).append("(")
				.append("id uuid, ").append("arrival_city text,").append("PRIMARY KEY (arrival_city, id));");
		
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
	
	public void insertRouteByDeparture(Route route) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME_BY_DEPARTURE).append("(id, departure_city) ").append("VALUES (")
				.append(route.getID()).append(", '").append(route.getDepartureCity()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertRouteByArrival(Route route) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME_BY_ARRIVAL).append("(id, arrival_city) ").append("VALUES (")
				.append(route.getID()).append(", '").append(route.getArrivalCity()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertRouteBatchDepart(Route route) {
		StringBuilder sb = new StringBuilder("BEGIN BATCH ").append("INSERT INTO ").append(TABLE_NAME).append("(id, departure_city, arrival_city, estimated_flight_duration, price) ")
				.append("VALUES (").append(route.getID()).append(", '").append(route.getDepartureCity()).append("', '").append(route.getArrivalCity()).append("', '")
				.append(route.getFlightDuration()).append("', '").append(route.getPrice()).append("');").append("INSERT INTO ").append(TABLE_NAME_BY_DEPARTURE).append("(id, departure_city) ").append("VALUES (")
				.append(route.getID()).append(", '").append(route.getDepartureCity()).append("');").append("APPLY BATCH;");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertRouteBatchArrive(Route route) {
		StringBuilder sb = new StringBuilder("BEGIN BATCH ").append("INSERT INTO ").append(TABLE_NAME).append("(id, departure_city, arrival_city, estimated_flight_duration, price) ")
				.append("VALUES (").append(route.getID()).append(", '").append(route.getDepartureCity()).append("', '").append(route.getArrivalCity()).append("', '")
				.append(route.getFlightDuration()).append("', '").append(route.getPrice()).append("');").append("INSERT INTO ").append(TABLE_NAME_BY_ARRIVAL).append("(id, arrival_city) ").append("VALUES (")
				.append(route.getID()).append(", '").append(route.getArrivalCity()).append("');").append("APPLY BATCH;");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Route selectByDeparture(String depart) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_DEPARTURE).append(" WHERE departure_city = '").append(depart).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getUUID("id"), r.getString("departure_city"), null, null, null);
			
			routes.add(route);
		}
		return routes.get(0);
	}
	
	public Route selectByArrival(String arrive) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_ARRIVAL).append(" WHERE arrival_city = '").append(arrive).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getUUID("id"), null, r.getString("arrival_city"), null, null);
			
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
			Route route = new Route(r.getUUID("id"), r.getString("departure_city"), r.getString("arrival_city"), r.getString("estimated_flight_duration"), r.getString("price"));
			
			routes.add(route);
		}
		return routes;
	}
	
	public List<Route> selectAllRoutesByDeparture(){
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_DEPARTURE);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getUUID("id"), r.getString("departure_city"), null, null, null);
			
			routes.add(route);
		}
		return routes;
	}
	
	public List<Route> selectAllRoutesByArrival(){
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_ARRIVAL);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Route> routes = new ArrayList<Route>();
		
		for(Row r : rs) {
			Route route = new Route(r.getUUID("id"), null, r.getString("arrival_city"), null, null);
			
			routes.add(route);
		}
		return routes;
	}
	
	public void deleteRouteByDeparture(String depart) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME_BY_DEPARTURE).append(" WHERE departure_city = '").append(depart).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteRouteByArrival(String arrive) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME_BY_ARRIVAL).append(" WHERE arrival_city = '").append(arrive).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
