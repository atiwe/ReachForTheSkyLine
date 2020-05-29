package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Customer;

public class CustomerRepository {

	private static final String TABLE_NAME = "customer";
	
	private static final String NAME = "name";
	
	private static final String EMAIL = "email";
	
	private static final String TELEPHONE = "telephone";
	
	private static final String SOCIAL_SECURITY = "social_security_number";
	
	private static final String BANK = "bank";
	
	private static final String DISCOUNT_CODE = "discount_code";
	
	private static final String SCHEDULED_FLIGHT = "scheduled_flight";
	
	private Session session;
	
	public CustomerRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append(NAME + " text,").append(EMAIL + " text,").append(TELEPHONE + " text,")
				.append(SOCIAL_SECURITY + " text,").append(BANK + " text,").append(DISCOUNT_CODE + " text,").append(SCHEDULED_FLIGHT + " int);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableCustomers(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertCustomer(Customer customer) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, name, email, telephone, social_security_number, bank, discount_code, scheduled_flight) ")
				.append("VALUES (").append(customer.getID()).append(", '").append(customer.getName()).append("', '").append(customer.getEmail()).append("', '")
				.append(customer.getTelephone()).append("', '").append(customer.getPersonalNumber()).append("', '").append(customer.getBank()).append("', '")
				.append(customer.getDiscountCode()).append("', '").append(customer.getScheduledFlightID()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Customer selectByName(String name) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(Row r : rs) {
			Customer customer = new Customer(r.getInt("id"), r.getString("name"), r.getString("email"), r.getString("telephone"), r.getString("social_security_number"), r.getString("bank"), r.getString("discount_code"), r.getInt("scheduled_flight"));
			
			customers.add(customer);
		}
		return customers.get(0);
	}
	
	public List<Customer> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Customer> employees = new ArrayList<Customer>();
		
		for(Row r : rs) {
			Customer customer = new Customer(r.getInt("id"), r.getString("name"), r.getString("email"), r.getString("telephone"), r.getString("social_security_number"), r.getString("bank"), r.getString("discount_code"), r.getInt("scheduled_flight"));
			
			employees.add(customer);
		}
		return employees;
	}
	
	public void deleteCustomerByID(int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE id = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteCustomerByName(String name) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteCustomerBySecurityNumber(String number) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE social_security_number = '").append(number).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteCustomerBySecurityNumberAndFlightID(String number, int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE social_security_number = '").append(number).append("' AND scheduled_flight = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
