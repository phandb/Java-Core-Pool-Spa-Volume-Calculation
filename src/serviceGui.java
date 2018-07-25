import java.awt.*;
import java.awt.event.*;

import javax.swing.*;



public class serviceGui extends JPanel
{
	private JButton addButton;
	private JButton exitButton;
	private JTextArea TextArea;
	public JTabbedPane tabbedPane;
	public serviceGui addThisTab;
	
	public serviceGui(String type)
	{	
		this.setName(type);
		this.otherTabsLayout();
		
	}
	
	public void otherTabsLayout()
	{
		//set up Customers Tab
				JPanel panel = new JPanel();
				
				addButton = new JButton("Add "+getName());
				addButton.setMnemonic('A');
				exitButton = new JButton("Exit");
				exitButton.setMnemonic('x');
						
				TextArea = new JTextArea(20,40);
								
				GridBagLayout layout = new GridBagLayout();
				this.setLayout(layout);
				//GridBagConstraints constraints = new GridBagConstraints();
						
				addComponent(panel,TextArea,0,0,1,1/*,GridBagConstraints.WEST,GridBagConstraints.BOTH*/ );
				addComponent(panel,addButton,0,1,1,1/*,GridBagConstraints.WEST*/ );
				addComponent(panel,exitButton,0,2,1,1/*,GridBagConstraints.WEST*/ );
				
				allButtonsHandler handler = new allButtonsHandler(); //object handler of class CalulateHandler
				addButton.addActionListener(handler);  //add calculate button to ActionListener */
				exitButton.addActionListener(handler); //add exit button to ActionListener
		
	}
	
	//Inner class allButtonsHandler to handle all buttons
	private class allButtonsHandler implements ActionListener{
		//when user hit the exit button, system will be closed
		public void actionPerformed(ActionEvent event){
			if (event.getSource() == exitButton){
				System.exit(0);
			}else if (event.getSource() == addButton){
				new serviceAdditionGui(addThisTab,getName());
			
			}
		}//		
	}//end of class allButtonsHandler
	
	//method set location and size of component
		private void addComponent(JPanel panel, Component component, int x, int y, int Width, int Height ){
			//JPanel addComponent = new JPanel();
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = x; // set grid x
			constraints.gridy = y; //set grid y
			constraints.gridwidth = Width;  //set grid width
			constraints.gridheight = Height; //set grid height
			//set internal padding both sides of component
			constraints.ipadx = 5;
			constraints.ipady = 5;
			//set external padding all around component
			constraints.insets = new Insets(5,5,5,5);
			constraints.fill = GridBagConstraints.NONE;
				
			this.add(component,constraints);  //add component
		}//end method add component
		
		

}
