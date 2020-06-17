package RFTSLgroup.RFTSLid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.Collections;
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
import Domain.User;
import Domain.Customer;
import Domain.Employee;
import Domain.FlightRequest;
import Domain.Route;

public class GuiCustomers extends JPanel implements ActionListener {
	JLabel jlemp;
	JLabel jlsf;
	JLabel jlfl;
	JLabel jlbf;
	JLabel emp2 = new JLabel("");
	JLabel emp3 = new JLabel("");
	JList jlistsf;
	JList jlistbf;
	JButton btnBookFlight, btnCancelFlight;
	
	DefaultListModel<String> modelsf;
	DefaultListModel<String> modelbf;
	
	Object[] dreamFlights;
	private Controller controller;
	List<ScheduledFlight> currentflightList;
	List<Customer> currentCustomers;
	private User currentUser;
	List<FlightRequest> currentBookedFlights;
	
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
		jlbf = new JLabel("Booked flights", SwingConstants.CENTER);
		
		//Lists
		modelsf = new DefaultListModel<String>();
		modelbf = new DefaultListModel<String>();
		jlistsf = new JList(modelsf);
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
		centrePanel.add(jlbf);
		centrePanel.add(new JScrollPane(jlistbf));
		centrePanel.add(emp2);
		centrePanel.add(bottomPanel);
		centrePanel.add(emp3);
		centrePanel.add(bottomPanel2);
		add(centrePanel);

	}
	
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
		updateBookedFlights();
	}
	
	public void updateTexts() {
		updateBookedFlights();
		updateScheduledFlights();
	}
	
	private void updateBookedFlights() {
		modelbf.clear();
		if(currentUser!=null) {
			List<FlightRequest> bookedFlights = controller.getFlightRequests();
			for(FlightRequest f : bookedFlights) {
				if(f.getCustomerID()==currentUser.getRelatedID()) {
					modelbf.addElement("Booked flight from " + f.getDepartureCity() + " to " + f.getArrivalCity() + " | Time: " +
				f.getStartTime());
				}
			}	
		}		 
	}
	
	private void updateScheduledFlights() {
		modelsf.clear();
		currentflightList = controller.getScheduledFlights();
		for(ScheduledFlight scheduledFlight : currentflightList)
		{
			Route route = controller.getRouteById(scheduledFlight.getID());
			String departureCity = "No destination";
			String arrivalCity = "No destination";
			if(route!=null) {
				departureCity = route.getDepartureCity();
				arrivalCity = route.getArrivalCity();
			}
			modelsf.addElement("Flight from " + departureCity + " to " + arrivalCity + 
			" | Estimated start: " + scheduledFlight.getEstimatedStart() + " | Estimated landing: " + 
			scheduledFlight.getEstimatedLanding() + " | Flight time: " + scheduledFlight.getFlightTime());
		}
	}
	

	//Action performed on button clicks
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBookFlight) {
			if(jlistsf.getSelectedIndex()>=0) {
			InputDialog id = new InputDialog();
			boolean sure = id.areYouASure();
			ScheduledFlight selectedFlight = currentflightList.get(jlistsf.getSelectedIndex());
			int flightID = selectedFlight.getID();
			Route route = controller.getRouteById(flightID);
			if(sure == true) {
				controller.bookFlight(currentUser.getRelatedID(), route.getDepartureCity(), route.getArrivalCity(), selectedFlight.getEstimatedStart());
				updateBookedFlights();
				}
			}else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the scheduled list to book it!");
			}
		} 
		else if (e.getSource() == btnCancelFlight) {
			if(jlistbf.getSelectedIndex()>=0) {
				
				System.out.println(jlistbf.getSelectedIndex());
				currentBookedFlights = controller.getFlightRequests();
				FlightRequest choosenBooked = currentBookedFlights.get(jlistbf.getSelectedIndex());
				int bookedID = choosenBooked.getID();
				
				int dialogResult = JOptionPane.showConfirmDialog(null, "Remove Flight?", "Warning", JOptionPane.OK_CANCEL_OPTION);
				if(dialogResult == JOptionPane.OK_OPTION) {
					controller.cancelFlight(bookedID);
					JOptionPane.showMessageDialog(null, "Flight canceled!");
					updateBookedFlights();
				}
			}else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the list to cancel it!");
			}
		}
		
	}
	
}
	