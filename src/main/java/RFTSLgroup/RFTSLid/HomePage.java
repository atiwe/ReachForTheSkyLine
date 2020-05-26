
package RFTSLgroup.RFTSLid;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

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
	
	public HomePage(Controller controller) {
		
		this.controller = controller;
		guiAdmin = new GuiAdmin(controller);
		guiCustomer = new GuiCustomers();
		guiEmployees = new GuiEmployees(controller);
		panelContainer.setLayout(cardLayout);
		
		icon = new ImageIcon("C:/Users/dalvi/ReachForTheSkyLineNew/src/main/java/RFTSLgroup/RFTSLid/airplane.jpg");
		background = new JLabel(icon);
		//background.setIcon(icon);
		
	
		
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
			cardLayout.show(panelContainer, "GuiCustomer");
		} 
		else if (e.getSource() == menuItemEmp) {
			cardLayout.show(panelContainer, "GuiEmployee");
		}
		else if (e.getSource() == btnAdmin) {
			cardLayout.show(panelContainer, "GuiAdmin");
		}
		else if (e.getSource() == btnCust) {
			cardLayout.show(panelContainer, "GuiCustomer");
		}
		else if (e.getSource() == btnEmp) {
			cardLayout.show(panelContainer, "GuiEmployee");
		}
	}
	

}

