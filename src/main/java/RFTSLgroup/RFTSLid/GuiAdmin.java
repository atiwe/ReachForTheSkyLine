package RFTSLgroup.RFTSLid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.util.List;

import Domain.Airplane;
import Domain.Campaign;
import Domain.Employee;
import Domain.Pilot;

public class GuiAdmin extends JPanel implements ActionListener {
	
	JLabel jlHeading, jlInfo, jlChoose; 
	JList jlistInfo; 
	JButton btnAddPilot, btnAddAir, btnAddEmp, btnAddDis, btnRemPilot, btnRemAir, btnRemEmp, btnEditPilot;
	JRadioButton rbtnPilot, rbtnAir, rbtnEmp, rbtnDis;
	ButtonGroup btnGroup;
	
	DefaultListModel<String> infoModel; 
	private Controller controller;
	
	List<Employee> currentEmployeeList;
	List<Pilot> currentPilotList;
	List<Campaign> currentCampaignList;
	List<Airplane> currentAirplaneList;
	
	
	public GuiAdmin(Controller controller) {
		this.controller = controller;
		// Panels with grid layout
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000, 600));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 2));
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2,1));
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		
		JPanel leftCenterPanel = new JPanel();
		leftCenterPanel.setLayout(new GridLayout(5,1));
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(2, 4));
		
		// Labels
		jlHeading = new JLabel("Admin", SwingConstants.CENTER);
		jlHeading.setFont(new Font("Serif", Font.BOLD, 24));
		jlInfo = new JLabel("Info", SwingConstants.CENTER);
		jlChoose = new JLabel("Choose what information you want to see");
		
		//Radio Buttons
		String pilot = "Pilots";
		rbtnPilot = new JRadioButton(pilot);
		rbtnPilot.setMnemonic(KeyEvent.VK_B);
		rbtnPilot.setActionCommand(pilot);
		rbtnPilot.addActionListener(this);
		
		String aircraft = "Aircrafts";
		rbtnAir = new JRadioButton(aircraft);
		rbtnAir.setMnemonic(KeyEvent.VK_B);
		rbtnAir.setActionCommand(aircraft);
		rbtnAir.addActionListener(this);
		
		String employee = "Employees";
		rbtnEmp = new JRadioButton(employee);
		rbtnEmp.setMnemonic(KeyEvent.VK_B);
		rbtnEmp.setActionCommand(employee);
		rbtnEmp.addActionListener(this);
		
		String discount = "Discounts";
		rbtnDis = new JRadioButton(discount);
		rbtnDis.setMnemonic(KeyEvent.VK_B);
		rbtnDis.setActionCommand(discount);
		rbtnDis.addActionListener(this);
		
		//Radio button group
		btnGroup = new ButtonGroup();
		btnGroup.add(rbtnPilot);
		btnGroup.add(rbtnAir);
		btnGroup.add(rbtnEmp);
		btnGroup.add(rbtnDis);
		
		//List view for information - depends on which radio button is chosen
		infoModel = new DefaultListModel<String>();
		jlistInfo = new JList(infoModel);

		//Buttons with action listeners
		btnAddPilot = new JButton("Add Pilot");
		btnAddPilot.addActionListener(this);
		btnAddAir = new JButton("Add Aircraft");
		btnAddAir.addActionListener(this);
		btnAddEmp = new JButton("Add Employee");
		btnAddEmp.addActionListener(this);
		btnAddDis = new JButton("Add Discount");
		btnAddDis.addActionListener(this);
		btnRemPilot = new JButton("Remove Pilot");
		btnRemPilot.addActionListener(this);
		btnRemAir = new JButton("Remove Aircraft");
		btnRemAir.addActionListener(this);
		btnRemEmp = new JButton("Remove Employee");
		btnRemEmp.addActionListener(this);
		btnEditPilot = new JButton("Edit Pilot Info");
		btnEditPilot.addActionListener(this);
		;
		
		//Adding components to panels
		mainPanel.add(topPanel);
		topPanel.add(jlHeading);
		topPanel.add(jlInfo);
		
		mainPanel.add(centerPanel);
		centerPanel.add(leftCenterPanel);
		leftCenterPanel.add(jlChoose);
		leftCenterPanel.add(rbtnPilot);
		leftCenterPanel.add(rbtnAir);
		leftCenterPanel.add(rbtnEmp);
		leftCenterPanel.add(rbtnDis);
		
		centerPanel.add(new JScrollPane(jlistInfo));
		
		mainPanel.add(bottomPanel);
		bottomPanel.add(btnAddAir);
		bottomPanel.add(btnAddEmp);
		bottomPanel.add(btnAddPilot);
		bottomPanel.add(btnAddDis);
		bottomPanel.add(btnRemAir);
		bottomPanel.add(btnRemEmp);
		bottomPanel.add(btnRemPilot);
		bottomPanel.add(btnEditPilot);
			
		add(mainPanel);	
	}
	
	public void createUI() {
		JFrame frame = new JFrame("Databas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		frame.pack();
		frame.setVisible(true);
	}
	
	// Actions perfomed when buttons clicked
	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == btnAddPilot) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddPilotDialog();
			if(id.confirmationDialog(arr)) {
				controller.addPilot( arr[0], arr[3], arr[2], arr[4], arr[5], arr[1]);
			}
		} 
		else if (e.getSource() == btnAddEmp) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddEmployeeDialog();	
			if(id.confirmationDialog(arr)) {
				controller.addEmployee(arr[0], arr[1], arr[2], arr[3], arr[4]);
			}
		} 
		else if (e.getSource() == btnAddAir) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddAircraftDialog();
			if(id.confirmationDialog(arr)) {
				controller.addAircraft( arr[0], arr[1], arr[2], arr[3]);
			}
		}
		else if (e.getSource() == btnAddDis) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddDiscountDialog();
			if(id.confirmationDialog(arr)) {
				controller.addCampaign(arr[0], arr[1], arr[2], arr[3]);
			}
		}
		else if (e.getSource() == btnRemAir) {
			infoModel.addElement("Removing Aircraft");
			//controller.removeAircraft(aircraftID);
		}
		else if (e.getSource() == btnRemPilot) {
			infoModel.addElement("Removing Pilot");
			//controller.removePilot(pilotID);
		}
		else if (e.getSource() == btnRemEmp) {
			infoModel.addElement("Removing Employee");
			//controller.removeEmployee(employeeID);
		}
		else if (e.getSource() == btnEditPilot) {
			InputDialog id = new InputDialog();
			String[] arr = id.showEditPilotDialog();
		//controller.editPilot(arr[0], arr[1], arr[2], arr[3], arr[4]);
		}
		else if (rbtnPilot.isSelected()) {
			infoModel.clear();
			showPilots();
		}
		else if (rbtnEmp.isSelected()) {
			infoModel.clear();
			showEmployees();
		}
		else if(rbtnDis.isSelected()) {
			infoModel.clear();
			showDiscounts();
		}
		else if (rbtnAir.isSelected()) {
			infoModel.clear();
			showAircrafts();
		}
	}
	
	public void showPilots() {
		
		currentPilotList = controller.getPilots();
		for (Pilot pilots: currentPilotList) {
			infoModel.addElement( " ID: " + pilots.getID() + " | Name: " + pilots.getName() + " | E-mail: " + pilots.getEmail() + " | Tel: " +
					pilots.getTelephone() + " | SSN: " + pilots.getPersonalNumber() + " | Emp. Date: " + pilots.getEmploymentDate() + " | Pilot License: " +
					pilots.getPilotLicense() + " | Weekly Flight Hours: " + pilots.getWeeklyFlightHours() + " | Last Flight: " + 
					pilots.getLastFlight() + " | Next Flight: " + pilots.getNextFlight());
		}
	}
	
	public void showEmployees() {
		  currentEmployeeList = controller.getEmployees(); 
		  for(Employee employees : currentEmployeeList) {
			  infoModel.addElement(" ID: " + employees.getID() + "| Name: " +
				  employees.getName() + " | Email: " + employees.getEmail() + " | Tel: " +
				  employees.getTelephone() + " | SSN: " + employees.getPersonalNumber()+ " | Emp. Date: " +
				  employees.getEmploymentDate()); 
			  }
		 
	}
	
	public void showDiscounts() {
		currentCampaignList = controller.getDiscounts();
		for (Campaign campaigns : currentCampaignList) {
			infoModel.addElement(" ID: " + campaigns.getID() + " | Start Date: " + campaigns.getStartDate() + " | End Date: " +  campaigns.getEndDate() +
					" | Reduction: " + campaigns.getReduction() + " | Code: " + campaigns.getDiscountCode());
		}
		
	}
	
	public void showAircrafts() {
		infoModel.addElement("| ID | Model | Producer | Capacity | Flight Hours | ");
		currentAirplaneList = controller.getAircrafts();
		for (Airplane airplanes : currentAirplaneList) {
			infoModel.addElement(" ID: " + airplanes.getID() + " | Model: " + airplanes.getModel() + " | Producer: " + airplanes.getProducer() + " | Capacity: " +
					airplanes.getCapacity() + " | Flight Hours: " + airplanes.getFlightHours());
		}
	}

}
