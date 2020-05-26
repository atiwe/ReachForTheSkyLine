
package RFTSLgroup.RFTSLid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

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
	
	public GuiEmployees() {
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
		
		//Add buttons with action listeners
		btnAddFlight = new JButton("Add Flight");
		btnAddFlightLine = new JButton("Add Flight Line");
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

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAddFlight) {
			InputDialog id = new InputDialog();
			id.showAddAircraftDialog();
		} 
		else if (e.getSource() == btnAddFlightLine) {
			InputDialog id = new InputDialog();
			id.showAddFlightLineDialog();
		}
		else if (e.getSource() == btnRemoveFlight) {
			
		}
		else if (e.getSource() == btnEditFlight) {
			InputDialog id = new InputDialog();
			id.showEditFlightDialog();
		}
		else if (e.getSource() == btnPlaceBooking) {
			InputDialog id = new InputDialog();
			id.showBookFlightDialog();
		}
		
	}

}
