package Domain;

import java.util.UUID;

public class Campaign {

	private UUID id;
	
	private String startDate;
	
	private String endDate;
	
	private String reduction;
	
	private String discountCode;
	
	Campaign(){
	}
	
	public Campaign(UUID ID, String StartDate, String EndDate, String Reduction, String DiscountCode) {
		this.id = ID;
		this.startDate = StartDate;
		this.endDate = EndDate;
		this.reduction = Reduction;
		this.discountCode = DiscountCode;
	}
	
	public UUID getID() {
		return id;
	}
	
	public void setID(UUID id) {
		this.id = id;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public String getReduction() {
		return reduction;
	}
	
	public void setReduction(String reduction) {
		this.reduction = reduction;
	}
	
	public String getDiscountCode() {
		return discountCode;
	}
	
	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}
}
