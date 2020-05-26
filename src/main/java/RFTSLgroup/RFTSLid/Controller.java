package RFTSLgroup.RFTSLid;

import java.util.UUID;

import Domain.Airplane;
import Domain.Employee;
import Domain.Pilot;
import Repository.EmployeeRepository;
import Repository.PilotRepository;

public class Controller {
	private GuiEmployees guiEmployee;
	private EmployeeRepository employeeRepository;
	private GuiAdmin guiAdmin;
<<<<<<< HEAD
	private HomePage homePage;
=======
	private PilotRepository pilotRepo;
>>>>>>> d9baf99f6516708481d2336197ce51ce86d3b110
	
	public Controller(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
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
    	pilotRepo.insertPilot(pilot);
    }

}
