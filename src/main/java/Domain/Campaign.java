package Domain;

public class Campaign {

	private int id;
	
	private String startDate;
	
	private String endDate;
	
	private String reduction;
	
	private String discountCode;
	
	Campaign(){
	}
	
	public Campaign(int ID, String StartDate, String EndDate, String Reduction, String DiscountCode) {
		this.id = ID;
		this.startDate = StartDate;
		this.endDate = EndDate;
		this.reduction = Reduction;
		this.discountCode = DiscountCode;
	}
	
	public int getID() {
		return id;
	}
	
	public void setID(int id) {
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
