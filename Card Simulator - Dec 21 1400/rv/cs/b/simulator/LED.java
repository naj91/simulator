/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GOval;
import acm.graphics.*;
import java.awt.Color;

/**
 * An LED is a GCompound object consisting of:
 *  1) GOval
 *  2) The radius of the GOval
 *  3) The Colour of the GOval
 */
public class LED extends GCompound
{
    private GOval oval;
    private double radius;
    private Color colour;    
    
    public LED()
    {}
    
    /*
     * Create an LED of radius r and Color c
     */
    public LED(double r, Color c)
    {
        radius = r;
        colour = c;
        
        oval = new GOval(radius, radius);
        oval.setColor(colour);
        oval.setFillColor(colour);
        add(oval);
    }
    
    /*
     * Create an LED of radius r and fills it with color c if isFilled=true
     */
    public LED(double r, Color c, boolean isFilled)
    {
        this(r, c);
        oval.setFilled(isFilled);
    }
    
    /*
     * This method allows you to turn on/off the LED, by specifying a boolean value
     *      if flag == true,
     *              LED is switched on
     *      else
     *              LED is switched off
     */
    public void toggleLED(boolean flag)
    {
        oval.setFilled(flag);
    }
    
    /*
     * This method returns true, if the LED is on
     *      and false, if the LED is off
     */
    public boolean isOn()
    {
        return oval.isFilled();
    }
}
