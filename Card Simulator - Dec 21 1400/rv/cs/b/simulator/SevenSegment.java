package rv.cs.b.simulator;

import acm.graphics.GCompound;
import acm.graphics.GOval;

public class SevenSegment extends GCompound implements Constants
{
	private Segment segment[];
	private GOval dot;
	 
	public SevenSegment()
	{
		segment = new Segment[7];
		dot = new GOval(SPACING, SPACING);
		initSegments();
		addSegments();
	}

	private void addSegments() {
		double X = getX();
		double Y = getY();
		
		add(segment[0],X+2*SEG_SPACING+VER_SEG_WIDTH, Y+SEG_SPACING); //a segment
		add(segment[3], X + segment[0].getX(),Y + 5*SEG_SPACING + 2*(HOR_SEG_BREADTH + VER_SEG_HEIGHT));//d segment
		add(segment[4], SEG_SPACING, HOR_SEG_BREADTH+ 4*SEG_SPACING+VER_SEG_HEIGHT + HOR_SEG_BREADTH);//e segment
		add(segment[5], X + segment[4].getX(), HOR_SEG_BREADTH+2*SEG_SPACING);//f segment
		add(segment[6], X +segment[0].getX(), Y + 3*SEG_SPACING + HOR_SEG_BREADTH + VER_SEG_HEIGHT);//g segment
		add(segment[1], X + 3*SEG_SPACING + HOR_SEG_LENGTH + VER_SEG_WIDTH ,Y + segment[5].getY());//b segment
		add(segment[2], X + segment[1].getX() , Y + segment[4].getY() );//c segment
		
					
		
	}

	private void initSegments() 
	{
		segment[0] = new  Segment(0); //a segment
		segment[1] = new Segment(1) ; //b segment
		segment[2] = new Segment(1) ; //c segment
		segment[3] = new Segment(0) ; //d segment
		segment[4] = new Segment(1) ; //e segment
		segment[5] = new Segment(1) ; //f segment
		segment[6] = new Segment(0) ; //g segment
			
	}
	public void Test(int i)
	{
		segment[i].lightUP(true);
	}

	public void handleClick(double mouseX, double mouseY) 
	{
		System.out.println("MouseX = "+mouseX + " MouseY = " + mouseY);
		
		int clicked_segment = getSelectedSegment(mouseX, mouseY);
		System.out.println("Clicked Segment = " + clicked_segment);
		
		int i = clicked_segment;
		if(clicked_segment != -1)
			System.out.println("X = " + (getX() + segment[i].getX()) + "  Y = " + (getY() + segment[i].getY()));
	}

	private int getSelectedSegment(double mouseX, double mouseY) 
	{
		 for(int i=0; i<segment.length; i++)
	     {
	            /* segment[i].getX() returns a relative value where segment[0].getX() returns 0.0
	                 To get the absolute value, getX() has been added to each statement
	                      getX() is the absolute value of the current LEDBlock object*/
	           
	            if((getX() + segment[i].getX() < mouseX) && (getX() + segment[i].getX() + segment[i].getWidth() > mouseX))
	               if((getY() + segment[i].getY() < mouseY) && (getY() + segment[i].getY() + segment[i].getHeight() > mouseY))
	               {
	            	   System.out.println("X = " + (getX() + segment[i].getX()) + "  Y = " + (getY() + segment[i].getY()));
	            	   Test(i);
	                   return i;
	               }
	     }
		return -1;
	}

}
