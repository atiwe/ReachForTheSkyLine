package RFTSLgroup.RFTSLid;


import javax.swing.JOptionPane;
import javax.swing.JTextField;

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

	public String[] showEditScheduledFlightDialog() {
		Object[] field = {
				"ETD", field1,
				"ETA", field2,
				"Pilot", field3
			};
		JOptionPane.showConfirmDialog(null, field, "Edit Scheduled Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3};
		return dialogToArray(fields);
	}

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
		JTextField[] fields = {field1, field2, field3, field4, field5, field6};
		return dialogToArray(fields);
	}
	
	public boolean showSureToBookDialog() {
		Object[] options = {"Book flight", "Cancel"};
	// int choice = JOptionPane.showConfirmDialog(null, options, "Are you sure",  JOptionPane.OK_CANCEL_OPTION);
	int choice = JOptionPane.showOptionDialog(null, "Are you sure you want to book this flight?", "Please choose ",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options, null);

		if (choice == JOptionPane.NO_OPTION) {
			return false;
		}

		return true;

	}
	
	public boolean areYouASure() {
		
		int selectedOption = JOptionPane.showConfirmDialog(null, "Wanna book?", "do it", JOptionPane.YES_NO_OPTION);
		
		if (selectedOption == JOptionPane.YES_OPTION) {
			return true;
		}
		
		return false;
		
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
				"Weekly Flight Hours", field1,
				"Last Flight", field2,
				"Next Flight", field3,

			};
		JOptionPane.showConfirmDialog(null, field, "Edit Pilot", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3};
		return dialogToArray(fields);
	}

	public String[] showEditCampaignDialog() {
		Object[] field = {
				"Start Date", field1,
				"End Date", field2,
				"Reduction", field3,
				"Discount code", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Edit Discount", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}

	public String[] showAddEmployeeDialog() {
		Object[] field = {
				"Name", field1,
				"Email", field2,
				"Telephone", field3,
				"Social", field4,
				"Employment date", field5,
				"Username", field6,
				"Password", field7
			};
		JOptionPane.showConfirmDialog(null, field, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4, field5, field6, field7};
		return dialogToArray(fields);
	}

	public String[] showEditEmployeeDialog() {
		Object[] field = {
				"Name", field1,
				"Email", field2,
				"Telephone", field3,
				"Social", field4,
				"Employment date", field5
			};
		JOptionPane.showConfirmDialog(null, field, "Edit Employee", JOptionPane.OK_CANCEL_OPTION);
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

	public String[] showEditAircraftDialog() {
		Object[] field = {
				"Model", field1,
				"Producer", field2,
				"Capacity", field3,
				"Flight Hours", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Edit Aircraft", JOptionPane.OK_CANCEL_OPTION);
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

	public boolean showLoginOptions() {
		Object[] options = {"Login", "Create new"};
		int choice = JOptionPane.showOptionDialog(null, "New or Existing Customer?", "Please choose: ", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, null);

		if (choice == JOptionPane.NO_OPTION) {
			return false;
		}

		return true;

	}

	public String[] showCreateCustomerDialog() {
		Object[] field = {
				"Name", field1,
				"Email", field2,
				"Telephone", field3,
				"SSN", field4,
				"Bank", field5,
				"Username", field6,
				"Password", field7
		};

		JOptionPane.showConfirmDialog(null, field, "Create new customer", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4, field5, field6, field7};
		return dialogToArray(fields);
	}

	public String[] showCustomerLoginDialog() {
		Object[] field = {
				"Username", field1,
				"Password", field2,
		};
		JOptionPane.showConfirmDialog(null, field, "Login as Customer", JOptionPane.OK_CANCEL_OPTION);

		JTextField[] fields = {field1, field2};
		return dialogToArray(fields);
	}

	public String[] showEmployeeLoginDialog() {
		Object[] field = {
				"Username", field1,
				"Password", field2
		};
		JOptionPane.showConfirmDialog(null, field, "Login as Employee", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2};
		return dialogToArray(fields);
	}

	public boolean confirmationDialog(String[] textFields) {
		for (String var : textFields)
		{
		    if(var.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Nothing saved because not all fields were answered");
		    	return false;
		    }
		}
		JOptionPane.showMessageDialog(null, "Changes saved!");
		return true;

	}


	private String[] dialogToArray(JTextField[] fields) {
		String[] arr = new String[fields.length];
		for(int i = 0; i<fields.length; i++) {
			arr[i]=fields[i].getText();
		}
		return arr;
	}
}
