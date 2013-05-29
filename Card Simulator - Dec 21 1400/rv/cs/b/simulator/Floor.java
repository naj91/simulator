
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.graphics.*;
import java.awt.Color;
/**
 *
 * 
 */
public class Floor extends GCompound implements Constants
{
    private LED floor_leds[];
    private LED floor_indicator;
    private GRect floor_rect;
   
    private boolean on = false;                 //True, only if the floor_rect AND floor_indicator are ON
    private boolean activated = true;          //if this is true, then the floor_rect and floor_indicator can be toggled
   
   
    public Floor()
    {
        floor_rect = new GRect(Ele_Floor_WIDTH,Ele_Floor_HEIGHT);
        floor_leds = new LED[3];
        floor_indicator = new LED(Ele_Floor_INDICATOR_LED_RADIUS,Color.RED);
       
        double X = getX();
        double Y = getY();
       
        double x = X;
        double y = Y;
        for(int i = 0 ; i < floor_leds.length ; i++)
        {
            floor_leds[i] = new LED(Ele_Floor_LED_RADIUS, Color.red);
            add(floor_leds[i],x,y);
            y += 2*Ele_Floor_LED_RADIUS+SPACING;
        }
       
        y = Y + 2*Ele_Floor_LED_RADIUS+SPACING;
        x = X + Ele_Floor_LED_RADIUS + Ele_Floor_INDICATOR_LED_RADIUS;
       
        floor_indicator.toggleLED(false);
        add(floor_indicator, x, y);
       
        x = x + Ele_Floor_INDICATOR_LED_RADIUS + 2*SPACING ;
        y = Y ;
       
        floor_rect.setFillColor(Color.BLACK);
        add(floor_rect,x,y);
    }
   
   
    /*
     * Handles Mouse Click
     */
    public void handleClick(double mouseX, double mouseY)
    {
        GObject obj = getElementAt(mouseX, mouseY);
        
        System.out.println("OBJ = " + obj + " " + mouseX + " " + mouseY);
        if(obj == floor_indicator)
            System.out.println("Indicator");
    }
    
    
    /*
     * You can toggle the Floor LEDs only if activated == true
     * 
     * if flag == true
     *      switches on the floor indication led
     * else
     *      switches off the floor indication led
     */
    public void toggleFloorLED(boolean flag)
    {
        if(activated)
        {
            floor_rect.setFilled(flag);
            floor_indicator.toggleLED(flag);
            on = flag;
        }
    }
    
    /*
     * If flag == true
     *      switches on the floor BCD LED indicated by the number i
     * else
     *      switches off the floor BCD Decoder LED indicated by the number i
     */
    public void toggleFloorDecoderLED(boolean flag, int i)
    {
        floor_leds[i].toggleLED(flag);
    }
    
    
    /*
     * If the Floor indicator is on, return true
     */
    public boolean isOn()
    {
        return on;
    }
    
    /*
     * Returns true or false, depending on whether the LED can be toggled or not
     */
    public boolean isActivated()
    {
        return activated;
    }

   
}
