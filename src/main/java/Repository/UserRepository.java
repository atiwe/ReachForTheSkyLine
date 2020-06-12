package Repository;

import com.datastax.driver.core.Session;

import Domain.Airplane;
import Domain.User;

public class UserRepository {
	
	private static final String TABLE_NAME = "airplane";
	
	private Session session;
	
	public UserRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("username text PRIMARY KEY, ").append("password text,").append("relatedID int,").append("type text;");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertUser(User user) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(username, password, relatedID, type")
				.append("VALUES ('").append(user.getUsername()).append("', '").append(user.getPassword()).append("', ").append(user.getRelatedID()).append(", '")
				.append(user.getType()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}

}
