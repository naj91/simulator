/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.program.ConsoleProgram;
import parport.ParallelPort;

/*
 * The Coconut class provides the main mode of communication between the 
 * Simulation and the Praaduct connected to the parallel port.
 * 
 * 
 */

class ParPortCommunicator implements Constants
{
   
    
   private static ParallelPort p378 =  new ParallelPort(0x378);
   private static ParallelPort p379 = new ParallelPort(0x379);
   private static ParallelPort p37A = new ParallelPort(0x37A);
   private static String col_patterns [] = {"1110","1101","1011","0111"}; 
   public static void main ( String []args )
   {
       //init();
       //while(true){   
       int aByte = inportB();
       outport(P378, aByte);
       
       System.out.println("Output to port: " + aByte);
      // }
       
//       try {
//		Thread.sleep(1000);
//	} catch (InterruptedException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//       Point p = readKeyboard();
//       System.out.println(p.row + "  " + p.col);
  }
   
   public static Point readKeyboard()
   {
	   
	   
	   byte pattern = (byte) 0xFF ;
	   byte data;
	   boolean found = false;
	  
	   for(int i=0 ; i<4 ; i++)
	   {
		   pattern = (byte) 0xFF;
		   pattern &= ~(1<<i);
		   outport(P378, pattern);
		   
		   data =(byte) inportB();
		   for(int j=0 ; j<4 ;j++)
		   {
			   data = (byte)(data>>4);
			   String data_string = Integer.toBinaryString(data);
			   if(data_string.length() > 4)
				   data_string = data_string.substring(data_string.length()-4);

			   System.out.println("Data: " + data_string);
			   int col = data_string.lastIndexOf('0');
			   if(col != -1){
				  // col = 3 - (31 - col) ;
				   System.out.println(col);
				   pattern &= (byte) ~(1<<7-j);
				   //found = true;
				   return new Point(i,col);
			   }
			   
		   
		   }
	   }
	   return null;
   }
   private static int findCol(String data_string)
   {
	   for(int i = 0; i<col_patterns.length; i++)
	   {
		   if(data_string.equals(col_patterns[i]))
			   return i;
	   }
	   return -1;
   }
   /*
    * Display Segment method
    */
   public static void DisplaySeg(String pattern)
   {
	   for(int i=0; i<pattern.length() ; i++)
	   {
		   if(pattern.charAt(i)=='1')
		   {
			   
			   
		   }
	   }
   }
   
   /*
    * This Method Reads the Input Switches (RED LEDs)
    */
   public static int inportB()
   {
       outport(P37A, 0x01);     //Send Strobe '0' to select the mux D3 to D0 inputs
       
       //Read from MUX
       int al = inport(P378);
       //System.out.println("P379 = " + al);
       al = al ^ 0x80;
       al = al >> 4;
       
       int ah = al;
       //System.out.println("ah = " + ah);
       
       outport(P37A, 0x00);     //Send strobe '1' to select the mux D7to D4 inputs
       
       //Read from Mux
       al = inport(P378);
       al = al ^ 0x80;
       al = al & 0xF0;
       
//       System.out.println("al = " + al);
//       
       al = al | ah;
       
       return al;
   }
   
   /*
    * This method is used to get a byte from any port, specified by Port Type
    */
   private static int inport(int portType)
   {       
       int aByte = 0;
       
       switch(portType)
       {
           case P378: aByte = p378.read();
               break;
           case P379: aByte = p379.read();
               break;
           case P37A: aByte = p37A.read();
               break;
           default: System.out.println("Writing to Invalid Port");
       } // write a byte to the port's DATA pins
       
       return aByte;
   }
   
   /*
    * This method is used to write a byte to a port, specified by Port Type
    */
   public static void outport(int portType, int aByte)
   {
       switch(portType)
       {
           case P378: p378.write(aByte);
               break;
           case P379: p379.write(aByte);
               break;
           case P37A: p37A.write(aByte);
               break;
           default: System.out.println("Writing to Invalid Port");
       } // write a byte to the port's DATA pins
   }
   
   /*
    * Initializes the static variables
    */
   private static void init()
   {
       p378 = new ParallelPort(0x378);
       p379 = new ParallelPort(0x379);
       p37A = new ParallelPort(0x37A);
   }
}
