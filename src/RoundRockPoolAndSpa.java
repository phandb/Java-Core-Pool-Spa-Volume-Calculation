import java.awt.*;

import javax.swing.*;

public class RoundRockPoolAndSpa extends JFrame
{


	private static JTabbedPane tabbedPane = new JTabbedPane(); //create tab panel
	
	
	public RoundRockPoolAndSpa(){
		super("Pool and Spa");
		setSize(500,500);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		//add components here
		//add each panel to tabbedPane
		tabbedPane.addTab("Spa", new PoolSpaGui("Spa"));
		tabbedPane.addTab("Pool",new PoolSpaGui("Pool"));                                         
		tabbedPane.addTab("Vendors", new serviceGui("Vendor"));
		tabbedPane.addTab("Customers", new serviceGui("Customer"));
		tabbedPane.addTab("Contractors", new serviceGui("Contractor"));
		//add JTabbedPane to Frame
		topPanel.add(tabbedPane,BorderLayout.CENTER);
		
	}
	
	public static void main(String[] args) {
		RoundRockPoolAndSpa project= new RoundRockPoolAndSpa();
		project.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		project.setVisible(true);
		
	}


}
