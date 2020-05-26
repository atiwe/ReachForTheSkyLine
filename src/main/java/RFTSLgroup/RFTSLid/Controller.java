package RFTSLgroup.RFTSLid;

import java.util.UUID;

import Domain.Employee;
import Repository.EmployeeRepository;

public class Controller {
	private GuiEmployees guiEmployee;
	private EmployeeRepository employeeRepository;
	private GuiAdmin guiAdmin;
	private HomePage homePage;
	
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

}
