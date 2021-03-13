package project_Group10;

import java.util.Arrays;

/**
 * This is the Floor subsystem class. It runs the floor subsystem which is responsible for generating an input file and sending the data to the 
 * scheduler for the elevator to use. This class will also be responsible for the floor lamps and buttons. The floor buttons will be implemented from the 
 * Button class which will indicate if the diretion is up or down.
 *@author Akkash Anton Amalarajah
 */
public class Floor_main extends Thread {
	
	static Lamp l;
	public Door Floor;
	public static Scheduler sch =  new Scheduler();
	
	public static int car=1;
	static Button b;
	public static Object[] input = new Object[4];
	
	
	/*
	 * The scheduler gets the input file with this method
	 */
	public synchronized Object[] getInput() {
		return input;
	}
	
//	public void lamps() {
//		b= new Button();
//		l = new Lamp();
//		l.getlu(Button.direction().equals("Up");
//		
//	}
	public boolean door() {
		Floor= new Door();
		boolean openClose;
		if (Door.openDoor()==true) {
			openClose=true;
			System.out.println("The doors are opening");
		}
		else {
			openClose=false;
			System.out.println("The doors are closing");
		}
		return openClose;
	}
	
	/**
	 * Main run method to run the floor class subsystem.
	 */
	public synchronized void run() {
		sch.mn.inputFile();
	}
		
}
