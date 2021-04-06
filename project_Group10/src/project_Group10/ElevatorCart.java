package project_Group10;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;

import project_Group10.ElevatorCart.elevatorstatemch;


public class ElevatorCart {

	//private DatagramPacket m1,EM1;
	private Lamp flrLamp;
	private Button flrButton;
	private static Door cartDoor;
	private int cartNumber;
	private static boolean status;
	private Motor motor;
	private static String  currFlr;
	private static int  destFlr;
	private static int  finalFlr;
	public String DoorTime;
	public String MotorTime;
	private static Elevator electoe;
	
	
	


	@SuppressWarnings("static-access")
	public ElevatorCart(int i)  {
		DoorTime= electoe.info[4];
		MotorTime=electoe.info[5];
	    
		
		cartNumber = i;
		flrLamp = new Lamp();
		cartDoor = new Door();
		
		motor = new Motor();
		status = false;
		currFlr = "1";
		
		
		
	
	}

	public boolean setDestFlr(int dest, int dest2)  {
		
		destFlr= dest;
		finalFlr = dest2;
		return true;		
	}

	public boolean cartStatus()  {
		
		
		return status;		
	}
	

	
	public boolean closeDoor(int i)  {
		
		
		return Door.closeDoor();	
	}
	
	
	
	public boolean openDoor(int i)  {
		
		
		return Door.openDoor();		
	}
	
	
	public boolean doorStatus()  {
		
		
		return Door.getDo();		
	}


	
	
	public boolean onLamp()  {
			
		return true;		
	}
	
	public boolean offLamp()  {
		
		return true;		
	}
	
	
	// Motor moves up to input floor and returns true if successful
	public void moveUp(String floor) {
		destFlr = 0;
		
		try {
			destFlr= Integer.parseInt(floor);
			}catch (NumberFormatException e) {}
		
		if (destFlr !=0) {
			motor.moveUp(destFlr);
			}
		
		currFlr = floor;
	}
	
	
	// Motor moves down to input floor and returns true if successful
	public void moveDown(String floor) {
		destFlr = 0;	
		try {
			destFlr = Integer.parseInt(floor);
			}catch (NumberFormatException e) {}
		
		if (destFlr !=0) {
			motor.moveUp(destFlr);
			}
		currFlr = floor;
	}
	
/////////////////////////////////////////
////State Machine setup/////////////////
/////////////////////////////////////////


	public enum elevatorstatemch {
		getInfo {
			public elevatorstatemch next() {
				return Move;
			}

			public String dowork() {
				status = true;
				System.out.println("Getting informtion on where to go");
				Elevator.outPut();
				System.out.println();
				return "Getting informtion on where to go";
			}

		},
		Move {

			public elevatorstatemch next() {
				return Move2;
			}

			public String dowork() {

				System.out.println("Information received!");
				System.out.println("lets move elevator1 to desired location");
				long start_door= System.currentTimeMillis();
				long currentTime_door=start_door;
				long end_door= start_door + 10*1000;

				if (Door.getDo() == false) {
					Door.closeDoor();
				}
				if(currentTime_door>=end_door) {
					System.out.println("Error: Door malfunction");
				}

				if (Integer.parseInt(currFlr) < destFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move1= start_move + 20*1000;
					System.out.println("moving down");
					//Scheduler.mot.moveDown(Integer.parseInt(Scheduler.floorno));
					if(currentTime_move>=end_move1) {
						System.out.println("Error: Elevator malfunction");
					}
				}

				if (Integer.parseInt(currFlr) > destFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					System.out.println("moving up");
					//Scheduler.mot.moveUp(Integer.parseInt(Scheduler.floorno));
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
					
				}
				
				//Door.openDoor();
				
				//Scheduler.sen.clearSignal();
				Door.closeDoor();

				System.out.println("Person secured inside elevator1");
				System.out.println();
				return "Person secured inside elevator1";
			}

		},
		Move2 {

			public elevatorstatemch next() {
				return getInfo;
			}

			public String dowork() {

				if (Door.getDo() == false) {
					Door.closeDoor();
				}
                
				 

				if (Integer.parseInt(currFlr) < destFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					//Scheduler.mot.moveUp(Button.getdestFloor());
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
				}
				if (Integer.parseInt(currFlr) > destFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					//Scheduler.mot.moveDown(Button.getdestFloor());
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
				}

				//Scheduler.sen.sendSignal();
				long start= System.currentTimeMillis();
				long currentTime=start;
				long end= start + 30*1000;
				Door.openDoor();
				//Scheduler.sen.clearSignal();

				try {
					TimeUnit.SECONDS.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				Door.closeDoor();
				if(currentTime==end) {
					return "Error: Door malfunction";
				}
				System.out.println("Now that person has been dropped off, job done");
				System.out.println();

				//Elevator.info = new String[4];
				status = false;
				
				return "Now that person has been dropped off, job done";
			}

		};

		public abstract elevatorstatemch next();
		public abstract String dowork();
	}
	
	

	//state machine to cycle through the states we have
		public synchronized void StateMachine2() {
			elevatorstatemch state = elevatorstatemch.getInfo;
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

	
	
	public static void main(String args[])  {
		
		
	}
		

}
