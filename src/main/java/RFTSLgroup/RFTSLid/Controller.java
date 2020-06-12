package RFTSLgroup.RFTSLid;

import java.util.List;
import Domain.Airplane;
import Domain.Campaign;
import Domain.Customer;
import Domain.Employee;
import Domain.Pilot;
import Domain.Route;
import Domain.ScheduledFlight;
import Domain.User;
import Repository.AirplaneRepository;
import Repository.CampaignRepository;
import Repository.CustomerRepository;
import Repository.EmployeeRepository;
import Repository.FlightRequestRepository;
import Repository.PilotRepository;
import Repository.RoutesRepository;
import Repository.ScheduledFlightRepository;
import Repository.UserRepository;

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
    private UserRepository userRepository;
	private GuiAdmin guiAdmin;
	private HomePage homePage;
	private GuiCustomers guiCustomers;
	
	public Controller(AirplaneRepository airplaneRepository, CampaignRepository campaignRepository, CustomerRepository customerRepository, EmployeeRepository employeeRepository, FlightRequestRepository flightRequestRepository, PilotRepository pilotRepository, RoutesRepository routesRepository, ScheduledFlightRepository scheduledFlightRepository, UserRepository userRepository) {
		this.airplaneRepository = airplaneRepository;
		this.campaignRepository = campaignRepository;
		this.customerRepository = customerRepository;
		this.employeeRepository = employeeRepository;
		this.flightRequestRepository = flightRequestRepository;
		this.pilotRepository = pilotRepository;
		this.routesRepository = routesRepository;
		this.scheduledFlightRepository = scheduledFlightRepository;
		this.userRepository = userRepository;
		createUI();
	}
	
    void createUI() {
    	homePage = new HomePage(this);
	}
    
    void setGuiCustomer(GuiCustomers guiCustomers) {
    	this.guiCustomers = guiCustomers;
    }
    
    //TODO Fixa så metoderna alltid får det högsta ID:t, förtillfället tar den sista ID:t + 1, men den sista
    // är inte alltid högst
    public void addEmployee(String name, String email, String telephone, String ssn, String empDate, String username, String password) {
    	List<Employee> employees = getEmployees();
    	int id = 0;
    	for(Employee employee : employees)
    	{
    		if(employee.getID() > id)
    			id = employee.getID();
    	}
    	if(id < 1){
    		id = 1;
    	}else {
    		id = id + 1;
    	}
    	Employee employee = new Employee(id, name, email, telephone, ssn, empDate);
    	employeeRepository.insertEmployee(employee);
    	
    	String type = "Employee";
    	User user = new User(username, password, id, type);
    	userRepository.insertUser(user);
    }
    
    
    void addFlight(String estimatedStart, String estimatedLanding, String flightTime, String pilot, int routeID) {
    	List<ScheduledFlight> flights = getScheduledFlights();
    	int id = 0;
    	for(ScheduledFlight flight : flights)
    	{
    		if(flight.getID() > id)
    			id = flight.getID();
    	}
    	if(id<1){
    		id = 1;
    	}else {
    		id = id + 1;
    	}
    	ScheduledFlight scheduledFlight = new ScheduledFlight(id, estimatedStart, estimatedLanding, flightTime, pilot, routeID);
    	scheduledFlightRepository.insertFlight(scheduledFlight);
    }
    
    public void addAircraft(String model, String producer, String capacity, String flightHours) {
    	List<Airplane> planes = getAircrafts();
    	int id = 0;
    	for(Airplane plane : planes)
    	{
    		if(plane.getID() > id)
    			id = plane.getID();
    	}
    	if(id < 1){
    		id = 1;
    	}else {
    	id = id + 1;
    	}
    	Airplane airplane = new Airplane(id, model, producer, capacity, flightHours);
    	airplaneRepository.insertAirplane(airplane);
    }
    
    public void addPilot(String name, String email, String telephone, String ssn, String empDate, String pilotLic) {
    	List<Pilot> pilots = getPilots();
    	int id = 0;
    	for(Pilot pilot : pilots)
    	{
    		if(pilot.getID() > id)
    			id = pilot.getID();
    	}
    	if(id < 1){
    		id = 1;
    	}else {
    	id = id + 1;
    	}
    	String weeklyFlightHours = null;
    	String lastFlight = null;
    	String nextFlight = null;
    	Pilot pilot = new Pilot(id, name, email, telephone, ssn, empDate, pilotLic, weeklyFlightHours, lastFlight, nextFlight);
    	pilotRepository.insertPilot(pilot);
    }

    public void addCampaign(String startDate, String endDate, String reduction, String discountCode) {
    	List<Campaign> campaigns = getDiscounts();
    	int id = 0;
    	for(Campaign campaign : campaigns)
    	{
    		if(campaign.getID() > id)
    			id = campaign.getID();
    	}
    	if(id < 1){
    		id = 1;
    	}else {
    		id = id + 1;
    	}
    	Campaign campaign = new Campaign(id, startDate, endDate, reduction, discountCode);
    	campaignRepository.insertCampaign(campaign);
    }
    
    public void addRoute(String deptCity, String arrCity, String flightDuration, String price) {
    	List<Route> routes = getFlightLines();
    	int id = 0;
    	for(Route route : routes)
    	{
    		if(route.getID() > id)
    			id = route.getID();
    	}
    	if(id < 1){
    		id = 1;
    	}else {
    		id++;
    	}
    	Route route = new Route(id, deptCity, arrCity, flightDuration, price);
    	routesRepository.insertRoute(route);
    }
    
    public void bookFlight(String name, String email, String phone, String personalNumber, String bank, String discountCode, int scheduledFlightID) {
    	List<Customer> customers = getCustomers();
    	int id = 0;
    	for(Customer customer : customers)
    	{
    		if(customer.getID() > id)
    			id = customer.getID();
    	}
    	if(id < 1){
    		id = 1;
    	}else {
    		id = id + 1;
    	}
    	Customer customer = new Customer(id, name, email, phone, personalNumber, bank, discountCode, scheduledFlightID);
    	customerRepository.insertCustomer(customer);
    }
    
    public void cancelFlight(int customerID) {
    	customerRepository.deleteCustomerByID(customerID);
    }
    
    
    public void editEmployee(int id, String name, String email, String telephone, String ssn, String empDate) {
    	Employee employee = new Employee(id, name, email, telephone, ssn, empDate);
    	employeeRepository.insertEmployee(employee);
    }
    
    public void editAircraft(int id, String model, String producer, String capacity, String flightHours) {
    	Airplane airplane = new Airplane(id, model, producer, capacity, flightHours);
    	airplaneRepository.insertAirplane(airplane);
    }
    
   public void editPilot(int id, String name, String email, String telephone, String ssn, String empDate, String pilotLic, String weeklyHours, String lastFlight, String nextFlight) {
    	
    	Pilot pilot = new Pilot(id, name, email, telephone, ssn, empDate, pilotLic, weeklyHours, lastFlight, nextFlight );
    	pilotRepository.insertPilot(pilot);
    }

    
   public void editScheduledFlight(int id, String ETD, String ETA, String flightTime, String pilot, int routeID) {
   	
   	ScheduledFlight scheduledFlight = new ScheduledFlight(id, ETD, ETA, flightTime, pilot, routeID);
   	scheduledFlightRepository.insertFlight(scheduledFlight);
   }
   
   public void editCampaign(int id, String start, String end, String reduction, String code) {
	   Campaign campaign = new Campaign(id, start, end, reduction, code);
	   campaignRepository.insertCampaign(campaign);
   }

    
    public void removeAircraft(int planeID) {
    	airplaneRepository.deleteAirplaneByID(planeID);
    }
    
    public void removePilot(int pilotID) {
    	
    	pilotRepository.deletePilotByID(pilotID);
    }
    
    public void removeFlight(int flightID) {
    	scheduledFlightRepository.deleteFlightByID(flightID);
    }
    
    public void removeEmployee(int employeeID) {
    	employeeRepository.deleteEmployeeByID(employeeID);
    }

    public void removeCampaign(int campaignID) {
    	campaignRepository.deleteCampaignByID(campaignID);
    }
    public void removeRoute(int flightID) {
    	routesRepository.deleteRouteByID(flightID);
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
    
    public List<Campaign> getDiscounts() {
    	return campaignRepository.selectAll();
    }
    
    public List<ScheduledFlight> getScheduledFlights(){
    	return scheduledFlightRepository.selectAll();
    }
    
    public List<Route> getFlightLines(){
    	return routesRepository.selectAll();
    }
    
    public Route getRouteById(int id) {
    	return routesRepository.selectByID(id);
    }
    
    public List<Customer> getCustomers(){
    	return customerRepository.selectAll();
    }
    
}
