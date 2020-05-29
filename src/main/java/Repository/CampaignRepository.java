package Repository;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import Domain.Campaign;

public class CampaignRepository {

	private static final String TABLE_NAME = "campaign";
	
	private Session session;
	
	public CampaignRepository(Session session) {
		this.session = session;
	}
	
	public void createTable() {
		StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ").append(TABLE_NAME).append("(")
				.append("id int PRIMARY KEY, ").append("start_date text,").append("end_date text,").append("reduction text,")
				.append("discount_code text);");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void alterTableRoutes(String columnName, String columnType) {
		StringBuilder sb = new StringBuilder("ALTER TABLE ").append(TABLE_NAME).append(" ADD ")
				.append(columnName).append(" ").append(columnType).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void insertCampaign(Campaign campaign) {
		StringBuilder sb = new StringBuilder("INSERT INTO ").append(TABLE_NAME).append("(id, start_date, end_date, reduction, discount_code) ")
				.append("VALUES (").append(campaign.getID()).append(", '").append(campaign.getStartDate()).append("', '").append(campaign.getEndDate()).append("', '")
				.append(campaign.getReduction()).append("', '").append(campaign.getDiscountCode()).append("');");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public Campaign selectByStart(String start) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE start_date = '").append(start).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Campaign> campaigns = new ArrayList<Campaign>();
		
		for(Row r : rs) {
			Campaign campaign = new Campaign(r.getInt("id"), r.getString("start_date"), r.getString("end_date"), r.getString("reduction"), r.getString("discount_code"));
			
			campaigns.add(campaign);
		}
		return campaigns.get(0);
	}
	
	public Campaign selectByEnd(String end) {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE end_date = '").append(end).append("';");
		
		final String query = sb.toString();
		
		ResultSet rs = session.execute(query);
		
		List<Campaign> campaigns = new ArrayList<Campaign>();
		
		for(Row r : rs) {
			Campaign campaign = new Campaign(r.getInt("id"), r.getString("start_date"), r.getString("end_date"), r.getString("reduction"), r.getString("discount_code"));
			
			campaigns.add(campaign);
		}
		return campaigns.get(0);
	}
	
	public List<Campaign> selectAll() {
		StringBuilder sb = new StringBuilder("SELECT * FROM ").append(TABLE_NAME);
		
		final String query = sb.toString();
		ResultSet rs = session.execute(query);
		
		List<Campaign> campaigns = new ArrayList<Campaign>();
		
		for(Row r : rs) {
			Campaign campaign = new Campaign(r.getInt("id"), r.getString("start_date"), r.getString("end_date"), r.getString("reduction"), r.getString("discount_code"));
			
			campaigns.add(campaign);
		}
		return campaigns;
	}
	
	public void deleteCampaignByID(int num) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE id = ").append(num).append(";");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteCampaignByStart(String start) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE start_date = '").append(start).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteCampaignByEnd(String end) {
		StringBuilder sb = new StringBuilder("DELETE FROM ").append(TABLE_NAME).append(" WHERE end_date = '").append(end).append("';");
		
		final String query = sb.toString();
		session.execute(query);
	}
	
	public void deleteTable(String tableName) {
		StringBuilder sb = new StringBuilder("DROP TABLE IF EXISTS ").append(tableName);
		
		final String query = sb.toString();
		session.execute(query);
	}
}
