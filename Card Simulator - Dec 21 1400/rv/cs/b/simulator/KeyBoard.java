/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GRect;
import java.util.logging.Level;
import java.util.logging.Logger;

import rv.cs.b.simulator.KeyBoardKey;

/**
 *
 * @author 
 */
public class KeyBoard extends GCompound implements Constants
{
    private GRect boundary ;
    private KeyBoardKey keys[][];
    
    public KeyBoard()
    {
        double X = getX();
        double Y = getY();
        keys = new KeyBoardKey[NUM_KEYBOARD_KEYS_ROW][NUM_KEYBOARD_KEYS_COL];
      
        for(int i = 0 ; i<NUM_KEYBOARD_KEYS_ROW ; ++i)
        {
            double x = X;
            for (int j=0 ; j<NUM_KEYBOARD_KEYS_COL ; ++j)
            {
                keys[i][j] = new KeyBoardKey(Keyboard_Key_Width,Keyboard_Key_Height);
                add(keys[i][j],x,Y);
                x += Keyboard_Key_Width + SPACING;
               
            }
            Y += Keyboard_Key_Height + SPACING ;            
        }
        
    }
    
    /*
     * 
     */
    
    public void handleClick(double mouseX, double mouseY)
    {
        int row = -1, col = -1;
        boolean found = false;
        for(int i = 0 ; i<NUM_KEYBOARD_KEYS_ROW && !found ; ++i)
        {
            for(int j=0; j<NUM_KEYBOARD_KEYS_COL ; ++j)
            {
               if((getX() + keys[i][j].getX() < mouseX) && (getX() + keys[i][j].getX() + keys[i][j].getWidth() > mouseX))
               {
                    if((getY() + keys[i][j].getY() < mouseY) && (getY() + keys[i][j].getY() + keys[i][j].getHeight() > mouseY))
                    {
                       row = i;
                       col = j;
                       found = true;
                       break;
                    }
               }
            }
        }
        if(found)
        {
            byte pattern =(byte) 0xFF ;
            Press(row, col);
            System.out.println("Key pressed at row " + row + " col " + col );
            
            System.out.println("Key pressed at row " + row + " col " + col );
            //Release(row,col);
            pattern ^= 1<<row ;    
            pattern ^= 1<<(7-col);
            
            
            int res  = pattern;
           //res = res<<1;
            
            System.out.println(res);        
        }
        
    }
    /*
     * This method presses the key of ith row and jth column
     */
    public void Press(int i, int j)
    {
        keys[i][j].setPressed(true);
        
    }
    
    /* 
     * This method releases the key of ith row and jth column from pressed state
     */
    public void Release(int i,int j)
    {
        keys[i][j].setPressed(false);
    }
    
    
    public void clearAll()
    {
    	for(int i=0;i<keys.length;i++)
    	{
    		for(int j=0;j<keys.length;j++)
    		{
    			Release(i,j);
    		}
    	}
    }
    
}
