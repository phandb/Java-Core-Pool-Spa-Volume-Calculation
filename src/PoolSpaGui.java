import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.DecimalFormat;

import javax.swing.*;

//@SuppressWarnings("serial")
public class PoolSpaGui extends JPanel //implements ActionListener
{
	
	public JTabbedPane tabbedPane;
	public JPanel Panel;
		
	private JTextField lengthField;
	private JTextField widthField;
	private JTextField depthField;
	private JTextField volumeField;
	private JTextField gunniteField;
	
	private JRadioButton Us = new JRadioButton();
	private JRadioButton Metric = new JRadioButton();
	public ButtonGroup RadioGroup = new ButtonGroup();
	
	/*private JLabel label;
	private JLabel model;
	private JLabel shape;
	private JLabel unit;*/
	//public JLabel volume;
	public JLabel volumeUnit;
	public JLabel volumeMetricUnit;
	//private JLabel length;
	////private JLabel width;
	//private JLabel depth;
	//private JLabel gunnite;
	public JLabel gunniteUnit;
	public JLabel gunniteMetricUnit;
	//US unit labels	
	public JLabel lengthUnit;
	public JLabel widthUnit;
	public JLabel depthUnit;
	//Metric unit labels
	public JLabel lengthMetricUnit;
	public JLabel widthMetricUnit;
	public JLabel depthMetricUnit;		
	public JComboBox modelBox;
	public JComboBox shapeBox;
	
	private JButton calButton;
	private JButton exitButton;
	private JButton clrButton;
	
		
	//Conversion constants to gallons
	private static final Double GALLONS =7.481;
	private static final Double LITERS = 1000.00;
	//model of spa and pool
	private static final String[] modelName = {"Custom", "Pacific","Oasis", "Atlantic", "Monte Carlo"};
	//shape of spa and pool
	private static final String[] shapeName = {"Rectangular", "Round", "Oval"};
	
	//constructor
	public PoolSpaGui(String type)
	{	
		this.setName(type);
		this.PoolSpaGuiLayout();
	}
	
