package project_Group10;

public class Scheduler extends Thread{
	
	
	public static String fInput[] = null;
	public static Boolean floorSensor = true;
	public static String floorno = null;
	public static String Direc = null;
	public static String time = null;
	public static String car = null;
	
	public synchronized void setfInput(String Inp[]) {
		
		while(fInput != null) {
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
	
	
	public synchronized String getFloor() {
		return fInput[1];
	}
	
	public synchronized void CheckFloor() {
		while(fInput.length == 0) {
			try {
				System.out.println();
				wait();
			}catch(InterruptedException e) {
				System.out.println(e);
			}
		}
		floorno = getFloor();
		System.out.println(getFloor());
	}
	
	public synchronized String getDirection() {
		return fInput[2];
	}
	
	public synchronized void CheckDirection() {
		while(fInput.length == 0) {
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
	
	
	public synchronized String getTime() {
		return fInput[0];
	}
	
	public synchronized void CheckTime() {
		while(fInput.length == 0) {
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
	
	public synchronized String getCar() {
		return fInput[3];
	}
	
	public synchronized void CheckCar() {
		while(fInput.length == 0) {
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
	public void run(){
		setfInput(Floor_main.data);
//		String[] op = fInput;
//	    for(String str : op)
//	        System.out.println(str);
	    CheckFloor();
	    CheckDirection();
	    CheckTime();
	    CheckCar();
	}
	
	
	public static void main(String[] args) {
		
		Thread Floor = new Floor_main();
		Thread sch = new Scheduler();
		Thread elv = new Elevator();
		Floor.run();
		sch.run();
		elv.run();
		
	}
	
	
	
}
