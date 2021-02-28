package project_Group10;

import java.util.Arrays;

/**
 * This is the Floor subsystem class. It runs the floor subsystem which is responsible for generating an input file and sending the data to the 
 * scheduler for the elevator to use. This class will also be responsible for the floor lamps and buttons. The floor buttons will be implemented from the 
 * Button class which will indicate if the diretion is up or down.
 *@author Akkash Anton Amalarajah
 */
public class Floor_main extends Thread {
	
	public static int car=1;
	static Button b;
	static Lamp l;
	public Door Floor;
	public Scheduler sch =  new Scheduler();
	
	public static Object[] input = new Object[4];
	

	
	/**
	 * Generate the input file and store it in an Object array
	 * @return
	 */
	@SuppressWarnings("static-access")
	public synchronized  Object[] inputFile() {
		b = new Button();
		input[0]=b.getTime().toString();
		input[1]= String.valueOf(b.currentFloor());
		input[2] = b.direction();
		input[3]=String.valueOf(car);
		

		Scheduler.floorno = input[0].toString();
		Scheduler.Direc = input[1].toString();
		Scheduler.time = input[2].toString();
		Scheduler.car = input[3].toString();
		
		System.out.println(Arrays.toString(input));
		notifyAll();
		//Inp = true;
		return input;
	}
	
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
		inputFile();
	}
		
}
