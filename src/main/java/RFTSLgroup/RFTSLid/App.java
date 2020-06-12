package RFTSLgroup.RFTSLid;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Session;

import Repository.AirplaneRepository;
import Repository.CampaignRepository;
import Repository.CustomerRepository;
import Repository.EmployeeRepository;
import Repository.FlightRequestRepository;
import Repository.KeySpaceRepository;
import Repository.PilotRepository;
import Repository.RoutesRepository;
import Repository.ScheduledFlightRepository;
import Repository.UserRepository;

public class App 
{
	
    private Cluster cluster;
    private GuiEmployees ui;
    private KeySpaceRepository keySpaceRepository;
    private AirplaneRepository airplaneRepository;
    private CustomerRepository customerRepository;
    private CampaignRepository campaignRepository; //
    private EmployeeRepository employeeRepository;
    private FlightRequestRepository flightRequestRepository; //
    private PilotRepository pilotRepository;
    private RoutesRepository routesRepository;
    private ScheduledFlightRepository scheduledFlightRepository;
    private UserRepository userRepository;
    
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
    
    private void startDatabase() {
    	String keyspaceName = "ReachForTheSkyLine";
    	keySpaceRepository = new KeySpaceRepository(session);
    	keySpaceRepository.createKeySpace(keyspaceName, "SimpleStrategy", 1);
    	keySpaceRepository.useKeyspace(keyspaceName);
    	airplaneRepository = new AirplaneRepository(session);
    	airplaneRepository.createTable();
    	campaignRepository = new CampaignRepository(session);
    	campaignRepository.createTable();
    	customerRepository = new CustomerRepository(session);
    	customerRepository.createTable();
    	employeeRepository = new EmployeeRepository(session);
    	employeeRepository.createTable();
    	flightRequestRepository = new FlightRequestRepository(session);
    	flightRequestRepository.createTable();
    	pilotRepository = new PilotRepository(session);
    	pilotRepository.createTable();
    	
        routesRepository = new RoutesRepository(session);
        routesRepository.createTable();
        scheduledFlightRepository = new ScheduledFlightRepository(session);
        scheduledFlightRepository.createTable();
        userRepository = new UserRepository(session);
        userRepository.createTable();
    	
    	
    	new Controller(airplaneRepository, campaignRepository, customerRepository, employeeRepository, flightRequestRepository, pilotRepository, routesRepository, scheduledFlightRepository, userRepository);
    }
    
    
    public static void main( String[] args )
    {

    	App app = new App();
    	app.connect("127.0.0.1", 9042);
    	app.startDatabase();
    }
}