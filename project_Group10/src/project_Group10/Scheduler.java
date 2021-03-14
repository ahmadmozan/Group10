package project_Group10;

import java.util.concurrent.TimeUnit;

/**
 * This class is about a scheduler subsystem that utilizes other subsystems. It is responsible for taking the input file the
 * Floor subsystem produces and pushes that file to the other subsystems. This class will be responsible for timing all the 
 * elevator movements and sending signals to doors, motor, lamps etc. This is only iteration1 of the project further code
 * and operations will be added to satisfy this class needs.
 * @author ahmad
 *
 */
public class Scheduler extends Thread{
	
	/**
	 * Creating a string for the input file and a boolean to be able to identify if its empty or not
	 * Adding 4 Strings for each of the info given by the input file 
	 */
	public static String fInput[] = new String[4];
	public boolean empty1 = false;
	public static Boolean floorSensor = true;
	public static String floorno = null;
	public static String Direc = null;
	public static String time = null;
	public static String car = null;
	
	public static Motor mot = new Motor();
	public static Sensor sen = new Sensor();
	
	public static int currElev = 1;

	public static Main mn = new Main();
	
	/**
	 * gets the input file and prints it
	 */
	public synchronized void getfInput() {
		
		while(fInput == null) {
			try {
				System.out.println("Waiting on an input file");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		notifyAll();
		System.out.println(fInput);
	}
	
	/**
	 * Gets floor and prints it
	 * @return Floor string
	 */
	public synchronized String getFloor() {
		return fInput[1];
	}
	
	/**
	 * It checks if there is a floor value in yet or not and 
	 * prints that
	 */
	public synchronized void CheckFloor() {
		while(fInput[1] == null) {
			try {
				System.out.println("wow");
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		floorno = getFloor();
		System.out.println(getFloor());
	}
	
	/**
	 * Gets Direction and prints it
	 * @return Direction string
	 */
	public synchronized String getDirection() {
		return fInput[2];
	}
	
	/**
	 * It checks if there is a direction value in yet or not and 
	 * prints that
	 */
	public synchronized void CheckDirection() {
		while(fInput[2] == null) {
			try {
				System.out.println();
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		Direc = getDirection();
		System.out.println(getDirection());
	}
	
	/**
	 * Gets time and prints it
	 * @return time string
	 */
	public synchronized String getTime() {
		return fInput[0];
	}
	
	/**
	 * It checks if there is a time value in yet or not and 
	 * prints that
	 */
	public synchronized void CheckTime() {
		while(fInput[0] == null) {
			try {
				System.out.println();
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		time = getTime();
		System.out.println(getTime());
	}
	
	/**
	 * Gets car and prints it
	 * @return car string
	 */
	public synchronized String getCar() {
		return fInput[3];
	}
	
	/**
	 * It checks if there is a car value in yet or not and 
	 * prints that
	 */
	public synchronized void CheckCar() {
		while(fInput[3] == null) {
			try {
				System.out.println();
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		car = getCar();
		System.out.println(getCar());
	}
	
	
	/////////////////////////////////////////
	//// State Machine setup/////////////////
	/////////////////////////////////////////
	
	public enum statemachine{
		Fetch{
			public statemachine next() {
				return SendElevator;
			}
			
			public String dowork() {
				mn.setfInput();
				System.out.println("Information has been submitted, moving elevator next");
				System.out.println();
				return "Information has been submited, moving elevator next";
			}
			
		},
		SendElevator{
			
			public statemachine next() {
				return Repeat;
			}
			
			public String dowork() {
				
				System.out.println("Moving Elevator");
				Elevator.moveit = true;
				System.out.println();
				return "Moving Elevator";
			}
			
		},
		Repeat{
			
			public statemachine next() {
				return Fetch;
			}
			
			public String dowork() {
				while(Elevator.moveit == true) {
				}
				System.out.println("person has been dropped, waiting on new one");
				fInput = new String[4];
				floorno = null;
				Direc = null;
				car = null;
				time = null;
				System.out.println();
				return "person has been dropped, waiting on new one";
			}
			
		},;

		public abstract statemachine next();
		public abstract String dowork();
	}
	
	/**
	 * State machine that will keep running and make sure that the system is running in 
	 * a cycle that goes from fetching -> to moving elevator to pick up -> to moving 
	 * elevator to destination for drop off -> then finally repeating the cycle
	 */
	public synchronized void StateMachine() {
		statemachine state = statemachine.Fetch;
		while(true) {
			state.dowork();
			state = state.next();
			
			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * runs the state machine system.
	 */
	public synchronized void run(){
		 StateMachine();
	}
	
	/**
	 * Creates the three threads and then runs them creating our elevator whole system. 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread elv = new Elevator();
		Thread Floor = new Floor_main();
		Thread sch = new Scheduler();
		
		Floor.start();
		elv.start();
		sch.start();
		
		
	}
	
	
	
}
