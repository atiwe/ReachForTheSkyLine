package RFTSLgroup.RFTSLid;


import java.awt.print.Book;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;

public class App 
{
	
    private Cluster cluster;
    
    private Session session;
    
    private static final String TABLE_NAME = "EMPLOYEES";
 
    public void connect(String node, Integer port) {
        Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();
 
        session = cluster.connect();
    }
 
    public Session getSession() {
        return this.session;
    }
 
    public void close() {
        session.close();
        cluster.close();
    }
    
/*    public void createKeySpace(String keySpaceName, String replicationStrategy, int replicationFactor)
    {
    	StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ").append(keySpaceName).append(" WITH replication = {")
    			.append("'class':'").append(replicationStrategy).append("','replication_factor':")
    			.append(replicationFactor).append("};");
    	
    	String query = sb.toString();
    	session.execute(query);
    }
    
    public void createTable()
    {
    	StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
    			.append(TABLE_NAME).append("(")
    			.append("id uuid PRIMARY KEY, ")
    			.append("title text,")
    			.append("subject text);");
    	
    	String query = sb.toString();
    	session.execute(query);
    }
    
    public void alterTableEmployee(String columnName, String columnType)
    {
    	StringBuilder sb = new StringBuilder("ALTER TABLE ")
    			.append(TABLE_NAME).append(" ADD ")
    			.append(columnName).append(" ")
    			.append(columnType).append(";");
    	
    	String query = sb.toString();
    	session.execute(query);
    }
    
    public void insertEmployeeByTitle()
    {
    	StringBuilder sb = new StringBuilder("INSERT INTO ")
    			.append(TABLE_NAME).append(" ADD ")
    			.append(columnName).append()
    			.append(columnType).append(";");
    	
    	String query = sb.toString();
    	session.execute(query);
    }
/*    private KeyspaceRepository schemaRepository;
    
    @Before
    public void connect()
    {
    	CassandraConnector client = new CassandraConnector();
    	client.connect("127.0.0.1", 9042);
    	this.session = client.getSession();
    	schemaRepository = new KeyspaceRepository(session);
    }*/
    
    public static void main( String[] args )
    {
    	App app = new App();
    	app.connect("127.0.0.1", 9042);
    }
}