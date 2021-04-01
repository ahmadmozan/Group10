package project_Group10;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
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
	public static String fInput[] = new String[6];
	public boolean empty1 = false;
	public static Boolean floorSensor = true;
	public static String floorno = null;
	public static String Direc = null;
	public static String time = null;
	public static String car = null;
	public static Object Dtime;
	public static String Mtime;
	
	public static Motor mot = new Motor();
	public static Sensor sen = new Sensor();
	
	public static int currElev = 1;

	public static Main mn = new Main();
	
	public static DatagramSocket sendSocket, receiveSocket, sendSocket1, receiveSocket1;
	public static DatagramPacket sendPacket, receivePacket;
	public static DatagramPacket sendPacket1, receivePacket1;
	public static byte[] data = new byte[100];
	public static String s = null;
	static Scheduler sch = new Scheduler();

	//this is the initialization code for receiving the packet from Floor_main
		public Scheduler() {
			try {
		         sendSocket = new DatagramSocket();
		         sendSocket1 = new DatagramSocket();
		         receiveSocket = new DatagramSocket(5000);
		         receiveSocket1 = new DatagramSocket(5500);
		      } catch (SocketException se) {
		         se.printStackTrace();
		         System.exit(1);
		      } 
		}
		
		//code to receuve the input file stuff 
		 public static void receiveAndEcho()
		   {
		      // Construct a DatagramPacket for receiving packets up 
		      // to 100 bytes long (the length of the byte array).

		      data = new byte[100];
		      receivePacket = new DatagramPacket(data, data.length);
		      System.out.println("Waiting for input file.\n");

		      try {        
		         System.out.println("Waiting..."); // so we know we're waiting
		         receiveSocket.receive(receivePacket);
		      } catch (IOException e) {
		         e.printStackTrace();
		         System.exit(1);
		      }
		      // Process the received datagram.
		      System.out.println("Obtained input file information");
		      System.out.println(receivePacket.getData());
		      
		      // Slow things down (wait 5 seconds)
		      try {
		          Thread.sleep(5000);
		      } catch (InterruptedException e ) {
		          e.printStackTrace();
		          System.exit(1);
		      }
		      receiveSocket.close();
		   }
		 
		 
		 //this code converts the data from the received byte array into a string and stores it in the fInput string array
		 public static String[] convertToString(byte[] data) 
		 {
			 s = new String(data, StandardCharsets.UTF_8);
			 System.out.println(s);
			 String[] s2 = s.split(" ");
//			 for(int i = 0; i<s2.length; i++) {
//				 System.out.println(s2[i]);
//			 }
			 fInput = s2;
//			 for(int i = 0; i<fInput.length; i++) {
//				 System.out.println(fInput[i]);
//			 }
			return fInput;
		 }
	
	/**
	 * Gets floor and prints it
	 * @return Floor string
	 */
	public synchronized String getFloor() {
		return fInput[1];
	}
	

	/**
	 * Gets Direction and prints it
	 * @return Direction string
	 */
	public synchronized String getDirection() {
		return fInput[2];
	}
	

	
	/**
	 * Gets time and prints it
	 * @return time string
	 */
	public synchronized String getTime() {
		return fInput[0];
	}
	

	/**
	 * Gets car and prints it
	 * @return car string
	 */
	public synchronized String getCar() {
		return fInput[3];
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
				
				
				//mn.setfInput();
				sch.receiveAndEcho();
				sch.convertToString(data);
				
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
				long start= System.currentTimeMillis();
				long currentTime=start;
				long end= start + 30*1000;
				sch.RPC();
				if(currentTime>=end) {
					System.out.println("Error: Elevator did not reach floor");
					System.exit(1);
					
				}
				
				System.out.println();
				return "Moving Elevator";
			}
			
		},
		Repeat{
			
			public statemachine next() {
				return Fetch;
			}
			
			public String dowork() {
				System.out.println("person has been dropped, waiting on new instructions");
				fInput = new String[6];
				floorno = null;
				Direc = null;
				car = null;
				time = null;
				Dtime = null;
				Mtime = null;
				data = new byte[100];
				s = null;
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
	public synchronized static void StateMachine() {
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
	 * 
	 */
	public static void RPC() {
		
		byte[] free = "free".getBytes();
		
		try {
			sendPacket = new DatagramPacket(free, free.length, InetAddress.getLocalHost(), 5500);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println(new String(sendPacket.getData(),0,sendPacket.getLength()));
		try {
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] DATA1 = new byte[2];
		receivePacket1 = new DatagramPacket(DATA1, DATA1.length);
		
		try {
			receiveSocket1.receive(receivePacket1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(new String(receivePacket1.getData(),0,receivePacket.getLength()));
		
		if(receivePacket1.getData()[0] == (byte) 1 ) {
			
			try {
				sendPacket1 = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5502);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			try {
				sendSocket1.send(sendPacket1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(receivePacket1.getData()[1] == (byte) 1) {
			
			try {
				sendPacket1 = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 5502);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			
			try {
				sendSocket1.send(sendPacket1);
			} catch (IOException e) {
				e.printStackTrace();
			
			
		}
		}
		else{
			System.out.println("whoops elevators busy try again in a moment!");
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
		
		StateMachine();
		
//		Thread elv = new Elevator();
//		Thread Floor = new Floor_main();
//		Thread sch = new Scheduler();
//		
//		Floor.start();
//		elv.start();
//		sch.start();
		
		
	}
	
	
	
}
