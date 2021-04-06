package project_Group10;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;

import project_Group10.ElevatorCart.elevatorstatemch;


public class ElevatorCart1 {

	//private DatagramPacket m1,EM1;
	private Lamp flrLamp;
	private Button flrButton;
	private static Door cartDoor;
	private int cartNumber;
	public static boolean status;
	private static int  currFlr;
	private static int  destFlr;
	private static int  finalFlr;
	public static byte[] signal = new byte[100];
	public static String DoorTime;
	public static String MotorTime;
	public static DatagramPacket receive, receive1;
	public static DatagramSocket receiveSocket, receiveSocket1;
	public static Motor mot;
	
	public static String info[] = new String[6];
	public static ElevatorCart1 cart2;
	

	@SuppressWarnings("static-access")
	public ElevatorCart1()  {
		flrLamp = new Lamp();
		cartDoor = new Door();
		mot = new Motor();
		status = false;
		currFlr = 1;
		
	
	}

	/**
	 * This method returns to the scheduler class the results of the task complete. It updates the scheduler.
	 */
	public synchronized static void outPut() {

		System.out.println("we are currently on the "+ info[1]+" floor.\n");
		System.out.println("we are currently going "+ info[2]+"\n");
		System.out.println("the current time is "+ info[0]+"\n");
		System.out.println("we are heading to "+info[3]+"\n");

	}
	
	public static boolean setDestFlr(int dest, int dest2)  {
		
		destFlr= dest;
		finalFlr = dest2;
		return true;		
	}
	
	 public static void receive()
	   {
	      // Construct a DatagramPacket for receiving packets up 
	      // to 100 bytes long (the length of the byte array).

	      signal = new byte[2];
	      receive= new DatagramPacket(signal, signal.length);
	      System.out.println("Waiting for task...\n");

	      try {        
	         System.out.println("Waiting..."); // so we know we're waiting
	         receiveSocket.receive(receive);
	      } catch (IOException e) {
	         e.printStackTrace();
	         System.exit(1);
	      }

	      System.out.println(receive.getData());
	      System.out.println("Task received. Moving now...");
	      
	      String s = new String(receive.getData(), StandardCharsets.UTF_8);
	      System.out.println(s);
	      
	      if(s.equals("go")) {
	    	  StateMachine2();
	      }
	      else {
	    	  System.out.println("whoops something went wrong");
	      }
	      
	      // Slow things down (wait 5 seconds)
	      try {
	          Thread.sleep(5000);
	      } catch (InterruptedException e ) {
	          e.printStackTrace();
	          System.exit(1);
	      }
	   }
	
	 public static void receive2() {
		 byte[] info1 = new byte[100];
		 receive1 = new DatagramPacket(info1, info1.length);
		 try {
			receiveSocket1.receive(receive1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String infor = new String(receive1.getData(), StandardCharsets.UTF_8);
		String[] Infor1 = infor.split(" ");
		info = Infor1;
		
		cart2.destFlr = Integer.parseInt(info[1]);
		cart2.finalFlr = Integer.parseInt(info[3]);
		cart2.DoorTime = info[4];
		cart2.MotorTime = info[5];
		
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
				cart2.status = true;
				System.out.println("Getting informtion on where to go");
				cart2.outPut();
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
				
				mot.MTime = MotorTime;
				cartDoor.DTime = DoorTime;
				
				long start_door= System.currentTimeMillis();
				long currentTime_door=start_door;
				long end_door= start_door + 10*1000;

				if (cartDoor.getDo() == false) {
					cartDoor.closeDoor();
				}
				if(currentTime_door>=end_door) {
					System.out.println("Error: Door malfunction");
				}

				if (currFlr > destFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move1= start_move + 20*1000;
					System.out.println("moving down");
					System.out.println(MotorTime);
					//int x = Integer.parseInt(MotorTime);
					//System.out.println(x);
					mot.moveDown(destFlr);
					if(currentTime_move>=end_move1) {
						System.out.println("Error: Elevator malfunction");
					}
				}

				if (currFlr < destFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					System.out.println("moving up");
					System.out.println(MotorTime);
					//int x = Integer.parseInt(MotorTime);
					//System.out.println(x);
					mot.moveUp(destFlr);
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
					
				}
				
				Door.openDoor();
				
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
                
				 

				if (currFlr < finalFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					System.out.println("moving up");
					System.out.println(MotorTime);
					mot.moveUp(finalFlr);
					if(currentTime_move>=end_move) {
						System.out.println("Error: Elevator malfunction");
					}
				}
				if (currFlr > finalFlr) {
					long start_move= System.currentTimeMillis();
					long currentTime_move=start_move;
					long end_move= start_move + 20*1000;
					System.out.println("moving down");
					System.out.println(MotorTime);
					mot.moveDown(finalFlr);
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

				Door.closeDoor();
				if(currentTime==end) {
					return "Error: Door malfunction";
				}
				System.out.println("Now that person has been dropped off, job done");
				System.out.println();

				//Elevator.info = new String[4];
				cart2.status = false;
				return "Now that person has been dropped off, job done";
			}

		};

		public abstract elevatorstatemch next();
		public abstract String dowork();
	}
	
	

	//state machine to cycle through the states we have
		public synchronized static void StateMachine2() {
			elevatorstatemch state = elevatorstatemch.getInfo;
			while(true) {
				state.dowork();
				if(state == state.Move2) {
					break;
				}
				state = state.next();
				
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	
	
	public static void main(String args[])  {
		cart2 = new ElevatorCart1();
		try {
			cart2.receiveSocket = new DatagramSocket(520);
			cart2.receiveSocket1 = new DatagramSocket(521);
		} catch (SocketException e) {
			e.printStackTrace();
		}
		while(true) {
			cart2.receive();
		}
		
	}
		

}
