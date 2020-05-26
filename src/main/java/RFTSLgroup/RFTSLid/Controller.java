package RFTSLgroup.RFTSLid;

import java.util.UUID;

import Domain.Airplane;
import Domain.Employee;
import Domain.Pilot;
import Repository.AirplaneRepository;
import Repository.CustomerRepository;
import Repository.EmployeeRepository;
import Repository.PilotRepository;

public class Controller {
	private GuiEmployees guiEmployee;
    private AirplaneRepository airplaneRepository;
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;
    private PilotRepository pilotRepository;
	private GuiAdmin guiAdmin;
	private HomePage homePage;
	
	public Controller(AirplaneRepository airplaneRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, PilotRepository pilotRepository) {
		this.airplaneRepository = airplaneRepository;
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
		this.pilotRepository = pilotRepository;
		createUI();
	}
	
    void createUI() {
    	homePage = new HomePage(this);
	}
    
    public void addEmployee(String id, String name, String email, String telephone, String ssn, String empDate) {
    	UUID uuid = new UUID(234234, 0);
    	Employee employee = new Employee(uuid, name, email, telephone, ssn, empDate);
    	employeeRepository.insertEmployee(employee);
    }
    
    void addFlight(String t, String t2, String t3) {
    	
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
}
