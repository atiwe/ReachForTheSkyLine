package RFTSLgroup.RFTSLid;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import org.apache.cassandra.thrift.Cassandra.AsyncProcessor.system_add_column_family;

public class InputDialog {
	JTextField field1;
	JTextField field2;
	JTextField field3;
	JTextField field4;
	
	public InputDialog() {
		field1 = new JTextField();
		field2 = new JTextField();
		field3 = new JTextField();
		field4 = new JTextField();
	}
	
	public String[] showAddFlightDialog() {
		Object[] field = {
				"Date", field1,
				"Time", field2,
				"Plane", field3,
				"Pilot", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Add Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	
	public String[] showAddFlightLineDialog() {
		Object[] field = {
				"Dept City", field1,
				"Arrival City", field2,
				"Price", field3
			};
		JOptionPane.showConfirmDialog(null, field, "Add Flight Line", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3};
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
	
	public String[] showBookFlightDialog() {
		Object[] field = {
				"Name", field1,
				"Telephone", field2,
				"Social Security Number", field3,
				"Discount code", field4
			};
		JOptionPane.showConfirmDialog(null, field, "Book Flight", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3, field4};
		return dialogToArray(fields);
	}
	
	public String[] showAddPilotDialog() {
		Object[] field = {
				"Name", field1,
				"Pilop License", field2,
				"Telephone", field3,
			};
		JOptionPane.showConfirmDialog(null, field, "Add Pilot", JOptionPane.OK_CANCEL_OPTION);
		JTextField[] fields = {field1, field2, field3};
		return dialogToArray(fields);
	}
	
	private String[] dialogToArray(JTextField[] fields) {
		String[] arr = new String[fields.length];
		for(int i = 0; i<fields.length; i++) {
			arr[i]=fields[i].getText();
		}
		return arr;
	}
	
//	public static void main(String[] args) {
//		InputDialog input = new InputDialog();
//		String[] arr = input.showEditFlightDialog();
//		for(int i = 0; i<arr.length; i++) {
//			System.out.println(arr[i]);
//		}
//		arr = input.showBookFlightDialog();
//		for(int i = 0; i<arr.length; i++) {
//			System.out.println(arr[i]);
//		}
//		arr = input.showAddPilotDialog();
//		for(int i = 0; i<arr.length; i++) {
//			System.out.println(arr[i]);
//		}
//	}

}