	private void PoolSpaGuiLayout()
	{
		Panel = new JPanel();
		//Use GridBagLayout Manager		
		GridBagLayout Layout = new GridBagLayout();
		this.setLayout(Layout);
		//GridBagConstraints constraints = new GridBagConstraints();

		//define buttons for spa tab				
				calButton = new JButton("Calculate");
				calButton.setMnemonic('C');
				exitButton = new JButton("Exit");
				exitButton.setMnemonic('x');
				clrButton = new JButton("Clear");
				clrButton.setMnemonic('l');
				//define labels for spa tab
				JLabel label = new JLabel("Enter data for " + getName().toLowerCase() + ":");
				JLabel model = new JLabel("Select "+getName().toLowerCase()+" model:");
				JLabel shape = new JLabel("Select "+getName().toLowerCase()+" shape:");
				JLabel unit = new JLabel("Select measurement units:");
				JLabel volume = new JLabel("Volume Required: ");
				JLabel gunnite = new JLabel("Gunnite Required:");
				
				JLabel length = new JLabel("Enter length:");
				lengthUnit = new JLabel("ft.");
				JLabel width = new JLabel("Enter width:");
				widthUnit = new JLabel("ft.");
				
				JLabel depth = new JLabel("Enter average depth:");
			    depthUnit = new JLabel("ft.");
				volumeUnit = new JLabel("Gallons.");
				gunniteUnit = new JLabel("Cubic Feet");
				lengthMetricUnit = new JLabel("m.");
				widthMetricUnit = new JLabel("m.");
				depthMetricUnit = new JLabel("m.");
				volumeMetricUnit = new JLabel("Liters.");
				gunniteMetricUnit = new JLabel("Cubic Meter");
				//define fields for spa tab
				lengthField = new JTextField(15);
				widthField = new JTextField(15);
				
				depthField = new JTextField(15);
				volumeField = new JTextField(15);
				gunniteField = new JTextField(15);
				
				//define radio buttons 
				Us = new JRadioButton("US",true);
				Metric = new JRadioButton("Metric",false);
				RadioGroup = new ButtonGroup();
				RadioGroup.add(Us);
				RadioGroup.add(Metric);
				//define combo box
				modelBox = new JComboBox(modelName);
				shapeBox = new JComboBox(shapeName);
		
				
		//Add all spa components to the tab
		
		addComponent(Panel,label,1,0,1,1,GridBagConstraints.WEST );
		addComponent(Panel,model,0,1,1,1,GridBagConstraints.WEST );
		//constraints.fill = GridBagConstraints.HORIZONTAL;
		addComponent(Panel,modelBox,1,1,3,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
		addComponent(Panel,shape,0,2,1,1,GridBagConstraints.WEST );
		addComponent(Panel,shapeBox,1,2,3,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		
		addComponent(Panel,unit,0,3,1,1,GridBagConstraints.WEST );
		addComponent(Panel,Us,1,3,1,1,GridBagConstraints.WEST );
		addComponent(Panel,Metric,2,3,1,1,GridBagConstraints.WEST );
		
		addComponent(Panel,length,0,4,1,1,GridBagConstraints.WEST );
		addComponent(Panel,lengthField,1,4,3,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		addComponent(Panel,lengthUnit,4,4,1,1,GridBagConstraints.WEST );
		
		addComponent(Panel,width,0,5,1,1,GridBagConstraints.WEST );
		addComponent(Panel,widthField,1,5,3,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		addComponent(Panel,widthUnit,4,5,1,1,GridBagConstraints.WEST );
				
		addComponent(Panel,depth,0,7,1,1,GridBagConstraints.WEST );
		addComponent(Panel,depthField,1,7,3,1,GridBagConstraints.WEST,GridBagConstraints.HORIZONTAL );
		addComponent(Panel,depthUnit,4,7,1,1,GridBagConstraints.WEST );
		
		addComponent(Panel,volume,0,8,1,1,GridBagConstraints.WEST );
		addComponent(Panel,volumeField,1,8,2,1,GridBagConstraints.WEST );
		addComponent(Panel,volumeUnit,3,8,1,1,GridBagConstraints.WEST );
		
		addComponent(Panel,gunnite,0,9,1,1,GridBagConstraints.WEST );
		addComponent(Panel,gunniteField,1,9,2,1,GridBagConstraints.WEST );
		addComponent(Panel,gunniteUnit,3,9,1,1,GridBagConstraints.WEST );
				
		addComponent(Panel,calButton,1,10,1,1,GridBagConstraints.WEST );
		addComponent(Panel,clrButton,2,10,1,1,GridBagConstraints.WEST );
				
		addComponent(Panel,exitButton,1,11,1,1,GridBagConstraints.WEST );
					
		//Event handler for buttons
		
		AllButtonsHandler buttonsHandler = new AllButtonsHandler(); //create a handler for all buttons
		exitButton.addActionListener(buttonsHandler); //add exit button to ActionListener
		calButton.addActionListener(buttonsHandler);  //add calculate button to ActionListener 
		clrButton.addActionListener(buttonsHandler);  //add clear button to ActionListener
		
		RadioButtonHandler radioButtons = new RadioButtonHandler();
		Us.addItemListener(radioButtons);
		Metric.addItemListener(radioButtons);
		
		
		
	}//end of method PoolSpaGuiLayout()
	//inner class handler for radio button
	private class RadioButtonHandler implements ItemListener{
		public void itemStateChanged(ItemEvent event){
			
			Metric.addItemListener( 
					new ItemListener(){
						public void itemStateChanged(ItemEvent event)
						{
							
							if (event.getStateChange() == ItemEvent.SELECTED)
							
							{
								lengthUnit.setVisible(false);
								widthUnit.setVisible(false);
								depthUnit.setVisible(false);
								volumeUnit.setVisible(false);
								gunniteUnit.setVisible(false);
								
								addComponent(Panel,lengthMetricUnit,4,4,1,1,GridBagConstraints.WEST );
								addComponent(Panel,widthMetricUnit,4,5,1,1,GridBagConstraints.WEST );
								addComponent(Panel,depthMetricUnit,4,7,1,1,GridBagConstraints.WEST );
								addComponent(Panel,volumeMetricUnit,3,8,1,1,GridBagConstraints.WEST );
								addComponent(Panel,gunniteMetricUnit,3,9,1,1,GridBagConstraints.WEST );
							}else if (event.getStateChange() == ItemEvent.DESELECTED){
								//spaUs.setVisible(true);
								lengthUnit.setVisible(true);
								widthUnit.setVisible(true);
								depthUnit.setVisible(true);
								volumeUnit.setVisible(true);
								gunniteUnit.setVisible(true);
								addComponent(Panel,lengthUnit,4,4,1,1,GridBagConstraints.WEST );
								addComponent(Panel,widthUnit,4,5,1,1,GridBagConstraints.WEST );
								addComponent(Panel,depthUnit,4,7,1,1,GridBagConstraints.WEST );
								addComponent(Panel,volumeUnit,3,8,1,1,GridBagConstraints.WEST );
								addComponent(Panel,gunniteUnit,3,9,1,1,GridBagConstraints.WEST );
							}
						}
					}
					);
			Us.addItemListener( 
					new ItemListener(){
						public void itemStateChanged(ItemEvent event)
						{
							
							if (event.getStateChange() == ItemEvent.SELECTED)
							
							{
								lengthMetricUnit.setVisible(false);
								widthMetricUnit.setVisible(false);
								depthMetricUnit.setVisible(false);
								volumeMetricUnit.setVisible(false);
								gunniteMetricUnit.setVisible(false);
								addComponent(Panel,lengthUnit,4,4,1,1,GridBagConstraints.WEST );
								addComponent(Panel,widthUnit,4,5,1,1,GridBagConstraints.WEST );
								addComponent(Panel,depthUnit,4,7,1,1,GridBagConstraints.WEST );
								addComponent(Panel,volumeUnit,3,8,1,1,GridBagConstraints.WEST );
								addComponent(Panel,gunniteUnit,3,9,1,1,GridBagConstraints.WEST );
							}else if (event.getStateChange() == ItemEvent.DESELECTED){
								lengthMetricUnit.setVisible(true);
								widthMetricUnit.setVisible(true);
								depthMetricUnit.setVisible(true);
								volumeMetricUnit.setVisible(true);
								gunniteMetricUnit.setVisible(true);
								addComponent(Panel,lengthMetricUnit,4,4,1,1,GridBagConstraints.WEST );
								addComponent(Panel,widthMetricUnit,4,5,1,1,GridBagConstraints.WEST );
								addComponent(Panel,depthMetricUnit,4,7,1,1,GridBagConstraints.WEST );
								addComponent(Panel,volumeMetricUnit,3,8,1,1,GridBagConstraints.WEST );
								addComponent(Panel,gunniteMetricUnit,3,9,1,1,GridBagConstraints.WEST );
								
							}
						}
					}
					);
			
		}
	}//end of inner class radio button handler
	
	
	//Inner class to handle all buttons
		private class AllButtonsHandler implements ActionListener{
			//when user hit the exit button, system will be closed
			public void actionPerformed(ActionEvent event){
				if (event.getSource() == exitButton){
					System.exit(0);
				}else if (event.getSource() == calButton){
					//try...catch block error
					try{
						double lNum = Double.parseDouble(lengthField.getText()); //get the first number
						double wNum = Double.parseDouble(widthField.getText()); //get the second 
						double dNum = Double.parseDouble(depthField.getText());
						//double rNum = Double.parseDouble(spaRadiusField.getText());
						double vNum;
						DecimalFormat format = new DecimalFormat("#,###.##");
						// if us button is selected, calculate volume and output result to answer field
						if (Us.isSelected()){
							if ((shapeBox.getSelectedItem().equals("Rectangular" )) || (shapeBox.getSelectedItem().equals("Oval" )))
									{
										vNum = Math.round(lNum*wNum*dNum);
										volumeField.setText(String.valueOf(format.format(vNum*GALLONS)));
										gunniteField.setText(String.valueOf(format.format(vNum)));
									}
							else if (shapeBox.getSelectedItem().equals("Round"))
								{	
									if (lNum == wNum){
										vNum = Math.round(Math.PI*Math.pow((lNum/2),2)*dNum);
										volumeField.setText(String.valueOf(format.format(vNum*GALLONS)));
										gunniteField.setText(String.valueOf(format.format(vNum)));
									}else{
										JOptionPane.showMessageDialog(null,"Length and width have to be equaled for round shape");
									}//end else
								}//end else if
						}
							
						// if metric button is selected, calculate volume and output result to answer field	
						else if (Metric.isSelected()){
							if ((shapeBox.getSelectedItem().equals("Rectangular" )) || (shapeBox.getSelectedItem().equals("Oval" )))
							{
								vNum = Math.round(lNum*wNum*dNum);
								volumeField.setText(String.valueOf(format.format(vNum*LITERS)));
								gunniteField.setText(String.valueOf(format.format(vNum)));
							}
							else if (shapeBox.getSelectedItem().equals("Round"))
							{	
								if (lNum == wNum){
									vNum = Math.round(Math.PI*Math.pow((lNum/2),2)*dNum);
									volumeField.setText(String.valueOf(format.format(vNum*LITERS)));
									gunniteField.setText(String.valueOf(format.format(vNum)));
								}else{
									JOptionPane.showMessageDialog(null,"Length and width have to be equaled for round shape");
								}//end else
							}
						}
						
						//open file to write to, name SpaData.txt
						
						FileOutputStream saveFile = new FileOutputStream(getName()+"Data.txt",true);
						
						//create an ObjectOutputStream to put objects into save file
						BufferedWriter save = new BufferedWriter(new OutputStreamWriter(saveFile,"UTF8"));
						
						//now do the save
						save.write("---------------------------------------------------------------------");
						save.newLine();
						save.write("-----------------"+getName()+" Data-------------" );
						save.newLine();
						save.write("Selected "+getName()+" Model:  " + modelBox.getSelectedItem());
						save.newLine();
						save.write("Selected "+getName()+"  Shape:  " + shapeBox.getSelectedItem());
						save.newLine();
						if (Us.isSelected()){
							save.write(getName()+ " Dimension Unit in Feet");
						}else{
							save.write(getName()+" Dimension Unit in Meter");
						}
							
						save.newLine();
						save.write(getName()+" Length:  " + lengthField.getText());
						save.newLine();
						save.write(getName()+" Width:  " + widthField.getText() );
						save.newLine();
						save.write(getName()+" Depth:  " + depthField.getText());
						save.newLine();
						if (Us.isSelected()){
							save.write(getName()+" Volume Required:  " + volumeField.getText()+ "  "+volumeUnit.getText());
							save.newLine();
							save.write(getName()+" Gunnite Required:  " + gunniteField.getText()+ "  "+gunniteUnit.getText());
							save.newLine();
							
						}else{
							save.write(getName()+" Volume Required:  " + volumeField.getText()+ "  "+volumeMetricUnit.getText());
							save.newLine();
							save.write(getName()+" Gunnite Required:  " + gunniteField.getText()+ "  "+gunniteMetricUnit.getText());
							save.newLine();
							
						}
						
						save.write("---------------------------------------------------------------------------");
						
						//close the file.
						save.close();  //also close saveFile
										
					}//end try
					//print out message if an error is catched
					catch(Exception e){
						JOptionPane.showMessageDialog(null,"Please enter a valid number");
						
					}//end catch
					//write data to Volume.txt
					
				}else if (event.getSource() == clrButton){
					lengthField.setText(null);
					widthField.setText(null);
					depthField.setText(null);
					volumeField.setText(null);
					gunniteField.setText(null);
					
				}
				
			}//		
		}//end of class ExitHandler
	
	//method set location and size of component
	
	
	//overload addComponent method with one constraints parameter
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
		
}//end of class PoolSpaGUI

                                                          
