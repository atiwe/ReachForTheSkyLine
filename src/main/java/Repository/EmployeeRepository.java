package Repository;

import java.util.ArrayList;
import java.util.List;

import Domain.Employee;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class EmployeeRepository {

	private static final String TABLE_NAME = "employees";
	
	private static final String TABLE_NAME_BY_NAME = TABLE_NAME + "ByName";
	
	private Session session;
	
	public EmployeeRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id uuid PRIMARY KEY, ").append("name text,").append("email text,").append("telephone text,")
				.append("personal number text,").append("employment date text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void createTableEmployeesByName() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME_BY_NAME).append("(")
				.append("id uuid, ").append("name text,").append("PRIMARY KEY (name, id));");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableEmployees(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertEmployee(Employee employee) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, name, email, telephone, personal number, employment date) ")
				.append("VALUES (").append(employee.getID()).append(", '").append(employee.getName()).append("', '").append(employee.getEmail()).append("', '")
				.append(employee.getTelephone()).append("', '").append(employee.getPersonalNumber()).append("', '").append(employee.getEmploymentDate())
				.append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertEmployeeByName(Employee employee) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME_BY_NAME).append("(id, name) ").append("VALUES (")
				.append(employee.getID()).append(", '").append(employee.getName()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertEmployeeBatch(Employee employee) {
		StringBuilder sb = new StringBuilder("BEGIN BATCH ").append("INSERT INTO ").append(TABLE_NAME).append("(id, name, email, telephone, personal number, employment date) ")
				.append("VALUES (").append(employee.getID()).append(", '").append(employee.getName()).append("', '").append(employee.getEmail()).append("', '")
				.append(employee.getTelephone()).append("', '").append(employee.getPersonalNumber()).append("', '").append(employee.getEmploymentDate())
				.append("');").append("INSERT INTO ").append(TABLE_NAME_BY_NAME).append("(id, name) ").append("VALUES (")
				.append(employee.getID()).append(", '").append(employee.getName()).append("');").append("APPLY BATCH;");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Employee selectByName(String name) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_NAME).append(" WHERE name = '").append(name).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Employee> employees = new ArrayList<Employee>();
		
		for(Row r : rs) {
			Employee s = new Employee(r.getUUID("id"), r.getString("name"), null, null, null, null);
			
			employees.add(s);
		}
		return employees.get(0);
	}
	
	public List<Employee> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Employee> employees = new ArrayList<Employee>();
		
		for(Row r : rs) {
			Employee employee = new Employee(r.getUUID("id"), r.getString("name"), r.getString("email"), r.getString("telephone"), r.getString("personal number"), r.getString("employment date"));
			
			employees.add(employee);
		}
		return employees;
	}
	
	public List<Employee> selectAllEmployeeByName(){
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME_BY_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Employee> employees = new ArrayList<Employee>();
		
		for(Row r : rs) {
			Employee employee = new Employee(r.getUUID("id"), r.getString("name"), null, null, null, null);
			
			employees.add(employee);
		}
		return employees;
	}
	
	public void deleteEmployeeByName(String name) {
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
