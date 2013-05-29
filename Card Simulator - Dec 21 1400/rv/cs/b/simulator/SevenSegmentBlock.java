package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GRect;

public class SevenSegmentBlock extends GCompound implements Constants
{
	private SevenSegment  sevensegments[];
	private GRect boundary;

	
	public SevenSegmentBlock(int num)
	{
		sevensegments = new SevenSegment[num];
		double X = getX();
		double Y = getY();
		for(int i = 0; i<sevensegments.length; i++)
		{
			sevensegments[i] = new SevenSegment();
			add(sevensegments[i],X + i*(4*SEG_SPACING + 2*VER_SEG_WIDTH + HOR_SEG_LENGTH),Y);
		}
		boundary = new GRect((4 * SEG_SPACING + HOR_SEG_BREADTH + SPACING)
				* num * 2, 6 * SEG_SPACING + 3 * HOR_SEG_BREADTH
				+ VER_SEG_HEIGHT * 2 + SPACING);
       
        //add(boundary, getX() - SPACING/2, getY() - SPACING/2);

	}
	
	public void handleClick(double mouseX, double mouseY)
	{
		int clicked_block = getClickedBlock(mouseX, mouseY);
		System.out.println("Clicked Block " + clicked_block);
		
		if(clicked_block == -1)
			return ;
		sevensegments[clicked_block].handleClick(mouseX,mouseY);
	}
	
	/*
     * Given the coordinates of the location where a mouse Click has occurred,
     * this method returns the number (position) of the LED clicked (if valid)
     * or returns -1, if no LED has been clicked
     */
    private int getClickedBlock(double mouseX, double mouseY)
    {
        for(int i=0; i<sevensegments.length; i++)
        {
            /* sevensegments[i].getX() returns a relative value where sevensegments[0].getX() returns 0.0
                 To get the absolute value, getX() has been added to each statement
                      getX() is the absolute value of the current LEDBlock object*/
           
			if ((getX() + sevensegments[i].getX() < mouseX)
					&& (getX() + sevensegments[i].getX()
							+ sevensegments[i].getWidth() > mouseX)) {
				if ((getY() + sevensegments[i].getY() < mouseY)
						&& (getY() + sevensegments[i].getY()
								+ sevensegments[i].getHeight() > mouseY)) {
					return i;
				}
			}
		}
        return -1;
    }

}
