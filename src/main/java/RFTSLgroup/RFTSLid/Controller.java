package RFTSLgroup.RFTSLid;

import java.util.List;
import Domain.Airplane;
import Domain.Campaign;
import Domain.Customer;
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
    
    //TODO Fixa så metoderna alltid får det högsta ID:t, förtillfället tar den sista ID:t + 1, men den sista
    // är inte alltid högst
    public void addEmployee(String name, String email, String telephone, String ssn, String empDate) {
    	//List<Employee> employees = getEmployees();
    	Employee employeeHigh = highestIDEmployee();
    	int id = employeeHigh.getID();
    	if(id < 1){
    		id = 1;
    	}else {
    		id = id + 1;
    	}
    	Employee employee = new Employee(id, name, email, telephone, ssn, empDate);
    	employeeRepository.insertEmployee(employee);
    }
    
    
    void addFlight(String estimatedStart, String estimatedLanding, String flightTime, String pilot, int routeID) {
    	List<ScheduledFlight> flights = getScheduledFlights();
    	int id;
    	if((flights.size())<1){
    		id = 1;
    	}else {
    		id = (flights.get(flights.size()-1).getID())+1;
    	}
    	ScheduledFlight scheduledFlight = new ScheduledFlight(id, estimatedStart, estimatedLanding, flightTime, pilot, routeID);
    	scheduledFlightRepository.insertFlight(scheduledFlight);
    }
    
    public void addAircraft(String model, String producer, String capacity, String flightHours) {
    	List<Airplane> planes = getAircrafts();
    	int id;
    	if((planes.size())<1){
    		id = 1;
    	}else {
    		    	id = (planes.get(planes.size()-1).getID())+1;
    	}
    	Airplane airplane = new Airplane(id, model, producer, capacity, flightHours);
    	airplaneRepository.insertAirplane(airplane);
    }
    
    public void addPilot(String name, String email, String telephone, String ssn, String empDate, String pilotLic) {
    	List<Pilot> pilots = getPilots();
    	int id;
    	if((pilots.size())<1){
    		id = 1;
    	}else {
    		    	id = (pilots.get(pilots.size()-1).getID())+1;
    	}
    	String weeklyFlightHours = null;
    	String lastFlight = null;
    	String nextFlight = null;
    	Pilot pilot = new Pilot(id, name, email, telephone, ssn, empDate, pilotLic, weeklyFlightHours, lastFlight, nextFlight);
    	pilotRepository.insertPilot(pilot);
    }

    public void addCampaign(String startDate, String endDate, String reduction, String discountCode) {
    	List<Campaign> campaigns = getDiscounts();
    	int id;
    	if((campaigns.size())<1){
    		id = 1;
    	}else {
    		id = (campaigns.get(campaigns.size()-1).getID())+1;
    	}
    	Campaign campaign = new Campaign(id, startDate, endDate, reduction, discountCode);
    	campaignRepository.insertCampaign(campaign);
    }
    
    public void addRoute(String deptCity, String arrCity, String flightDuration, String price) {
    	List<Route> routes = getFlightLines();
    	int id;
    	if((routes.size())<1){
    		id = 1;
    	}else {
    		id = (routes.get(routes.size()-1).getID())+1;
    	}
    	Route route = new Route(id, deptCity, arrCity, flightDuration, price);
    	routesRepository.insertRoute(route);
    }
    
    public void bookFlight(String name, String email, String phone, String personalNumber, String bank, String discountCode, int scheduledFlightID) {
    	List<Customer> customers = getCustomers();
    	int id;
    	if((customers.size())<1){
    		id = 1;
    	}else {
    		id = (customers.get(customers.size()-1).getID())+1;
    	}
    	Customer customer = new Customer(id, name, email, phone, personalNumber, bank, discountCode, scheduledFlightID);
    	customerRepository.insertCustomer(customer);
    }
    
    public void cancelFlight(String personalNumber, int flightID) {
    	//TODO Fixa en metod i customerRepository som gör att man kan ta bort customer med hjälp av
    	//personnummer och flightID, ifall en kund har bokat flera flights
    	customerRepository.deleteCustomerBySecurityNumberAndFlightID(personalNumber, flightID);
    }
    
    public void editPilot(int id,String name, String email, String telephone, String ssn, String empDate, String pilotLic, String weeklyHours, String lastFlight, String nextFlight) {
    	
    	Pilot pilot = new Pilot(id, name, email, telephone, ssn, empDate, pilotLic, weeklyHours, lastFlight, nextFlight );
    	pilotRepository.insertPilot(pilot);
    }
    
    public void removeAircraft(int planeID) {
    	airplaneRepository.deleteAirplaneByID(planeID);
    }
    
    public void removePilot(int pilotID) {
    	pilotRepository.deletePilotByID(pilotID);
    }
    
    public void removeEmployee(int employeeID) {
    	employeeRepository.deleteEmployeeByID(employeeID);
    }
    
    public List<Pilot> getPilots() {
    	return pilotRepository.selectAll();
    }
    
    public List<Airplane> getAircrafts() {
    	return airplaneRepository.selectAll();
    }
    
    public List<Employee> getEmployees() {
    	return employeeRepository.selectAll();
    	
    }
    
    public Employee highestIDEmployee() {
    	return employeeRepository.selectByHighestID();
    }
    
    public List<Campaign> getDiscounts() {
    	return campaignRepository.selectAll();
    }
    
    public List<ScheduledFlight> getScheduledFlights(){
    	return scheduledFlightRepository.selectAll();
    }
    
    public List<Route> getFlightLines(){
    	return routesRepository.selectAll();
    }
    
    public List<Customer> getCustomers(){
    	return customerRepository.selectAll();
    }
    
}
