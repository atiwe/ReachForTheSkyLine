package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import Domain.User;

public class UserRepository {
	
	private static final String TABLE_NAME = "user";
	
	private Session session;
	
	public UserRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("username text PRIMARY KEY, ").append("password text,").append("relatedID int,").append("type text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableUsers(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertUser(User user) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(username, password, relatedID, type) ")
				.append("VALUES ('").append(user.getUsername()).append("', '").append(user.getPassword()).append("', ").append(user.getRelatedID()).append(", '")
				.append(user.getType()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public User selectByUser(String username) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE username = '").append(username).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<User> users = new ArrayList<User>();
		
		for(Row r : rs) {
			User user = new User(r.getString("username"), r.getString("password"), r.getInt("relatedID"), r.getString("type"));
			
			users.add(user);
		}
		return users.get(0);
	}
	
	public User selectByUserAndPassword(String username, String password) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE username = '").append(username).append("' AND password = ").append(password).append(";");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<User> users = new ArrayList<User>();
		
		for(Row r : rs) {
			User user = new User(r.getString("username"), r.getString("password"), r.getInt("relatedID"), r.getString("type"));
			
			users.add(user);
		}
		return users.get(0);
	}
	
	public List<User> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<User> users = new ArrayList<User>();
		
		for(Row r : rs) {
			User user = new User(r.getString("username"), r.getString("password"), r.getInt("relatedID"), r.getString("type"));
			
			users.add(user);
		}
		return users;
	}
	
	public void deleteUserByName(String name) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE username = '").append(name).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}

}
