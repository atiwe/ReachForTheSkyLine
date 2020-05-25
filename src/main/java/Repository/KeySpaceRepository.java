package Repository;

import com.datastax.driver.core.Session;

public class KeySpaceRepository {

	private Session session;
	
	public KeySpaceRepository(Session session) {
		this.session = session;
	}
	
	public void createKeySpace(String keyspaceName, String replicationStrategy, int numberOfReplicas) {
		StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append(keyspaceName).append(" WITH replication = {")
				.append("'class':'").append(replicationStrategy).append("','replication_factor':").append(numberOfReplicas).append("};");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void useKeyspace(String keyspace) {
		session.execute("USE " + keyspace);
	}
	
	public void deleteKeySpace(String keyspaceName) {
		StringBuilder sb = new StringBuilder("DROP KEYSPACE ").append(keyspaceName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
