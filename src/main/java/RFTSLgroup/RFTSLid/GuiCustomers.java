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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.apache.cassandra.utils.OutputHandler.SystemOutput;

import Domain.ScheduledFlight;
import Domain.Route;

public class GuiCustomers extends JPanel implements ActionListener {
	JLabel jlemp;
	JLabel jlsf;
	JLabel jlfl;
	JLabel emp = new JLabel("");
	JLabel emp2 = new JLabel("");
	JList jlistsf;
	JList jlistfl;
	JButton btnBookFlight, btnCancelFlight;
	
	DefaultListModel<String> modelsf;
	DefaultListModel<String> modelfl;
	
	Object[] dreamFlights;
	private Controller controller;
	List<ScheduledFlight> currentflightList;
	List<Route> currentRouteList;
	
	public GuiCustomers(Controller controller) {
		this.controller = controller;
		//Panels with grid layout
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000, 700));
		JPanel centrePanel = new JPanel();
		centrePanel.setLayout(new GridLayout(10,0));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,1));
		JPanel bottomPanel2 = new JPanel();
		bottomPanel2.setLayout(new GridLayout(1,1));
		
		//Labels
		jlemp = new JLabel("Customers", SwingConstants.CENTER);
		jlemp.setFont(new Font("Serif", Font.BOLD, 24));
		jlsf = new JLabel("Scheduled flights", SwingConstants.CENTER);
		jlfl = new JLabel("Routes", SwingConstants.CENTER);
		
		//Lists
		modelsf = new DefaultListModel<String>();
		modelfl = new DefaultListModel<String>();
		jlistsf = new JList(modelsf);
		jlistfl = new JList(modelfl);
		

		currentflightList = controller.getScheduledFlights();
		for(ScheduledFlight scheduledFlight : currentflightList)
		{
			modelsf.addElement("ID: " + scheduledFlight.getID() + ", Estimated start: " + scheduledFlight.getEstimatedStart() + ", Estimated landing: " + scheduledFlight.getEstimatedLanding() + ", Flight time: " + scheduledFlight.getFlightTime()
			+ ", Pilot: " + scheduledFlight.getPilot() + ", RouteID: " + scheduledFlight.getRouteID());
		}

		currentRouteList = controller.getFlightLines();
		for(Route route : currentRouteList)
		{
			modelfl.addElement("ID: " + route.getID() + ", Departure city: " + route.getDepartureCity() + ", Arrival city: " + route.getArrivalCity() + ", Flight time: "
					+ route.getFlightDuration() + ", Price: " + route.getPrice());
		}
		
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
		centrePanel.add(emp);
		centrePanel.add(bottomPanel);
		centrePanel.add(emp2);
		centrePanel.add(bottomPanel2);
		add(centrePanel);

	}

	//Action performed on button clicks
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBookFlight) {
			InputDialog id = new InputDialog();
			String[] arr = id.showBookFlightDialog();
			int flightID = currentflightList.get(jlistsf.getSelectedIndex()).getID();
			if(id.confirmationDialog(arr)) {
				controller.bookFlight(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], flightID);
			}
		} 
		else if (e.getSource() == btnCancelFlight) {
			InputDialog cancel = new InputDialog();
			String[] arr = cancel.showCancelFlightDialog();
			controller.cancelFlight(arr[0], Integer.parseInt(arr[1]));
		}
		
	}
	
}
	