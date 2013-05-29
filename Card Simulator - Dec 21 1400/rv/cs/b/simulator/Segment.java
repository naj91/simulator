package rv.cs.b.simulator;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GRect;

public class Segment extends GCompound implements Constants
{
	private GRect segment ;
	
	/*
	 * type indicates the type of segment 0-horizontal segment and 1-indicates
	 * the vertical segment.
	 */
	public Segment(int type)
	{
		if(type == 1)  //Vertical segment
 		{
			segment = new GRect(VER_SEG_WIDTH , VER_SEG_HEIGHT);
			
			
		}
		else
		{
			segment = new GRect(HOR_SEG_LENGTH, HOR_SEG_BREADTH);
		}
		segment.setFillColor(Color.RED);
		add(segment);
	}
	/*
	 * Light up
	 */
	public void lightUP(boolean state)
	{
		segment.setFilled(state);
		
	}

}
