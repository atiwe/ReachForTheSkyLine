package RFTSLgroup.RFTSLid;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;

public class App 
{
	
    private Cluster cluster;
    
    private Session session;
 
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
    	App app = new App();
    	app.connect("127.0.0.1", 9042);
    }
}