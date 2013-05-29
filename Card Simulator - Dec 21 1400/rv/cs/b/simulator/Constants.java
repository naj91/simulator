/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

/**
 *
 * @author satvik
 */
public interface Constants 
{
    
    static final int P378 = 1;
    static final int P379 = 2;
    static final int P37A = 3;
   
   
    static final int SPACING = 5;
    
    static final int LEDs_X_LOC = 250;
    static final int LEDs_Y_LOC = 275;
    static final int NUM_LED = 8;
    static final int LED_RADIUS = 25;
    
    
    
    static final int KEYBOARD_X_LOC = 15;
    static final int KEYBOARD_Y_LOC = 150;
    static final int NUM_KEYBOARD_KEYS_ROW = 4;
    static final int NUM_KEYBOARD_KEYS_COL = 4;
    static final int Keyboard_Key_Width = 25;
    static final int Keyboard_Key_Height = 25;
    
    
    static final int  Ele_X_LOC = 750;
    static final int  Ele_Y_LOC = 50;
    static final int Ele_No_FLOORS = 4;
    static final int Ele_Floor_LED_RADIUS = LED_RADIUS - 10;
    static final int Ele_Floor_INDICATOR_LED_RADIUS = 2*Ele_Floor_LED_RADIUS + SPACING;
    static final int Ele_Floor_HEIGHT = 3*(2*Ele_Floor_LED_RADIUS + SPACING);
    static final int Ele_Floor_WIDTH = 100;
    
    static final int HOR_SEG_BREADTH = 5;
    static final int HOR_SEG_LENGTH = 20;
    static final int VER_SEG_WIDTH = 5;
    static final int VER_SEG_HEIGHT = 20;
    static final int SEG_SPACING = 3;
    
    
    static final int Sev_Seg_X_LOC = 500; 
    static final int Sev_Seg_Y_Loc = 500;
    
    static final int MOTOR_X_LOC = 375;
    static final int MOTOR_Y_LOC = 100;
    static final int MOTOR_CENTRE_X = 50;
    static final int MOTOR_CENTRE_Y = 50;
    static final int NUM_OF_POINTS = 50;
    static final int SHAFT_RADIUS = 100;   
    static final int INDICATOR_RADIUS = 5;
    
}
