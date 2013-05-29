/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import java.awt.Color;

/**
 *
 * @author satvik
 */
public class KeyBoardKey extends GCompound 
{

    private GRect key;
    private double width;
    private double length;
    private Color colour;    
    
    public KeyBoardKey()
    {}
    
    /*
     * Create an Key of width w and lenght l
     */
    public KeyBoardKey(double w, double l)
    {
        width = w;
        length = l;
        key = new GRect(width, length);
        key.setColor(Color.BLACK);
        key.setFillColor(colour);
        add(key);
    }
    
    /*
     * This method allows you to turn on/off the Key, by specifying a boolean value
     *      if flag == true,
     *              Key is switched on
     *      else
     *              Key is switched off
     */
    public void toggleKey(boolean flag)
    {
        key.setFilled(flag);
    }
    
    /*
     * This method returns true, if the Key is on
     *      and false, if the LED is off
     */
    public boolean isOn()
    {
        return key.isFilled();
    }
    
    public void setPressed(boolean state)
    {
        key.setFilled(state);
        
    }

}
