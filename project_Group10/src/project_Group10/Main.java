package project_Group10;

import java.util.Arrays;

public class Main extends Thread {
	
	/**
	 * This method is retreiving the data from the floor class via the scheduler. 
	 * It checks if the information was collected or not before attempting to collect it
	 */
		
	public synchronized void getInfo() {

			while(Elevator.info[0] == null && Elevator.info[1] == null && Elevator.info[2] == null && Elevator.info[3] == null) {
	            try {
	                System.out.println("Need Info first");
	                wait();
	            }catch (InterruptedException e) {
	                System.out.println(e);
	            }
	        }
			
	        System.out.println(Elevator.info[0]);
	        System.out.println(Elevator.info[1]);
	        System.out.println(Elevator.info[2]);
	        System.out.println(Elevator.info[3]);
	        System.out.println();
	    }
	
	
	/**
	 * Generate the input file and store it in an Object array
	 * @return
	 */
	@SuppressWarnings("static-access")
	public synchronized  Object[] inputFile() {
		Floor_main.b = new Button();
		Floor_main.input[0]=Floor_main.b.getTime().toString();
		Floor_main.input[1]= String.valueOf(Floor_main.b.currentFloor());
		Floor_main.input[2] = Floor_main.b.direction();
		Floor_main.input[3]=String.valueOf(Floor_main.car);
		
		
		System.out.println(Arrays.toString(Floor_main.input));
		notifyAll();
		return Floor_main.input;
	}
	
	
	/**
	 * This function sets the input file into an array of its own to be able to use it else where in the project
	 * 
	 * @param Inp  - which is input file
	 */
	public synchronized void setfInput() {
		
		while(Floor_main.input[0] == null && Floor_main.input[1] == null && Floor_main.input[2] == null && Floor_main.input[3] == null) {
			try {
				System.out.println("null");
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		
		System.out.println("HELOEOOEOE");
		
		
		
		Scheduler.floorno = (String) Floor_main.input[1];		// time/ floor / up/down / elevator no.
		Scheduler.Direc = (String) Floor_main.input[2];
		Scheduler.time = (String) Floor_main.input[0];
		Scheduler.car = (String) Floor_main.input[3];
		
		
		Elevator.info[0] = Scheduler.time; 
		Elevator.info[1] = Scheduler.floorno;
		Elevator.info[2] = Scheduler.Direc; 
		Elevator.info[3] = Scheduler.car;
		
		notifyAll();
	}
	
	
}
