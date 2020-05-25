package RFTSLgroup.RFTSLid;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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
	
	public GuiCustomers() {
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(1000, 700));
		JPanel centrePanel = new JPanel();
		centrePanel.setLayout(new GridLayout(10,0));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(1,1));
		JPanel bottomPanel2 = new JPanel();
		bottomPanel2.setLayout(new GridLayout(1,1));
		
		jlemp = new JLabel("Customers", SwingConstants.CENTER);
		jlemp.setFont(new Font("Serif", Font.BOLD, 24));
		jlsf = new JLabel("Scheduled flights", SwingConstants.CENTER);
		jlfl = new JLabel("Flight lines", SwingConstants.CENTER);
		
		
		modelsf = new DefaultListModel<String>();
		modelfl = new DefaultListModel<String>();
		jlistsf = new JList(modelsf);
		jlistfl = new JList(modelfl);
		
		btnBookFlight = new JButton("Book Flight");
		btnCancelFlight = new JButton("Cancel Flight");
	
		bottomPanel.add(btnBookFlight);

		bottomPanel2.add(btnCancelFlight);
	
		
		String hej = "sdfsdf";
		String då = "asdas";
		modelsf.addElement( hej);
		modelsf.addElement(då);
		modelsf.addElement( hej);
		modelsf.addElement(då);
		modelsf.addElement( hej);
		modelsf.addElement(då);
		modelfl.addElement( hej);
		modelfl.addElement(då);
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
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame("Databas");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GuiCustomers ui = new GuiCustomers();
		frame.add(ui);
		frame.pack();
		frame.setVisible(true);
	}
}
	

