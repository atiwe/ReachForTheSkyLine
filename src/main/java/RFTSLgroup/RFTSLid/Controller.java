package RFTSLgroup.RFTSLid;

import com.datastax.driver.core.Session;

import Repository.EmployeeRepository;

public class Controller {
	private GuiEmployees ui;
	private EmployeeRepository employeeRepository;
	
	public Controller(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
		createUI();
	}
	
    void createUI() {
    	ui = new GuiEmployees();
    	ui.setController(this);
    	ui.createUI();
	}
    
    void addFlight(String t, String t2, String t3) {
    	
    }

}
