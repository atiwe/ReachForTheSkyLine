
package RFTSLgroup.RFTSLid;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import Domain.Customer;
import Domain.User;


public class HomePage implements ActionListener {
	
	JFrame frame = new JFrame("Reach For The Sky Line");
	JPanel panelContainer = new JPanel();
	CardLayout cardLayout = new CardLayout();
	
	JLabel background;
	ImageIcon icon;
	
	JMenuBar menuBar;
	JMenu menu;
	JMenuItem menuItemAdmin, menuItemCust, menuItemEmp;
	
	JLabel jlHeading;
	JButton btnAdmin, btnCust, btnEmp;
	
	JPanel startPage;
	GuiAdmin guiAdmin;
	GuiCustomers guiCustomer;
	GuiEmployees guiEmployees;
	private Controller controller;
	private User currentUser;
	
	List<Customer> existingCustomers;
	
	public HomePage(Controller controller) {
		
		this.controller = controller;
		guiAdmin = new GuiAdmin(controller);
		guiCustomer = new GuiCustomers(controller);
		guiEmployees = new GuiEmployees(controller);
		panelContainer.setLayout(cardLayout);
		
		icon = new ImageIcon("airplane.jpg");
		background = new JLabel(icon);
		
		
		//Start page panel
		startPage = new JPanel();
		startPage.setBackground(Color.CYAN);
		startPage.setLayout(new GridLayout(3, 1));
		JPanel middlePanel = new JPanel(new GridLayout(3,1));
		
		
		jlHeading = new JLabel("Choose your role", SwingConstants.CENTER);
		jlHeading.setFont(new Font("Serif", Font.BOLD, 24));
		startPage.add(jlHeading);
		
		btnAdmin = new JButton("Administrator");
		btnCust = new JButton("Customer");
		btnEmp = new JButton("Employee");
		
		btnAdmin.addActionListener(this);
		btnCust.addActionListener(this);
		btnEmp.addActionListener(this);
		
		startPage.add(middlePanel);
		middlePanel.add(btnAdmin);
		middlePanel.add(btnCust);
		middlePanel.add(btnEmp);
		startPage.add(background);
		
			
		
		//Different views for different users profiles
		panelContainer.add(startPage, "Start");
		panelContainer.add(guiAdmin, "GuiAdmin");
		panelContainer.add(guiCustomer, "GuiCustomer");
		panelContainer.add(guiEmployees, "GuiEmployee");
		
		cardLayout.show(panelContainer, "Start");
		
		//Menu bar
		menuBar = new JMenuBar();
		menu = new JMenu("User menu");
		menuBar.add(menu);
		
		//Menu Items
		menuItemAdmin = new JMenuItem("Administrator View");
		menu.add(menuItemAdmin);
		
		menuItemCust = new JMenuItem("Customer View");
		menu.add(menuItemCust);
		
		menuItemEmp = new JMenuItem("Employee View");
		menu.add(menuItemEmp);
		
		//Menu Item Listeners
		menuItemAdmin.addActionListener(this);
		menuItemCust.addActionListener(this);
		menuItemEmp.addActionListener(this);
		
		//Main frame
	
		frame.add(panelContainer);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItemAdmin) {
			cardLayout.show(panelContainer, "GuiAdmin");
		} 
		else if (e.getSource() == menuItemCust) {
			newOrExistingCustomer();
			if(customerLoginWindow()) {
			cardLayout.show(panelContainer, "GuiCustomer");
			}
			guiCustomer.updateTexts();
		} 
		else if (e.getSource() == menuItemEmp) {
			employeeLoginWindow();
			cardLayout.show(panelContainer, "GuiEmployee");
		}
		else if (e.getSource() == btnAdmin) {
			cardLayout.show(panelContainer, "GuiAdmin");
		}
		else if (e.getSource() == btnCust) {
			newOrExistingCustomer();
			if(customerLoginWindow()) {
			cardLayout.show(panelContainer, "GuiCustomer");
			}
		}
		else if (e.getSource() == btnEmp) {
			if(employeeLoginWindow())
				cardLayout.show(panelContainer, "GuiEmployee");
		}
	}
	
	public void newOrExistingCustomer() {
		InputDialog id = new InputDialog();

		boolean result = id.showLoginOptions();
		if(result == true ) {
			customerLoginWindow();
		} else {
			createNewUser();
		}
	}

	public boolean customerLoginWindow() {
		InputDialog id = new InputDialog();
		String[] arr = id.showCustomerLoginDialog();
		String username = arr[0];
		String password = arr[1];
		return checkLoginCustomer(username, password);

	}

	public void createNewUser() {
		InputDialog id = new InputDialog();
		String[] arr = id.showCreateCustomerDialog();
		
		if (id.confirmationDialog(arr)){
			controller.addCustomer(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], arr[6]);
			
			JOptionPane.showMessageDialog(null, "Customer account added");
		}
		else {
			JOptionPane.showMessageDialog(null, "Something went wrong! no account added");
		}
	}

	public boolean employeeLoginWindow() {
		InputDialog id = new InputDialog();
		String[] arr = id.showEmployeeLoginDialog();
		String username = arr[0];
		String password = arr[1];
		
		return checkLoginEmployee(username, password);
	}

	public boolean checkLoginEmployee(String username, String password) {

			User loginUser = controller.getUser(username);
			if(loginUser!=null) {
				if(loginUser.getPassword().equals(password)) {
					if(loginUser.getType().equals("Employee")) {
						currentUser = loginUser;
						return true;
					}else {
						JOptionPane.showMessageDialog(null, "Not a employee account!");
						return false;
					}
				}else {
					JOptionPane.showMessageDialog(null, "Wrong password!");
					return false;
				}
			}else {
				JOptionPane.showMessageDialog(null, "Wrong username!");
				return false;
			}
	}
	
	public boolean checkLoginCustomer(String username, String password) {
		
			User loginUser = controller.getUser(username);
			if(loginUser!=null) {
				if(loginUser.getPassword().equals(password)) {
					if(loginUser.getType().equals("Customer")) {
						currentUser = loginUser;
						return true;
					}else {
						JOptionPane.showMessageDialog(null, "Not a customer account!");
						return false;
					}
				}else {
					JOptionPane.showMessageDialog(null, "Wrong password!");
					return false;
				}
			}else {
				JOptionPane.showMessageDialog(null, "Wrong username!");
				return false;
			}
	}
}

