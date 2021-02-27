package Group10;

import java.util.Arrays;


public class Floor_main extends Thread {
	
	public static int car=1;
	static Button b;
	public Scheduler sch =  new Scheduler();
	
	static Object[] input = new Object[4];
	public static void main(String[] args) {
		inputFile();
	}

	
	
	@SuppressWarnings("static-access")
	public synchronized static Object[] inputFile() {
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
	
	public synchronized Object[] getInput() {
		return input;
	}
	
	/**
	 * Main run method to run the floor class subsystem.
	 */
	public synchronized void run() {
		inputFile();
	}
		
}
