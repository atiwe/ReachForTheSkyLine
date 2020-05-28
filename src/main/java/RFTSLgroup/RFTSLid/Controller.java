package RFTSLgroup.RFTSLid;

import java.util.UUID;

import Domain.Airplane;
import Domain.Employee;
import Domain.Pilot;
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
    
    public void addEmployee(String id, String name, String email, String telephone, String ssn, String empDate) {
    	UUID uuid = new UUID(234230, 0);
    	Employee employee = new Employee(uuid, name, email, telephone, ssn, empDate);
    	employeeRepository.insertEmployee(employee);
    }
    
    
    void addFlight(String id, String estimatedStart, String estimatedLanding, String flightTime, String pilot, String routeID) {
    	UUID uuid = new UUID(23456, 1);
    	UUID uuid2 = new UUID(2342345, 2);
    	ScheduledFlight scheduledFlight = new ScheduledFlight(uuid, estimatedStart, estimatedLanding, flightTime, pilot, uuid2);
    	scheduledFlightRepository.insertFlight(scheduledFlight);
    }
    
    public void addAircraft(String id, String model, String producer, String capacity, String flightHours) {
    	UUID uuid = new UUID(235235, 0);
    	Airplane airplane = new Airplane(uuid, model, producer, capacity, flightHours);
    }
    
    public void addPilot(String id, String name, String email, String telephone, String ssn, String empDate, String pilotLic) {
    	UUID uuid  = new UUID(236236, 0);
    	String weeklyFlightHours = null;
    	String lastFlight = null;
    	String nextFlight = null;
    	Pilot pilot = new Pilot(uuid, name, email, telephone, ssn, empDate, pilotLic, weeklyFlightHours, lastFlight, nextFlight);
    	pilotRepository.insertPilot(pilot);
    }

    public void editPilot(String id, String name, String weeklyHours, String lastFlight, String nextFlight) {
    	
    }
}
