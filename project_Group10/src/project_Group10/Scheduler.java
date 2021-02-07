package project_Group10;

public class Scheduler extends Thread{
	
	
	public static String fInput[] = new String[4];
	public boolean empty1 = true;
	public static Boolean floorSensor = true;
	public static String floorno = null;
	public static String Direc = null;
	public static String time = null;
	public static String car = null;
	
	public synchronized void setfInput(String Inp[]) {
		
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
	
	public synchronized String getDirection() {
		return fInput[2];
	}
	
	public synchronized void CheckDirection() {
		while(fInput[2] == null) {
			try {
				System.out.println("wow");
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
	
	public synchronized String getCar() {
		return fInput[3];
	}
	
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
	public synchronized void run(){
		
		String[] op = Floor_main.data;
	    for(String str : op)
	        System.out.println(str);
	    
		setfInput(Floor_main.data);
	    
	    CheckFloor();
	    CheckDirection();
	    CheckTime();
	    CheckCar();
	}
	
	
	public static void main(String[] args) {
		
		Thread Floor = new Floor_main();
		Thread sch = new Scheduler();
		//Thread elv = new Elevator();
		Floor.start();
		//elv.start();
		sch.start();
		
		
	}
	
	
	
}
