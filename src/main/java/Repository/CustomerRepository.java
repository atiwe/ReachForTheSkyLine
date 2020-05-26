package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Customer;

public class CustomerRepository {

	private static final String TABLE_NAME = "customer";
	
	private static final String TABLE_NAME_BY_NAME = TABLE_NAME + "ByName";
	
	private Session session;
	
	public CustomerRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id uuid PRIMARY KEY, ").append("name text,").append("email text,").append("telephone text,")
				.append("social_security_number text,").append("bank text,").append("discount_code text);").append("scheduled_flight uuid);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void createTableCustomersByName() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME_BY_NAME).append("(")
				.append("id uuid, ").append("name text,").append("PRIMARY KEY (name, id));");
		
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
	
	public void insertEmployeeByName(Customer customer) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME_BY_NAME).append("(id, name) ").append("VALUES (")
				.append(customer.getID()).append(", '").append(customer.getName()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertEmployeeBatch(Customer customer) {
		StringBuilder sb = new StringBuilder("BEGIN BATCH ").append("INSERT INTO ").append(TABLE_NAME).append("(id, name, email, telephone, social_security_number, bank, discount_code, scheduled_flight) ")
				.append("VALUES (").append(customer.getID()).append(", '").append(customer.getName()).append("', '").append(customer.getEmail()).append("', '")
				.append(customer.getTelephone()).append("', '").append(customer.getPersonalNumber()).append("', '").append(customer.getBank()).append("', '")
				.append(customer.getDiscountCode()).append("', '").append(customer.getScheduledFlightID()).append("');")
				.append("INSERT INTO ").append(TABLE_NAME_BY_NAME).append("(id, name) ").append("VALUES (")
				.append(customer.getID()).append(", '").append(customer.getName()).append("');").append("APPLY BATCH;");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Customer selectByName(String name) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(Row r : rs) {
			Customer s = new Customer(r.getUUID("id"), r.getString("name"), null, null, null, null, null, null);
			
			customers.add(s);
		}
		return customers.get(0);
	}
	
	public List<Customer> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Customer> employees = new ArrayList<Customer>();
		
		for(Row r : rs) {
			Customer customer = new Customer(r.getUUID("id"), r.getString("name"), r.getString("email"), r.getString("telephone"), r.getString("social_security_number"), r.getString("bank"), r.getString("discount_code"), r.getUUID("scheduled_flight"));
			
			employees.add(customer);
		}
		return employees;
	}
	
	public List<Customer> selectAllCustomersByName(){
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Customer> customers = new ArrayList<Customer>();
		
		for(Row r : rs) {
			Customer customer = new Customer(r.getUUID("id"), r.getString("name"), null, null, null, null, null, null);
			
			customers.add(customer);
		}
		return customers;
	}
	
	public void deleteCustomerByName(String name) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME_BY_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
