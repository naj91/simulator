/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.program.GraphicsProgram;

/**
 *This class simulates the Praaduct.
 */
public class InterfaceBoard extends GraphicsProgram implements Constants
{
    private LEDBlock greenLEDs;
    private LEDBlock redLEDs;
    private KeyBoard keyboard;
    private Elevator elevator;
    private SevenSegmentBlock sevsegBlock;
    private Motor motor;
    
    private JButton Out_Led_Button;
    private JButton Out_Led_Button1;
    private JButton Out_Led_Button2;
    private JButton keyboardSwitch;
	
	private JButton motorSwitchOnClockwise;
	private JButton motorSwitchOnAntiClockwise;
    private JButton motorSwitchOff;
    
     private JTextField segmentBlock1;
     private JTextField segmentBlock2;
     private JTextField segmentBlock3;
     private JTextField segmentBlock4; 
     
     private GLabel  label ;
    public static void main(String args[])
    {
        new InterfaceBoard().start(args);
    }
    
    public void init()
    {
    	//initTextBoxes();
        initButtons();
        addMouseListeners();
        addActionListeners();
        
    }
   
    private void initTextBoxes() {
    	segmentBlock1 = new JTextField(1);
    	segmentBlock2 =  new JTextField(1);
    	segmentBlock3 = new JTextField(1);
    	segmentBlock4 = new JTextField(1);
    	
    	
    	add(segmentBlock1, NORTH);
    	add(segmentBlock2, NORTH);
    	add(segmentBlock3, NORTH);
    	add(segmentBlock4, NORTH);	
	}

	public void run()
    {
        initLEDBlocks();
        initKeyBoard();
        initElevator();
        initSevenSegment();
        initMotor();        
        
        /*for(int i=0; i<500; i++)
        {
            motor.rotateMotor(true);
            pause(20);
        }*/
    }   
    
    private void initSevenSegment() {
		sevsegBlock = new SevenSegmentBlock(4);
		add(sevsegBlock,10,10);
		
	}

    public void actionPerformed(ActionEvent e)
    {
        println("actionPerformed");
        Object obj = e.getSource();
        println(obj.toString());
                
        //Updates the Green Leds with the code present on the Virtual switch of Red LEDs.
        if(obj == Out_Led_Button1)
        {
            greenLEDs.update(redLEDs.getBinaryCodeString(), true);            
        }
        
        //Reads the Physical Switch (RED LEDs) and outputs the code on the GREEN LEDs.
        if(obj == Out_Led_Button2)
        {
            int aByte = ParPortCommunicator.inportB();
            String pattern = Integer.toBinaryString(aByte);
            System.out.println("Pattern: " + pattern);
            if(pattern.length() > 8)
            {
            	pattern = pattern.substring(pattern.length()-8);
            }
            
            redLEDs.update(pattern, false);
            
            greenLEDs.update(pattern, true);
            ParPortCommunicator.outport(P378, aByte);
        }
        
        //Reads the Keyboard switch and updates the graphical keyboard
        if(obj == keyboardSwitch)
        {
            
        	Action activateKeyboard = new AbstractAction() 				
        	{
        		@Override
        		public void actionPerformed(ActionEvent arg0) 
        		{             				
        			keyboard.clearAll();       
        			Point p = ParPortCommunicator.readKeyboard();
                  if (p!=null)
                  {
                      System.out.println(p.row + "  " + p.col);
                      keyboard.Press(p.row, p.col);
                  }	
        		}
        	};
        	
        	Timer timer = new Timer(100, activateKeyboard);
        	timer.setInitialDelay(100);
        	//timer.setRepeats(false);
        	
        	timer.start(); 
        }
        
      //Starts the virtual motor 
        if(obj == motorSwitchOnClockwise)
        {       	
        	Action rotateMotor = new AbstractAction() 				
        	{
        		@Override
        		public void actionPerformed(ActionEvent arg0) 
        		{             				
        			motor.rotateMotor(true);        			                    
        		}
        	};
        	
        	Timer timer = new Timer(20, rotateMotor);
        	timer.setInitialDelay(20);
        	//timer.setRepeats(false);
        	
        	for(int i=0; i<100; i++)
            {
        		timer.start();
            }
        }
      //Starts the virtual motor 
        if(obj == motorSwitchOnAntiClockwise)
        {       	
        	Action rotateMotor = new AbstractAction() 				
        	{
        		@Override
        		public void actionPerformed(ActionEvent arg0) 
        		{             				
        			motor.rotateMotor(false);        			                    
        		}
        	};
        	
        	Timer timer = new Timer(20, rotateMotor);
        	timer.setInitialDelay(20);
        	//timer.setRepeats(false);
        	
        	for(int i=0; i<100; i++)
            {
        		timer.start();
            }
        }
        
        if(obj == motorSwitchOff)
        {
        	Thread.currentThread().stop();
        }
    }
   
