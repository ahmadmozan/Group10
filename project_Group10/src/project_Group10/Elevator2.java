package project_Group10;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;



import java.io.ByteArrayOutputStream;

public class Elevator2 {

	//private DatagramPacket sendPacket, receivePacket, rp;
	private DatagramSocket EM;
	public String mode;
	public String Reading2;
	public String Information[];
	ByteArrayOutputStream Data = new ByteArrayOutputStream();
	public String Signal2;
	public String return2;

	private static int currElev = 1;
	public static boolean moveit = false;
	
	

	public void Receieve() throws InterruptedException {

		/// this was added by ousama on 27/03/21

		try {
			 EM= new DatagramSocket(24); // Creates socket bound to port 24

			

				
				byte[] ele1=new byte[1024];
				
				DatagramPacket E1 = new DatagramPacket(ele1, ele1.length); // Creates a packet to recieve into
				//DatagramPacket requestPacket = new DatagramPacket(requestByteArray, requestByteArray.length,InetAddress.getLocalHost(), 22);
				
				EM.receive(E1);
				String ss;
				if (!moveit) {
					
					ss= "Elevator free";
				} else {
					ss = "Elevator currently in use";
				}
				
				byte [] answer= (ss).getBytes();
				DatagramPacket m1= new DatagramPacket(answer,answer.length,InetAddress.getLocalHost(),22);
				EM.send(m1);
			
			// everthing ousama wrote on 27/03/21
				/*
				while (!receieved) { // Loop until a non null packet is recieved
//					printPacket(requestPacket, true);
					EM.send(requestPacket); // Send a request to the intermediate server
					EM.receive(recievedPacket); // Receive the response
//					printPacket(recievedPacket, false);
					if (!(new String(recievedPacket.getData()).trim().equals("NA"))) {// If the response is not null,
																						// ie. a actual response
						receieved = true; // Break out of loop
					}
					Thread.sleep(1000);
				}

				byte[] dataArray = recievedPacket.getData(); // get the data from the packet to analyze
				if (dataArray[0] != 0 | dataArray[0] != 1) { // If the prefix is not invalid
					throw new IOException("Bad Packet (Invalid Request)"); // Get the length of the first string
				}

				Information[i] = new String(dataArray);

			}*/

		} catch (IOException e) {
			e.printStackTrace();
		}
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
				moveit = true;
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
				System.out.println("lets move elevator2 to desired location");
				long start_door= System.currentTimeMillis();
				long currentTime_door=start_door;
				long end_door= start_door + 10*1000;

				if (Door.getDo() == false) {
					Door.closeDoor();
				}
				if(currentTime_door>=end_door) {
					System.out.println("Error: Door malfunction");
				}

				if (currElev > Integer.parseInt(Scheduler.floorno)) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move1= start_move + 20*1000;
					System.out.println("moving down");
					Scheduler.mot.moveDown(Integer.parseInt(Scheduler.floorno));
					if(currentTime_move>=end_move1) {
						System.out.println("Error: Elevator malfunction");
					}
				}

				if (currElev < Integer.parseInt(Scheduler.floorno)) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					System.out.println("moving up");
					Scheduler.mot.moveUp(Integer.parseInt(Scheduler.floorno));
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
				}

				Scheduler.sen.sendSignal();
				Door.openDoor();
				Scheduler.sen.clearSignal();

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

				currElev = Button.destFloor();

				if (currElev > Integer.parseInt(Scheduler.floorno)) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					Scheduler.mot.moveUp(Button.getdestFloor());
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
				}
				if (currElev < Integer.parseInt(Scheduler.floorno)) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					Scheduler.mot.moveDown(Button.getdestFloor());
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
				}

				Scheduler.sen.sendSignal();
				long start= System.currentTimeMillis();
				long currentTime=start;
				long end= start + 30*1000;
				Door.openDoor();
				Scheduler.sen.clearSignal();

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

				Elevator.info = new String[4];
				moveit = false;
				
				return "Now that person has been dropped off, job done";
			}

		};

		public abstract elevatorstatemch next();

		public abstract String dowork();

	}

//state machine to cycle through the states we have
	public synchronized static void StateMachine2() {
		elevatorstatemch state = elevatorstatemch.getInfo;
		while (true) {
			state.dowork();
			state = state.next();

			try {
				TimeUnit.SECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

//to get destination after person enters elevator
	public static void elevButton() {
		Button newbutton = new Button();
		newbutton.destFloor();
		currElev = newbutton.destFloor;
	}

	/*
	 * This method runs the elevator class subsystem.
	 */
	public void run() {

		StateMachine2();

	}
	
	
	
	public static void main(String args[]) {
		Elevator1 newe = new Elevator1();

		while (true) {
			newe.Receieve();
		}

	}

}
