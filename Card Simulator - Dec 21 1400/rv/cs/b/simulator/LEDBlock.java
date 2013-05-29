/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import java.awt.Color;

/**
 * An object of LEDBlock is an array of Output LEDs of Color color
 * An LEDBlock also stores the binary equivalent depicted by the LEDs that are on or off
 */
public class LEDBlock extends GCompound implements Constants
{
    private GRect boundary;
    private LED leds[];  
    private Color color;
    private String binaryCode;
   
    public LEDBlock(){}
   
    /*
     * Creates an unfilled LEDBlock of NUM_LEDS of color C
     */
    public LEDBlock(Color c)
    {
        color = c;
       
        double x = getX();
        double y = getY();
       
        leds = new LED[NUM_LED];
        for(int i=0; i<leds.length; i++)
        {
            leds[i] = new LED(LED_RADIUS, color);
           
            add(leds[i], x, y);
            x += LED_RADIUS + SPACING;
        }
       
              
        boundary = new GRect((leds[0].getWidth() + SPACING) * leds.length, leds[0].getHeight() + SPACING);
        boundary.setColor(color);
        add(boundary, getX() - SPACING/2, getY() - SPACING/2);
       
        binaryCode = "00000000";
    }
   
    /*
     * Creates an LEDBlock of length NUM_LED, and fills it with Color C depending on whether setFilled = true or false
     */
    public LEDBlock(Color c, boolean setFilled)
    {
        this(c);
       
        for(int i=0; i<leds.length; i++)
        {
            leds[i].toggleLED(setFilled);
        }
       
        if(setFilled)
            binaryCode = "11111111";
    }
   
    /*
     * Returns the Binary Code (String) stored in the Combination of 8 LEDs
     */
    public String getBinaryCodeString()
    {
        return binaryCode;
    }
   
    /*
     * Returns the Hex Code (int) stored in the Combination of 8 LEDs
     */
    public int getHexCode()
    {
        return Integer.parseInt(binaryCode, 2);
    }
   
   
    /*
     * This method is called by the MouseEvent handler method, and toggles the LEDs
     * of the given LEDBlock, if a valid LED is clicked
     */
    public void handleClick(double mouseX, double mouseY)
    {
        int clickedLED = getClickedLED(mouseX, mouseY);
       
       
        if(clickedLED == -1)            //LED hasn't been clicked
            return;
       
        //add(new GLabel("HAHA " + clickedLED), getX() + 50, getY() + 50);
        System.out.println("LED " + clickedLED + " has been clicked");
       
        if(leds[clickedLED].isOn())
            leds[clickedLED].toggleLED(false);
        else
            leds[clickedLED].toggleLED(true);
       
        //Modify the Binary Code
       
        String temp = "";
        for(int i=0; i<leds.length; i++)
        {
            if(i == clickedLED)
                temp += ((leds[i].isOn())? "1":"0");
            else
                temp += binaryCode.charAt(i);
        }
        binaryCode = temp;
        //CocoOut();
        
       
    }
    
    
    /*
     * 
     * 
     */
    private void CocoOut()
    {
        ParPortCommunicator.outport(P378, getHexCode());
    }
   
   
    /*
     * Given the coordinates of the location where a mouse Click has occurred,
     * this method returns the number (position) of the LED clicked (if valid)
     * or returns -1, if no LED has been clicked
     */
    private int getClickedLED(double mouseX, double mouseY)
    {
        for(int i=0; i<leds.length; i++)
        {
            /* leds[i].getX() returns a relative value where leds[0].getX() returns 0.0
                 To get the absolute value, getX() has been added to each statement
                      getX() is the absolute value of the current LEDBlock object*/
           
            if((getX() + leds[i].getX() < mouseX) && (getX() + leds[i].getX() + leds[i].getWidth() > mouseX))
               if((getY() + leds[i].getY() < mouseY) && (getY() + leds[i].getY() + leds[i].getHeight() > mouseY))
               {
                   return i;
               }
        }
        return -1;
    }
   
    
    
    /*
     * This method calls Cocoout (parallel port) only for green led (green == true)
     */
    public void update(String binCode, boolean green)
    {
        binaryCode = binCode;
        for(int i=0 ; i<leds.length ; i++)
        {
           if(binaryCode.charAt(i)== '1')
               leds[i].toggleLED(true);
           else
               leds[i].toggleLED(false);
        }
        
        if(green)
        	CocoOut();
    }
}