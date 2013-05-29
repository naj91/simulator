/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GObject;
/**
 *
 * @author sai.asm
 */
public class Elevator extends GCompound implements Constants
{
    private Floor floor[];
   // private GroundFloor gfloor;
    
    public Elevator()
    {
        floor = new Floor[Ele_No_FLOORS];
        double x = getX();
        double y = getY();
        for(int i = 0 ; i < Ele_No_FLOORS ;i++)
        {
            floor[i] = new Floor();
            add(floor[i],x,y);
            y += Ele_Floor_HEIGHT + SPACING;
        }
    }
    
    
    /*
     * Used to handle a mouse click event
     */
    public void handleClick(GObject obj, double mouseX, double mouseY)
    {
        int clickedFloor = getClickedFloor(mouseX, mouseY);
        
        if(clickedFloor == -1)          //Invalid floor
            return;
        
        System.out.println("Floor Clicked: " + clickedFloor);
        
        floor[clickedFloor].handleClick(mouseX, mouseY);
        
        if(floor[clickedFloor].isActivated())
        {
            if(floor[clickedFloor].isOn())
                floor[clickedFloor].toggleFloorLED(false);
            else
                floor[clickedFloor].toggleFloorLED(true);
        }
    }
    
    
    
    /*
     * Returns the floor at the Current Cursor position
     */
    private int getClickedFloor(double mouseX, double mouseY)
    {
        for(int i=0; i<floor.length; i++)
        {
            if((getY() + floor[i].getY() <= mouseY) && (getY() + floor[i].getY() + floor[i].getHeight()) >= mouseY)
                return i;
        }
        return -1;
    }
    
    
    /*
     * Toggles the LED i, connected to the BCD Decoder
     */
    public void toggleFloorDecoderLED(boolean flag, int numFloor, int numLed)
    {
        floor[numFloor].toggleFloorDecoderLED(flag, numLed);
    }
    
}
