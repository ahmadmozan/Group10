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
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.text.DateFormat;
import java.util.Date;

/**
 * This class is about a scheduler subsystem that utilizes other subsystems. It is responsible for taking the input file the
 * Floor subsystem produces and pushes that file to the other subsystems. This class will be responsible for timing all the 
 * elevator movements and sending signals to doors, motor, lamps etc. 
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
	public static long startTime;
	public static long totalProTime=0;
	
	public static Motor mot = new Motor();
	public static Sensor sen = new Sensor();
	
	public static int currElev = 1;

	
	
	public static DatagramSocket sendSocket, receiveSocket, sendSocket1, receiveSocket1;
	public static DatagramPacket sendPacket, receivePacket;
	public static DatagramPacket sendPacket1, receivePacket1;
	public static DatagramPacket sendPacketElev;
	public static DatagramSocket sendSocketElev;
	public static byte[] data = new byte[100];
	public static String s = null;
	static Scheduler sch = new Scheduler();

		/*
		 * this is the initialization code for receiving the packet from Floor_main
		*/
		public Scheduler() {
			try {
		         sendSocket = new DatagramSocket();
		         sendSocket1 = new DatagramSocket();
		         sendSocketElev = new DatagramSocket();
		         receiveSocket = new DatagramSocket(5000);
		         receiveSocket1 = new DatagramSocket(5500);
		      } catch (SocketException se) {
		         se.printStackTrace();
		         System.exit(1);
		      } 
		}
		
		/**
		 * code to receive the input file stuff 
		 */
		 public static void receiveAndEcho()
		   {
		      /**
		       *  Construct a DatagramPacket for receiving packets up 
		       *   to 100 bytes long (the length of the byte array).
		       */
		      

		      data = new byte[100];
		      receivePacket = new DatagramPacket(data, data.length);
		      System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Waiting for input file.]\n");

		      try {        
		         System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Waiting..."+"]"); // so we know we're waiting
		         receiveSocket.receive(receivePacket);
		      } catch (IOException e) {
		         e.printStackTrace();
		         System.exit(1);
		      }
		      /**
		       *  Process the received datagram.
		       */
		      System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Obtained input file information"+"]");
		      System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+receivePacket.getData()+"]");
		      
		      /**
		       *  Using sleep to slow things down (wait 5 seconds)
		       */
		      try {
		          Thread.sleep(1000);
		      } catch (InterruptedException e ) {
		          e.printStackTrace();
		          System.exit(1);
		      }
		   }
		 
		 
		 /**
		  * this code converts the data from the received byte array into a string and stores it in the fInput string array
		  * @param data
		  * @return
		  */
		 public static String[] convertToString(byte[] data) 
		 {
			 s = new String(data, StandardCharsets.UTF_8);
			 System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+s+"]");
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
				
				startTime = System.nanoTime();
                for(int i=0; i< 1000000; i++){
                    Object obj = new Object();
                }
				
				//mn.setfInput();
				sch.receiveAndEcho();
				sch.convertToString(data);
				
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Information has been submitted, moving elevator next"+"]");
				
				System.out.println();
				return "Information has been submited, moving elevator next";
			}
			
		},
		SendElevator{
			
			public statemachine next() {
				return Repeat;
			}
			
			public String dowork() {
				
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Moving Elevator"+"]");
				long start= System.currentTimeMillis();
				long currentTime=start;
				long end= start + 30*1000;
				sch.RPC();
				if(currentTime>=end) {
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Error: Elevator did not reach floor"+"]");
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
			/**
			 * Initializing all main inputs at the beginning of the program
			 */
			 
			public String dowork() {
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Elevator on the move, waiting on new instructions"+"]");
				fInput = new String[6];
				floorno = null;
				Direc = null;
				car = null;
				time = null;
				Dtime = null;
				Mtime = null;
				data = new byte[100];
				s = null;
				
				long elapsedTime = System.nanoTime() - startTime;
				System.out.println("Total time in millis: "
                        + elapsedTime/1000000);

                totalProTime+=elapsedTime/1000000;

                System.out.println("the final time the Scheduler took to run in seconds : "+totalProTime/1000);
				
				System.out.println();
				return "Elevator on the move, waiting on new one";
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
	 * Sending and receiving packets protocol between the main elevator class and scheduler to check which elevator is free
	 */
	public static void RPC() {
		
		byte[] free = "free".getBytes();
		
		try {
			sendPacket = new DatagramPacket(free, free.length, InetAddress.getLocalHost(), 550);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(sendPacket.getData(),0,sendPacket.getLength())+"]");
		System.out.println("["+LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Checking which elevator is free"+"]");
		
		try {
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		byte[] DATA1 = new byte[1];
		receivePacket1 = new DatagramPacket(DATA1, DATA1.length);
		
		try {
			receiveSocket1.receive(receivePacket1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(receivePacket1.getData(),0,receivePacket1.getLength())+"]");
		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Now we command that elevator that was free"+"]");
		
		if(new String(receivePacket1.getData(),StandardCharsets.UTF_8).equals("1") ) {
			
			byte[] Elev = new byte[1];
			String X2 = "1";
			Elev = X2.getBytes();
			
			try {
				sendPacketElev = new DatagramPacket(Elev, Elev.length, InetAddress.getLocalHost(), 5501);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			try {
				sendSocketElev.send(sendPacketElev);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			
			
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
				System.exit(1);
			}
			
		}
		else if(new String(receivePacket1.getData(),StandardCharsets.UTF_8).equals("2")) {

			byte[] Elev = new byte[1];
			String X2 = "2";
			Elev = X2.getBytes();
			
			try {
				sendPacketElev = new DatagramPacket(Elev, Elev.length, InetAddress.getLocalHost(), 5501);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			try {
				sendSocketElev.send(sendPacketElev);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			
			
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
		else if(new String(receivePacket1.getData(),StandardCharsets.UTF_8).equals("3")) {

			byte[] Elev = new byte[1];
			String X2 = "3";
			Elev = X2.getBytes();
			
			try {
				sendPacketElev = new DatagramPacket(Elev, Elev.length, InetAddress.getLocalHost(), 5501);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			try {
				sendSocketElev.send(sendPacketElev);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			
			
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
		else if(new String(receivePacket1.getData(),StandardCharsets.UTF_8).equals("4")) {

			byte[] Elev = new byte[1];
			String X2 = "4";
			Elev = X2.getBytes();
			
			try {
				sendPacketElev = new DatagramPacket(Elev, Elev.length, InetAddress.getLocalHost(), 5501);
			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
			try {
				sendSocketElev.send(sendPacketElev);
			} catch (IOException e1) {
				e1.printStackTrace();
				System.exit(1);
			}
			
			
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
			System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"whoops elevators busy try again in a moment!"+"]");
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
