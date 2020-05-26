package RFTSLgroup.RFTSLid;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;
import Repository.EmployeeRepository;
import Repository.KeySpaceRepository;

public class App 
{
	
    private Cluster cluster;
    private GuiEmployees ui;
    
    private static Session session;
    
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
    
    
    public static void main( String[] args )
    {
    	String keyspaceName = "ReachForTheSkyLine";
    	App app = new App();
    	app.connect("127.0.0.1", 9042);
    	KeySpaceRepository keyspace = new KeySpaceRepository(session);
    	keyspace.createKeySpace(keyspaceName, "SimpleStrategy", 1);
    	keyspace.useKeyspace(keyspaceName);
    	EmployeeRepository employee = new EmployeeRepository(session);
    	employee.createTable();
    	Controller controller = new Controller(employee);
    }
}