    public void mouseClicked(MouseEvent e)
    {
        GObject obj = getElementAt(e.getX(), e.getY());  
        //println(obj + "" + e.getX() + " " + e.getY());
       
        if(obj == null)
            return;      
       
       
        if(obj == redLEDs)
        {           
            //println(obj + "" + e.getX() + " " + e.getY());
            redLEDs.handleClick(e.getX(), e.getY());
            System.out.println(redLEDs.getBinaryCodeString() + "---> " + (redLEDs.getHexCode()));
            //greenLEDs.update(redLEDs.getBinaryCodeString());
        }       
        if (obj == keyboard)
        {
           keyboard.handleClick(e.getX(), e.getY());
        }
               
        if(obj == elevator)
        {
            println(obj + "" + e.getX() + " " + e.getY());
            elevator.handleClick(obj, e.getX(), e.getY());
            elevator.toggleFloorDecoderLED(true, 0, 0);
        }
        
        if(obj == sevsegBlock)
        {
        	sevsegBlock.handleClick(e.getX(), e.getY());
        }
    }
        
    private void initMotor() 
    {
        motor = new Motor();
        add(motor, MOTOR_X_LOC, MOTOR_Y_LOC);        
    } 

    private void initElevator() 
    {
        elevator = new Elevator();              //To be updated everywhere ----- Global Object
        add(elevator, Ele_X_LOC, Ele_Y_LOC);
    }

    private void initKeyBoard() 
    {
        keyboard = new KeyBoard();
        add(keyboard, KEYBOARD_X_LOC, KEYBOARD_Y_LOC);
       
    }

    private void initLEDBlocks() 
    {
            greenLEDs = new LEDBlock(Color.GREEN);
                add(greenLEDs, LEDs_X_LOC, LEDs_Y_LOC+30);
               
                redLEDs = new LEDBlock(Color.RED);
                add(redLEDs, LEDs_X_LOC, LEDs_Y_LOC+60);

    }
    
    private void initButtons()
    {
        Out_Led_Button1 = new JButton("Green LEDs (Virtual Switch)");
        add(Out_Led_Button1, SOUTH);
        
        Out_Led_Button2 = new JButton("Green LEDs (Physical Switch)");
        add(Out_Led_Button2, SOUTH);
        
        keyboardSwitch = new JButton("Activate Keyboard");
        add(keyboardSwitch, NORTH);
        
        motorSwitchOnClockwise = new JButton("Motor(clockwise)");
        add(motorSwitchOnClockwise, NORTH);
        
        motorSwitchOnAntiClockwise = new JButton("Motor(anti - clockwise)");
        add(motorSwitchOnAntiClockwise, NORTH);
        
        motorSwitchOff = new JButton("Stop Motor");
        add(motorSwitchOff, NORTH);
        
        label = new GLabel("RVCE Interfacing Board");
        label.setFont("Comic Sans MS-30");
        add(label, LEDs_X_LOC + 100, LEDs_Y_LOC + 300 );
        
        GLabel names = new GLabel("(C) 2011 AD 01001101011001010010000001111001\n");
        names.setFont("Comic Sans MS-10");
        add(names, label.getX() + label.getWidth() +  20, label.getY() + 20);
    }
}