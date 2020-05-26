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
	private PilotRepository pilotRepo;
	
	public Controller(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		createUI();
	}
	
    void createUI() {
//    	guiEmployee = new GuiEmployees();
//    	guiEmployee.setController(this);
//    	guiEmployee.createUI();
    	guiAdmin = new GuiAdmin();
    	guiAdmin.setController(this);
    	guiAdmin.createUI();
	}
    
//	this.id = ID;
//	this.name = Name;
//	this.email = Email;
//	this.telephone = Telephone;
//	this.personalNumber = PersonalNumber;
//	this.employmentDate = EmploymentDate;
    
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
