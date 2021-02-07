package project_Group10;

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
	public boolean empty1 = true;
	public static Boolean floorSensor = true;
	public static String floorno = null;
	public static String Direc = null;
	public static String time = null;
	public static String car = null;
	
	/**
	 * This function sets the input file into an array of its own to be able to use it else where in the project
	 * 
	 * @param Inp  - which is input file
	 */
	public synchronized void setfInput(String Inp[]) {
		
		System.out.println(Inp[0]);
		while(Inp[0] == null && Inp[1] == null && Inp[2] == null && Inp[3] == null) {
			try {
				System.out.println("wow");
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		
		for(int i = 0; i < fInput.length; i++) {
			if(fInput[i] !=null) {
				System.out.println(i);
				empty1 = false;
				break;
			}
		}
		
		while(empty1 == false) {
			try {
				System.out.println("Input file is currently being processed");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		fInput = Inp; // time/ floor / up/down / elevator no.
		notifyAll();
		
	}
	
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
	
	/**
	 * runs the system. First gets the array and then prints all 4 values
	 */
	public synchronized void run(){
		 
		setfInput(Floor_main.data);
	    
	    CheckFloor();
	    CheckDirection();
	    CheckTime();
	    CheckCar();
	}
	
	/**
	 * Creates the three threads and then runs them creating our elevator whole system. 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Thread Floor = new Floor_main();
		Thread sch = new Scheduler();
		Thread elv = new Elevator();
		Floor.start();
		elv.start();
		sch.start();
		
		
	}
	
	
	
}
