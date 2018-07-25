import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


import javax.swing.*;


public class serviceAdditionGui extends JFrame {
	public serviceGui customGui;
	public JPanel subPanel;
	
	private JLabel fName;
	private JLabel lName;
	private JLabel cName;
	private JLabel Address1;
	private JLabel Address2;
	private JLabel City;
	private JLabel State;
	private JLabel Zip;
	private JLabel Phone;
	private JLabel Contact;
	private JLabel Product;
	private JTextField fNameField;
	private JTextField lNameField;
	private JTextField cNameField;
	private JTextField AddressField1;
	private JTextField AddressField2;
	private JTextField CityField;
	private JComboBox StateField;
	private JTextField ZipField;
	private JTextField PhoneField;
	private JTextField ContactField;
	private JTextField ProductField;
	
	public JButton addNew;
	public JButton cancel;
	
	//constructor
	public serviceAdditionGui(serviceGui customGui, String type){
		super();
		this.customGui = customGui;
		this.setName(type);
		this.setTitle("Enter New "+type+" Data");
		this.setVisible(true);
		this.setSize(850,300);
		this.setLocation(500, 500);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		subPanel = new JPanel();
		this.add(subPanel);
		this.buildGUI();
			
	}//end of constructor
	//GUI Layout for Adding customer, vendor, and contractor
	private void buildGUI(){
		subPanel = new JPanel();
		GridBagLayout subLayout = new GridBagLayout();
		this.setLayout(subLayout);
		
		String[] stateList = { "AL", "AK", "AZ", "AR", "CA", "CO", "CT", 
				"DE", "FL", "GA", "HI", "ID", "IL", "IN", "IA", "KS", 
				"KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", 
				"MT", "NE",	"NV", "NH", "NJ", "NM", "NY", "NC", "ND", 
				"OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", 
				"UT", "VT", "VA", "WA",	"WV", "WI", "WY" };

		if (getName().equals("Customer")){
			
			fName = new JLabel("First Name: ");
			fNameField = new JTextField(10);
			addComponent(subPanel,fName,0,1,1,1,GridBagConstraints.WEST );
			addComponent(subPanel,fNameField,1,1,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
			
			lName = new JLabel("Last Name: ");
			lNameField = new JTextField(10);
			addComponent(subPanel,lName,2,1,1,1,GridBagConstraints.WEST );
			addComponent(subPanel,lNameField,3,1,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
			
		}else if (getName().equals("Vendor") || getName().equals("Contractor")){
			cName = new JLabel("Company Name:");
			cNameField = new JTextField(20);
			addComponent(subPanel,cName,0,1,1,1,GridBagConstraints.WEST );
			addComponent(subPanel,cNameField,1,1,20,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		}
		
		Address1 = new JLabel("Street Address:");
		AddressField1 = new JTextField(30);
		addComponent(subPanel,Address1,0,2,1,1,GridBagConstraints.WEST );
		addComponent(subPanel,AddressField1,1,2,20,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
		Address2 = new JLabel("Address Line 2:");
		AddressField2 = new JTextField(30);
		addComponent(subPanel,Address2,0,3,1,1,GridBagConstraints.WEST );
		addComponent(subPanel,AddressField2,1,3,20,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
		City = new JLabel("City:");
		CityField = new JTextField(15);
		addComponent(subPanel,City,0,4,1,1,GridBagConstraints.EAST );
		addComponent(subPanel,CityField,1,4,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL);
		
		State = new JLabel("State:");
		StateField = new JComboBox(stateList);
		addComponent(subPanel,State,2,4,1,1,GridBagConstraints.EAST );
		addComponent(subPanel,StateField,3,4,1,1,GridBagConstraints.WEST/*,GridBagConstraints.HORIZONTAL */);
		
		Zip = new JLabel("Zip:");
		ZipField = new JTextField(10);
		addComponent(subPanel,Zip,6,4,1,1,GridBagConstraints.EAST );
		addComponent(subPanel,ZipField,7,4,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
		Phone = new JLabel("Phone:");
		PhoneField = new JTextField(15);
		addComponent(subPanel,Phone,8,4,1,1,GridBagConstraints.EAST );
		addComponent(subPanel,PhoneField,9,4,1,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
		if (getName() == "Vendor" || getName() == "Contractor"){
			Contact = new JLabel("Contact Person:");
			ContactField = new JTextField(30);
			Product = new JLabel("Products:");
			ProductField = new JTextField(30);
		
			addComponent(subPanel,Contact,0,5,1,1,GridBagConstraints.WEST );
			addComponent(subPanel,ContactField,1,5,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
			addComponent(subPanel,Product,0,6,1,1,GridBagConstraints.WEST );
			addComponent(subPanel,ProductField,1,6,10,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		}
		
		addNew = new JButton("Add New "+getName());
		addNew.setMnemonic('A');
		cancel = new JButton("Cancel");
		cancel.setMnemonic('C');
		
		addComponent(subPanel,addNew,1,7,1,1 );
		addComponent(subPanel,cancel,2,7,1,1,GridBagConstraints.WEST );
		
		AllButtonsHandler buttonsHandler = new AllButtonsHandler(); //create a handler for all buttons
		addNew.addActionListener(buttonsHandler); //add exit button to ActionListener
		cancel.addActionListener(buttonsHandler);  //add calculate button to ActionListener 
		
	}//end of the layout
	
	//Inner class to handle all buttons
			private class AllButtonsHandler implements ActionListener{
				//when user hit the exit button, system will be closed
				public void actionPerformed(ActionEvent event){
					if (event.getSource() == cancel){
						 dispose();
					}else if (event.getSource() == addNew){
						//try...catch block error
						try{
							//open file to write to, name Vendor.txt
							FileOutputStream saveToFile = new FileOutputStream("New"+getName()+".txt",true);
							//create an ObjectOutputStream to put objects into save file
							BufferedWriter save = new BufferedWriter(new OutputStreamWriter(saveToFile,"UTF8"));
							
							//now do the save
							save.write("---------------------------------------------------------------------");
							save.newLine();
							save.write("-----------------New "+getName()+"-------------" );
							save.newLine();
							if (getName().equals("Customer")){
								save.write("First Name:  " + fNameField.getText());
								save.newLine();
								save.write("Last Name:  " + lNameField.getText());
															
							}else if( (getName().equals("Vendor")) || (getName().equals("Contractor"))){
								save.write("Company Name:  " + cNameField.getText());
							}
							
							save.newLine();
							save.write("Address:  " + AddressField1.getText());
							save.newLine();
							save.write("Address Line 2:  " + AddressField2.getText());
							save.newLine();
							save.write("City:  " + CityField.getText());
							save.newLine();
							save.write("State:  " + StateField.getSelectedItem() );
							save.newLine();
							save.write("Zip:  " + ZipField.getText());
							save.newLine();
							save.write("Phone:  " + PhoneField.getText());
							save.newLine();
							if( (getName().equals("Vendor")) || (getName().equals("Contractor"))){
								save.write("Contact Person:  " + ContactField.getText());
								save.newLine();
								save.write("Product:  " + ProductField.getText());
							}
							
							save.newLine();
							save.write("---------------------------------------------------------------------------");
							
							//close the file.
							save.close();  //also close saveFile
							if (getName().equals("Customer")){
								fNameField.setText(null);
								lNameField.setText(null);
															
							}else if( (getName().equals("Vendor")) || (getName().equals("Contractor"))){
								cNameField.setText(null);
							}
							
							
							AddressField1.setText(null);
							AddressField2.setText(null);
							CityField.setText(null);
							//StateField.setText(null);
							ZipField.setText(null);
							PhoneField.setText(null);
							ContactField.setText(null);
							ProductField.setText(null);
							
						}
						catch(Exception e){
							e.printStackTrace();  //if there was an error, print the info
						}
					
				}//end add button		
			}//end of event
	}//end of inner class
	
	//method set location and size of component
	
	
		//overload addComponent method with one constraints parameter
	private void addComponent(JPanel panel, Component component, int x, int y, int Width, int Height){
		
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = x; // set grid x
		constraints.gridy = y; //set grid y
		constraints.gridwidth = Width;  //set grid width
		constraints.gridheight = Height; //set grid height
		//set internal padding both sides of component
		constraints.ipadx = 10;
		constraints.ipady = 10;
		//set external padding all around component
		constraints.insets = new Insets(2,2,2,2);
		//constraints.anchor = align;
		constraints.fill = GridBagConstraints.NONE;
			
		this.add(component,constraints);  //add component
	}//end method add component
		private void addComponent(JPanel panel, Component component, int x, int y, int Width, int Height, int align){
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = x; // set grid x
			constraints.gridy = y; //set grid y
			constraints.gridwidth = Width;  //set grid width
			constraints.gridheight = Height; //set grid height
			//set internal padding both sides of component
			constraints.ipadx = 10;
			constraints.ipady = 10;
			//set external padding all around component
			constraints.insets = new Insets(2,2,2,2);
			constraints.anchor = align;
			constraints.fill = GridBagConstraints.NONE;
				
			this.add(component,constraints);  //add component
		}//end method add component
		
		//overload method addComponent with 2 constraints parameters
		private void addComponent(JPanel panel, Component component, int x, int y, int Width, int Height, int align, int fill){
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
			constraints.insets = new Insets(1,1,1,1);
			constraints.anchor = align;
			constraints.fill = fill;                                                                                     
				
			this.add(component,constraints);  //add component
		}//end method add component

}
