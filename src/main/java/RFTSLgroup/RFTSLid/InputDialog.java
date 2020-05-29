package RFTSLgroup.RFTSLid;

import java.util.UUID;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.cassandra.thrift.Cassandra.AsyncProcessor.system_add_column_family;

public class InputDialog {
	JTextField field1;
	JTextField field2;
	JTextField field3;
	JTextField field4;
	JTextField field5;
	JTextField field6;
	JTextField field7;
	JTextField field8;
	
	public InputDialog() {
		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field4 = new JTextField();
		field5 = new JTextField();
		field6 = new JTextField();
		field7 = new JTextField();
		field8 = new JTextField();
	}
	
	public String[] showAddFlightDialog() {
		Object[] field = {
				"Estimated Start", field1,
				"Estimated Landing", field2,
				"Flight Time", field3,
				"Pilot", field4,
				"Flightline ID", field5
			};
		JOptionPane.showConfirmDialog(null, field, "Add Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4, field5};
		return dialogToArray(fields);
	}
	
	public String[] showAddFlightLineDialog() {
		Object[] field = {
				"Dept City", field1,
				"Arrival City", field2,
				"Flight Duration", field3,
				"Price", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Add Flight Line", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	
	public String[] showEditFlightDialog() {
		Object[] field = {
				"Dept City", field1,
				"Arrival City", field2,
				"Price", field3,
				"Pilot", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Edit Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	
//	private int id;
//	
//	private String name;
//	
//	private String email;
//	
//	private String telephone;
//	
//	private String personalNumber;
//	
//	private String bank;
//	
//	private String discountCode;
//	
//	private int scheduledFlightID;
	
	public String[] showBookFlightDialog() {
		Object[] field = {
				"Name", field1,
				"Mail", field2,
				"Telephone", field3,
				"Social Security Number", field4,
				"Bank", field5,
				"Discount code", field6
			};
		JOptionPane.showConfirmDialog(null, field, "Book Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	
	public String[] showCancelFlightDialog() {
		Object[] field = {
				"Social Security Number", field1,
				"Flight ID", field2
		};
		JOptionPane.showConfirmDialog(null, field, "Cancel Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2};
		return dialogToArray(fields);
	}
	
	public String[] showAddPilotDialog() {
		Object[] field = {
				"Name", field1,
				"Pilot License", field2,
				"Telephone", field3,
				"Email", field4,
				"Social Security #", field5,
				"Employment Date", field6,
			};
		JOptionPane.showConfirmDialog(null, field, "Add Pilot", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4, field5, field6};
		return dialogToArray(fields);
	}
	
	public String[] showEditPilotDialog() {
		Object[] field = {
				"ID", field1,
				"Name", field2,
				"Weekly Flight Hours", field3,
				"Last Flight", field4,
				"Next Flight", field5,
				
			};
		JOptionPane.showConfirmDialog(null, field, "Edit Pilot", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4, field5};
		return dialogToArray(fields);
	}
	
	public String[] showAddEmployeeDialog() {
		Object[] field = {
				"Name", field1,
				"Email", field2,
				"Telephone", field3,
				"Social", field4,
				"Employment date", field5
			};
		JOptionPane.showConfirmDialog(null, field, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4, field5};
		return dialogToArray(fields);
	}
	
	public String[] showAddAircraftDialog() {
		Object[] field = {
				"Model", field1,
				"Producer", field2,
				"Capacity", field3,
				"Flight Hours", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Add Aircraft", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}	
	
	public String[] showAddDiscountDialog() {
		Object[] field = {
				"Start Date", field1,
				"End Date", field2,
				"Reduction", field3,
				"Discount code", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Add Discount", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	

	public String[] showAddCampainDialog() {
		Object[] field = {
				"Start date", field1,
				"End date", field2,
				"Reduction", field3,
				"Discound code", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Add Campain", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}

	public String[] showAddFlightRequestDialog() {
		Object[] field = {
				"Customer ID", field1,
				"Departure city", field2,
				"Arrival city", field3,
				"Start time", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Add Flight Request", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	
	private String[] dialogToArray(JTextField[] fields) {
		String[] arr = new String[fields.length];
		for(int i = 0; i<fields.length; i++) {
			arr[i]=fields[i].getText();
		}
		return arr;
	}
}
