package rv.cs.b.simulator;

import java.awt.Color;

import acm.graphics.GCompound;
import acm.graphics.GLine;
import acm.graphics.GOval;
import acm.graphics.GPoint;

public class Motor extends GCompound implements Constants
{
    
    private GOval stepCircles[];
    private GPoint pointsOnCircle[];
    private GLine indicatorLine;    
    
    byte aByte ;
    String pattern ;
    
    private int currentPointIndex;
    
    private double cx, cy;
    
    public Motor()
    {
        cx = MOTOR_CENTRE_X;
        cy = MOTOR_CENTRE_Y;
        constructCircle(cx, cy);
        
        pattern = "10001000" ;
    }
    
    
    /*
     * Rotates the motor by one step in the 
     *         clockwise direction, if clockwise is true
     * else
     *         anti-clockwise direction if clockwise is false
     */
    public void rotateMotor(boolean clockwise)
    {
        GPoint nextPoint;
        int nextPointIndex;
        
        
        
        if(clockwise)
        {
            nextPointIndex = (currentPointIndex + 1) % NUM_OF_POINTS;
            nextPoint = pointsOnCircle[nextPointIndex];
            indicatorLine.setEndPoint(nextPoint.getX(), nextPoint.getY());    
            
            
            ParPortCommunicator.outport(P378, (byte) 0x88);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x44);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x22);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x11);
            pause(5);
            /*ParPortCommunicator.outport(P378, (byte) 0x88);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x44);*/
            
            
        }
        else
        {        
            nextPointIndex = currentPointIndex - 1;
            if(nextPointIndex < 0)
                nextPointIndex = NUM_OF_POINTS - 1;
            nextPoint = pointsOnCircle[(nextPointIndex) % NUM_OF_POINTS];
            indicatorLine.setEndPoint(nextPoint.getX(), nextPoint.getY());
            
            ParPortCommunicator.outport(P378, (byte) 0x44);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x88);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x11);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x22);
            pause(5);
            /*ParPortCommunicator.outport(P378, (byte) 0x33);
            pause(5);
            ParPortCommunicator.outport(P378, (byte) 0x44);*/
            
        }   
        
        
        stepCircles[currentPointIndex].setFilled(false);
        stepCircles[currentPointIndex].setVisible(false);
        stepCircles[nextPointIndex].setFilled(true);
        stepCircles[nextPointIndex].setVisible(true);
        
        currentPointIndex = nextPointIndex;
    }
    
    
    /*
     * Constructs a circle whose circumference is divided into NUM_OF_POINTS parts
     *         and stores the coordinates of these points in the GPoint object - pointsOnCircle
     */
    private void constructCircle(double cx, double cy)
    {
        pointsOnCircle= new GPoint[NUM_OF_POINTS];
        stepCircles = new GOval[NUM_OF_POINTS]; 
        
        double x, y;
        for(int i=0; i<NUM_OF_POINTS; i++)
        {
            x=cx + SHAFT_RADIUS * Math.cos(i*2*Math.PI/NUM_OF_POINTS);
            y=cy + SHAFT_RADIUS * Math.sin(i*2*Math.PI/NUM_OF_POINTS);
            
            pointsOnCircle[i]=new GPoint(x,y);
        }
        
        drawCircleWithPoints();    
        
        
        aByte = (byte) (0x88);
    }
    
    
    /*
     * Using the GPoint object - pointsOnCircle, this method draws a(n approximate) circle using lines
     *         for the circumference.
     */
    private void drawCircleWithPoints()
    {
        int i;
        for(i=0; i<pointsOnCircle.length-1; i++)
        {
            double x1=pointsOnCircle[i].getX();
            double x2=pointsOnCircle[i+1].getX();
            double y1=pointsOnCircle[i].getY();
            double y2=pointsOnCircle[i+1].getY();
            
            GLine line=new GLine(x1, y1, x2, y2);
            add(line);
            
            stepCircles[i] = new GOval(INDICATOR_RADIUS, INDICATOR_RADIUS);
            stepCircles[i].setFillColor(Color.RED);
            stepCircles[i].setVisible(false);
            add(stepCircles[i], x1, y1);
        }
        GLine line=new GLine(pointsOnCircle[i].getX(),pointsOnCircle[i].getY(),pointsOnCircle[0].getX(),pointsOnCircle[0].getY());
        add(line);
        
        stepCircles[i] = new GOval(INDICATOR_RADIUS, INDICATOR_RADIUS);
        stepCircles[i].setFillColor(Color.RED);
        stepCircles[i].setVisible(false);
        add(stepCircles[i], pointsOnCircle[i].getX(), pointsOnCircle[i].getY());
        
        indicatorLine = new GLine(cx, cy, pointsOnCircle[0].getX(), pointsOnCircle[0].getY());
        add(indicatorLine);
        
        currentPointIndex = 0;
    }
}