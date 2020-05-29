package RFTSLgroup.RFTSLid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import Domain.ScheduledFlight;
import Domain.Customer;
import Domain.Employee;
import Domain.Route;

public class GuiCustomers extends JPanel implements ActionListener {
	JLabel jlemp;
	JLabel jlsf;
	JLabel jlfl;
	JLabel jlbf;
	JLabel emp2 = new JLabel("");
	JLabel emp3 = new JLabel("");
	JList jlistsf;
	JList jlistfl;
	JList jlistbf;
	JButton btnBookFlight, btnCancelFlight;
	
	DefaultListModel<String> modelsf;
	DefaultListModel<String> modelfl;
	DefaultListModel<String> modelbf;
	
	Object[] dreamFlights;
	private Controller controller;
	List<ScheduledFlight> currentflightList;
	List<Route> currentRouteList;
	List<Customer> currentCustomers;
	
	public GuiCustomers(Controller controller) {
		this.controller = controller;
		controller.setGuiCustomer(this);
		//Panels with grid layout
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000, 700));
		JPanel centrePanel = new JPanel();
		centrePanel.setLayout(new GridLayout(12,0));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,1));
		JPanel bottomPanel2 = new JPanel();
		bottomPanel2.setLayout(new GridLayout(1,1));
		
		//Labels
		jlemp = new JLabel("Customers", SwingConstants.CENTER);
		jlemp.setFont(new Font("Serif", Font.BOLD, 24));
		jlsf = new JLabel("Scheduled flights", SwingConstants.CENTER);
		jlfl = new JLabel("Routes", SwingConstants.CENTER);
		jlbf = new JLabel("Booked flights", SwingConstants.CENTER);
		
		//Lists
		modelsf = new DefaultListModel<String>();
		modelfl = new DefaultListModel<String>();
		modelbf = new DefaultListModel<String>();
		jlistsf = new JList(modelsf);
		jlistfl = new JList(modelfl);
		jlistbf = new JList(modelbf);
		
		updateTexts();
		
		//Buttons with action listeners
		btnBookFlight = new JButton("Book Flight");
		btnCancelFlight = new JButton("Cancel Flight");
		btnBookFlight.addActionListener(this);
		btnCancelFlight.addActionListener(this);
	
		//Adding components to panels
		bottomPanel.add(btnBookFlight);
		bottomPanel2.add(btnCancelFlight);
	
		centrePanel.add(jlemp);
		centrePanel.add(jlsf);
		centrePanel.add(new JScrollPane(jlistsf));
		centrePanel.add(jlfl);
		centrePanel.add(new JScrollPane(jlistfl));
		centrePanel.add(jlbf);
		centrePanel.add(new JScrollPane(jlistbf));
		centrePanel.add(emp2);
		centrePanel.add(bottomPanel);
		centrePanel.add(emp3);
		centrePanel.add(bottomPanel2);
		add(centrePanel);

	}
	
	public void updateTexts() {
		updateBookedFlights();
		updateFlightLines();
		updateScheduledFlights();
	}
	
	private void updateBookedFlights() {
		modelbf.clear();
		  currentCustomers = controller.getCustomers(); 
			for(Customer c : currentCustomers) {
				modelbf.addElement("ID: " + c.getID() + ", Name" + c.getName() + ", Email: " + c.getEmail() + ", Phone: " + c.getTelephone() + ", Social security number: " + c.getPersonalNumber() + ", Bank: " + c.getBank() + ", Discount code:" + c.getDiscountCode() + ", Flight ID: " + c.getScheduledFlightID());
			}
		 
	}
	
	private void updateFlightLines() {
		modelfl.clear();
		currentRouteList = controller.getFlightLines();
		for(Route route : currentRouteList){
			modelfl.addElement("ID: " + route.getID() + ", Departure city: " + route.getDepartureCity() + ", Arrival city: " + route.getArrivalCity() + ", Flight time: "
					+ route.getFlightDuration() + ", Price: " + route.getPrice());
		}
	}
	
	private void updateScheduledFlights() {
		modelsf.clear();
		currentflightList = controller.getScheduledFlights();
		for(ScheduledFlight scheduledFlight : currentflightList)
		{
			modelsf.addElement("ID: " + scheduledFlight.getID() + ", Estimated start: " + scheduledFlight.getEstimatedStart() + ", Estimated landing: " + scheduledFlight.getEstimatedLanding() + ", Flight time: " + scheduledFlight.getFlightTime()
			+ ", Pilot: " + scheduledFlight.getPilot() + ", RouteID: " + scheduledFlight.getRouteID());
		}
	}

	//Action performed on button clicks
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBookFlight) {
			if(jlistsf.getSelectedIndex()>=0) {
			InputDialog id = new InputDialog();
			String[] arr = id.showBookFlightDialog();
			int flightID = currentflightList.get(jlistsf.getSelectedIndex()).getID();
			if(id.confirmationDialog(arr)) {
				controller.bookFlight(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], flightID);
				updateBookedFlights();
			}
			}
			else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the scheduled list to book it!");
			}
		} 
		else if (e.getSource() == btnCancelFlight) {
			if(jlistbf.getSelectedIndex()>=0) {
				int customerID = currentCustomers.get(jlistbf.getSelectedIndex()).getID();
				controller.cancelFlight(customerID);
				JOptionPane.showMessageDialog(null, "Flight canceled!");
			}else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the list to cancel it!");
			}
		}
		
	}
	
}
	