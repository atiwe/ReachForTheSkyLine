package RFTSLgroup.RFTSLid;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class GuiAdmin extends JPanel implements ActionListener {
	
	JLabel jlHeading, jlInfo, jlChoose; 
	JList jlistInfo; 
	JButton btnAddPilot, btnAddAir, btnAddEmp, btnAddDis, btnRemPilot, btnRemAir, btnRemEmp, btnEditPilot;
	JRadioButton rbtnPilot, rbtnAir, rbtnEmp, rbtnDis;
	ButtonGroup btnGroup;
	
	DefaultListModel<String> infoModel; 
	private Controller controller;
	
	
	public GuiAdmin() {
		
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
		rbtnPilot.setSelected(true);
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
	
	void setController(Controller controller) {
		this.controller = controller;
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
			id.showAddPilotDialog();
		} 
		else if (e.getSource() == btnAddEmp) {
			InputDialog id = new InputDialog();
			String[] arr = id.showAddEmployeeDialog();
			controller.addEmployee(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5]);
		} 
		else if (e.getSource() == btnAddAir) {
			InputDialog id = new InputDialog();
			id.showAddAircraftDialog();
		}
		else if (e.getSource() == btnAddDis) {
			InputDialog id = new InputDialog();
			id.showAddDiscountDialog();
		}
		else if (e.getSource() == btnRemAir) {
			infoModel.addElement("Removing Aircraft");
		}
		else if (e.getSource() == btnRemPilot) {
			infoModel.addElement("Removing Pilot");
		}
		else if (e.getSource() == btnRemEmp) {
			infoModel.addElement("Removing Employee");
		}
		else if (e.getSource() == btnEditPilot) {
			InputDialog id = new InputDialog();
			id.showEditPilotDialog();
		}
		else if (rbtnPilot.isSelected()) {
			infoModel.addElement("looking at pilots");
		}
		else if (rbtnEmp.isSelected()) {
			infoModel.addElement("looking at emps");
		}
		else if(rbtnDis.isSelected()) {
			infoModel.addElement("looking at Discounts");
		}
		else if (rbtnAir.isSelected()) {
			infoModel.addElement("looking at aircrafts");
		}
		
		
	}

}
