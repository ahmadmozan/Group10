package project_Group10;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.TimeUnit;


public class Elevator1 {

	private DatagramPacket sendPacket, receivePacket, rp;
	private DatagramSocket sendReceiveSocket, correctinfo;
	public String mode;
	public String Information[];
	ByteArrayOutputStream Data = new ByteArrayOutputStream();
	public String Reading;
	public String Signal1;
	public String return1;
	
	private static int currElev = 1;
	public static boolean moveit = false;
	

	public Elevator1() {
		try {
			sendReceiveSocket = new DatagramSocket();
			correctinfo = new DatagramSocket(8888);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);

		}
	}

	public void Receieve() {

		int messagesProcessed = 0;

		try {
			DatagramSocket socket = new DatagramSocket(23); // Creates socket bound to port 69

			for (int i = 0; i < 4; i++) {

				byte[] requestByteArray = "request".getBytes();
				boolean receieved = false; // defines a flag to check for receieving a actual packet vs a nothing to
											// report packet ("null")
				DatagramPacket recievedPacket = new DatagramPacket(new byte[4], 17); // Creates a packet to recieve into
				DatagramPacket requestPacket = new DatagramPacket(requestByteArray, requestByteArray.length,
						InetAddress.getLocalHost(), 22);

				while (!receieved) { // Loop until a non null packet is recieved
//					printPacket(requestPacket, true);
					socket.send(requestPacket); // Send a request to the intermediate server
					socket.receive(recievedPacket); // Receive the response
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

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void getSignal() {

		while (Signal1 == "false") {
			try {
				System.out.println("nobdoy is using the elavator yet");
				wait();
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			if (Signal1 == "True") {
				System.out.println("the elevator is heading to ur service");
			}
		}
		notifyAll();
	}

	public synchronized void returnSignal() {

		while (return1 == "false") {
			try {
				System.out.println("nobdoy is using the elavator yet");
				wait();
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			if (return1 == "True") {
				System.out.println("the elevator is heading to ur service");
			}
		}
		notifyAll();
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
