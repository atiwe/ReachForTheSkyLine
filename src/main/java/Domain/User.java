package Domain;

public class User {
	
	private String username;
	private String password;
	private int relatedID;
	private String type;
	
	public User(String username, String password, int relatedID, String type) {
		this.username = username;
		this.password = password;
		this.relatedID = relatedID;
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRelatedID() {
		return relatedID;
	}

	public void setRelatedID(int relatedID) {
		this.relatedID = relatedID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
