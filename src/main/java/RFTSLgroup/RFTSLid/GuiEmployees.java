
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import Domain.Route;
import Domain.ScheduledFlight;

public class GuiEmployees extends JPanel implements ActionListener{
	JLabel jlemp;
	JLabel jlsf;
	JLabel jlfl;
	JLabel emp = new JLabel("");
	JLabel emp2 = new JLabel("");
	JList jlistsf;
	JList jlistfl;
	JButton btnAddFlight, btnAddFlightLine, btnRemoveFlight, btnEditFlight, btnPlaceBooking;

	DefaultListModel<String> modelsf;
	DefaultListModel<String> modelfl;
	private Controller controller;

	List<ScheduledFlight> currentScheduledFlightList;
	List<Route> currentRouteList;

	public GuiEmployees(Controller controller) {
		this.controller = controller;
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000, 700));
		JPanel centrePanel = new JPanel();
		centrePanel.setLayout(new GridLayout(10,0));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,3));
		JPanel bottomPanel2 = new JPanel();
		bottomPanel2.setLayout(new GridLayout(1,3));

		//Labels
		jlemp = new JLabel("Employees", SwingConstants.CENTER);
		jlemp.setFont(new Font("Serif", Font.BOLD, 24));
		jlsf = new JLabel("Scheduled flights", SwingConstants.CENTER);
		jlfl = new JLabel("Flight lines", SwingConstants.CENTER);

		//List views for flights
		modelsf = new DefaultListModel<String>();
		modelfl = new DefaultListModel<String>();
		jlistsf = new JList(modelsf);
		jlistfl = new JList(modelfl);

		updateTexts();

		//Add buttons with action listeners
		btnAddFlight = new JButton("Add Flight");
		btnAddFlightLine = new JButton("Add Flight Route");
		btnRemoveFlight = new JButton("Remove Flight");
		btnEditFlight = new JButton("Edit Flight");
		btnPlaceBooking = new JButton("Place Booking");
		btnAddFlight.addActionListener(this);
		btnAddFlightLine.addActionListener(this);
		btnRemoveFlight.addActionListener(this);
		btnEditFlight.addActionListener(this);
		btnPlaceBooking.addActionListener(this);

		//Add components to panel
		bottomPanel.add(btnAddFlight);
		bottomPanel.add(btnAddFlightLine);
		bottomPanel2.add(btnRemoveFlight);
		bottomPanel2.add(btnEditFlight);
		bottomPanel2.add(btnPlaceBooking);

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

	private boolean checkFlightLineID(int id) {
		for(Route r : currentRouteList) {
			if(r.getID()==id) {
				return true;
			}
		}
		return false;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddFlight) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddFlightDialog();
			if(checkFlightLineID(Integer.parseInt(arr[4]))) {
				if(id.confirmationDialog(arr)) {
					controller.addFlight(arr[0], arr[1], arr[2], arr[3], Integer.parseInt(arr[4]));
			
				}
			}else {
				JOptionPane.showMessageDialog(null, "Not a valid flight line code!");
			}
			
			updateScheduledFlights();
		} 

		else if (e.getSource() == btnAddFlightLine) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddFlightLineDialog();
			if(id.confirmationDialog(arr)) {
				controller.addRoute(arr[0], arr[1], arr[2], arr[3]);
			}
			updateFlightRoutes();
		}

		else if (e.getSource() == btnRemoveFlight) {

			if(jlistsf.getSelectedIndex()>=0) {
				currentScheduledFlightList = controller.getScheduledFlights();
				int flightID = currentScheduledFlightList.get(jlistsf.getSelectedIndex()).getID();

				JOptionPane.showMessageDialog(this, "Removing Scheduled Flight with ID " + flightID );

				controller.removeFlight(flightID);
				updateScheduledFlights();

				controller.removePilot(flightID);

			} else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the list to remove it!");
			}
			
			updateScheduledFlights();

		}

		else if (e.getSource() == btnEditFlight) {
			if(jlistsf.getSelectedIndex()>=0) {
				currentScheduledFlightList = controller.getScheduledFlights();
				ScheduledFlight schFlight = currentScheduledFlightList.get(jlistsf.getSelectedIndex());

				int flightID = schFlight.getID();
				String flightTime = schFlight.getFlightTime();
				int flightRouteID = schFlight.getRouteID(); 

				JOptionPane.showMessageDialog(this, "Editing Scheduled Flight with ID " + flightID );
				InputDialog id = new InputDialog();
				String[] arr = id.showEditScheduledFlightDialog();

				controller.editScheduledFlight(flightID, arr[0], arr[1], flightTime, arr[2], flightRouteID);
				updateScheduledFlights();
			}
			else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the list to edit it!");
			}

		}

		else if (e.getSource() == btnPlaceBooking) {
			if(jlistsf.getSelectedIndex()>=0) {
				currentScheduledFlightList = controller.getScheduledFlights();
				ScheduledFlight schFlight = currentScheduledFlightList.get(jlistsf.getSelectedIndex());

				int flightID = schFlight.getID();

				InputDialog id = new InputDialog();
				String[] arr = id.showBookFlightDialog();

				controller.bookFlight(arr[0], arr[1], arr[2], arr[3], arr[4],arr[5], flightID);
				updateScheduledFlights();
				
			}
			else {
				JOptionPane.showMessageDialog(null, "You need to select a flight from the list to book it!");
			}
		}
	}

	public void updateTexts() {
		updateScheduledFlights();
		updateFlightRoutes();
	}


	private void updateScheduledFlights() {
		modelsf.clear();
		currentScheduledFlightList = controller.getScheduledFlights();
		for (ScheduledFlight scheduledFlight : currentScheduledFlightList) {
			modelsf.addElement(" Take Off: " + scheduledFlight.getEstimatedStart() + " | Arrival: " + scheduledFlight.getEstimatedLanding() + 
					" | Flight Time: " + scheduledFlight.getFlightTime() + " | Pilot: " + scheduledFlight.getPilot() + " | Route ID: " +
					scheduledFlight.getRouteID());
		}
	}

	private void updateFlightRoutes() {
		modelfl.clear();
		currentRouteList = controller.getFlightLines();
		for (Route routes : currentRouteList) {
			modelfl.addElement(" ID: " + routes.getID() + " | Dep. City: " + routes.getDepartureCity() + " | Arr. City: " + 
					routes.getArrivalCity() + " | Duration: " + routes.getFlightDuration() + " | Price:" + 
					routes.getPrice());
		}

	}
}
