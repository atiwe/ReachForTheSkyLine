package RFTSLgroup.RFTSLid;

import java.util.List;
import Domain.Airplane;
import Domain.Campaign;
import Domain.Employee;
import Domain.Pilot;
import Domain.Route;
import Domain.ScheduledFlight;
import Repository.AirplaneRepository;
import Repository.CampaignRepository;
import Repository.CustomerRepository;
import Repository.EmployeeRepository;
import Repository.FlightRequestRepository;
import Repository.PilotRepository;
import Repository.RoutesRepository;
import Repository.ScheduledFlightRepository;

public class Controller {
	private GuiEmployees guiEmployee;
    private AirplaneRepository airplaneRepository;
    private CampaignRepository campaignRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private FlightRequestRepository flightRequestRepository;
    private PilotRepository pilotRepository;
    private RoutesRepository routesRepository;
    private ScheduledFlightRepository scheduledFlightRepository;
	private GuiAdmin guiAdmin;
	private HomePage homePage;
	
	public Controller(AirplaneRepository airplaneRepository, CampaignRepository campaignRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, FlightRequestRepository flightRequestRepository, PilotRepository pilotRepository, RoutesRepository routesRepository, ScheduledFlightRepository scheduledFlightRepository) {
		this.airplaneRepository = airplaneRepository;
		this.campaignRepository = campaignRepository;
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
		this.flightRequestRepository = flightRequestRepository;
		this.pilotRepository = pilotRepository;
		this.routesRepository = routesRepository;
		this.scheduledFlightRepository = scheduledFlightRepository;
		createUI();
	}
	
    void createUI() {
    	homePage = new HomePage(this);
	}
    
    public void addEmployee(String name, String email, String telephone, String ssn, String empDate) {
    	List<Employee> employees = getEmployees();
    	int id;
    	if((getEmployees().size())<1){
    		id = 1;
    	}else {
    		    	id = (employees.get(employees.size()-1).getID())+1;
    	}

    	Employee employee = new Employee(id, name, email, telephone, ssn, empDate);
    	employeeRepository.insertEmployee(employee);
    }
    
    
    void addFlight(String estimatedStart, String estimatedLanding, String flightTime, String pilot, String routeID) {
    	int id1 = 101;
    	int id2 = 102;
    	ScheduledFlight scheduledFlight = new ScheduledFlight(id1, estimatedStart, estimatedLanding, flightTime, pilot, id2);
    	scheduledFlightRepository.insertFlight(scheduledFlight);
    }
    
    public void addAircraft(String model, String producer, String capacity, String flightHours) {
    	int id1 = 103;
    	Airplane airplane = new Airplane(id1, model, producer, capacity, flightHours);
    	airplaneRepository.insertAirplane(airplane);
    }
    
    public void addPilot(String name, String email, String telephone, String ssn, String empDate, String pilotLic) {
    	int id1  = 104;
    	String weeklyFlightHours = null;
    	String lastFlight = null;
    	String nextFlight = null;
    	Pilot pilot = new Pilot(id1, name, email, telephone, ssn, empDate, pilotLic, weeklyFlightHours, lastFlight, nextFlight);
    	pilotRepository.insertPilot(pilot);
    }

    public void addCampaign(String startDate, String endDate, String reduction, String discountCode) {
    	int id1 = 105;
    	Campaign campaign = new Campaign(id1, startDate, endDate, reduction, discountCode);
    	campaignRepository.insertCampaign(campaign);
    }
    
    public void addRoute(String deptCity, String arrCity, String flightDuration, String price) {
    	int id1 = 106;
    	Route route = new Route(id1, deptCity, arrCity, flightDuration, price);
    	routesRepository.insertRoute(route);
    }
    
    public void editPilot(String name, String weeklyHours, String lastFlight, String nextFlight) {
    	
    }
    
    public List<Pilot> getPilots() {
    	return pilotRepository.selectAll();
    }
    
    public List<Airplane> getAircraft() {
    	return airplaneRepository.selectAll();
    }
    
    public List<Employee> getEmployees() {
    	return employeeRepository.selectAll();
    }
    
    public List<Campaign> getDiscount() {
    	return campaignRepository.selectAll();
    }
    
    public List<ScheduledFlight> getScheduledFlights(){
    	return scheduledFlightRepository.selectAll();
    }
    
    public List<Route> getFlightLines(){
    	return routesRepository.selectAll();
    }
    
    
    
}
