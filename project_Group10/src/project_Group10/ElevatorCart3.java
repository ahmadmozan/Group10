package project_Group10;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.text.DateFormat;
import java.util.Date;

import project_Group10.ElevatorCart.elevatorstatemch;


public class ElevatorCart3 extends Thread{

	//private DatagramPacket m1,EM1;
	private Lamp flrLamp = new Lamp();
	private Button flrButton;
	private static Door cartDoor = new Door();
	private int cartNumber;
	private static int  currFlr = 1;
	public static int  destFlr;
	public static int  finalFlr;
	public static byte[] signal;
	public static String DoorTime;
	public static String MotorTime;
	public static DatagramPacket receive, receive1;
	public static DatagramSocket receiveSocket, receiveSocket1;
	public static Motor mot = new Motor();
	public static boolean shutdown;
	public static boolean sCount ;

	
	public static String info[] = new String[6];
	
	public static CartMonitor cMon;
	

	public static OutputGui g2;
	
	public ElevatorCart3(CartMonitor cc,OutputGui g) {
		cMon = cc;
		g2 = g;
		 shutdown = false;
		 sCount = false;
	}
	
	/**
	 * This method returns to the scheduler class the results of the task complete. It updates the scheduler.
	 */
	public synchronized static void outPut() {

		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"we are currently on the "+ info[1]+" floor.]\n");
		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"we are currently going "+ info[2]+"]"+"\n");
		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"the current time is "+ info[0]+"]"+"\n");
		System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"we are heading to "+info[3]+"]"+"\n");

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
	      System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Waiting for task...]\n");

	      try {        
	         System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Waiting..."+"]"); // so we know we're waiting
	         receiveSocket.receive(receive);
	      } catch (IOException e) {
	         e.printStackTrace();
	         System.exit(1);
	      }

	      System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+receive.getData()+"]");
	      System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Task received. Moving now..."+"]");
	      
	      String s = new String(receive.getData(), StandardCharsets.UTF_8);
	      System.out.println(s);
	      
	      if(s.equals("go")) {
	    	  StateMachine2();
	      }
	      else {
	    	  System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"whoops something went wrong"+"]");
	      }
	      
	      // Slow things down (wait 5 seconds)
	      try {
	          Thread.sleep(1000);
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
		
		destFlr = Integer.parseInt(info[1]);
		finalFlr = Integer.parseInt(info[3]);
		DoorTime = info[4];
		MotorTime = info[5];
		
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
				g2.setFlr4("Floor: "+Integer.toString(currFlr));
				sCount=false;
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+cMon.getStatus4()+"]");
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Getting informtion on where to go"+"]");
				receive2();
				outPut();
				System.out.println();
				return "Getting informtion on where to go";
			}

		},
		Move {

			public elevatorstatemch next() {
				return Move2;
			}

			public String dowork() {

				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Information received!"+"]");
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"lets move elevator4 to desired location"+"]");
				
				mot.MTime = MotorTime;
				cartDoor.DTime = DoorTime;
				
				

				if (cartDoor.getDo() == false) {
					cartDoor.closeDoor();
				}
				

				if (currFlr > destFlr) {
					
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"moving down"+"]");
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+MotorTime+"]");
					g2.setStat4("Status:Moving down");
					mot.moveDown((currFlr-destFlr));
					if(mot.shutdown()==true) {
						g2.setStat4("Status:MOTOR ERROR");
						shutdown=true;
						sCount = true;
					}
					else {
						shutdown=false;
						currFlr = destFlr;
						g2.setFlr4("Floor: "+Integer.toString(currFlr));
						

						cartDoor.openDoor();
						
						cartDoor.closeDoor();
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+cMon.getStatus4()+"]");
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Person secured inside elevator4"+"]");
						System.out.println();
						return "Person secured inside elevator1";
					}
					
				}

				else if (currFlr < destFlr) {
					
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"moving up"+"]");
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+MotorTime+"]");
					g2.setStat4("Status:Moving up");
					mot.moveUp((destFlr-currFlr));
					if(mot.shutdown()==true) {
						g2.setStat4("Status:MOTOR ERROR");
						shutdown=true;
						sCount = true;
					}
					else {
						shutdown=false;
						currFlr = destFlr;
						g2.setFlr4("Floor: "+Integer.toString(currFlr));
						

						cartDoor.openDoor();
						
						cartDoor.closeDoor();
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+cMon.getStatus4()+"]");
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Person secured inside elevator4"+"]");
						System.out.println();
						return "Person secured inside elevator1";
					}
					
					
				}
				
	
				return "NA";
			}

		},
		Move2 {

			public elevatorstatemch next() {
				return getInfo;
			}
			
			public String dowork() {

				if (cartDoor.getDo() == false) {
					cartDoor.closeDoor();
				}
                
				 

				if (currFlr < finalFlr) {
					
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"moving up"+"]");
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+MotorTime+"]");
					g2.setStat4("Status:Moving up");
					mot.moveUp((finalFlr-currFlr));
					if(mot.shutdown()==true) {
						g2.setStat4("Status:MOTOR ERROR");
						shutdown=true;
						sCount = true;
						return "ERROR";
					}
					else {
						shutdown=false;
						currFlr = finalFlr;
						g2.setFlr4("Floor: "+Integer.toString(currFlr));
						
						cartDoor.openDoor();
						cartDoor.closeDoor();
						
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+cMon.getStatus4()+"]");
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Now that person has been dropped off, job done"+"]");
						System.out.println();
						g2.setStat4("Status:IDLE");
						
						sCount = true;
						cMon.setFalse4();
						return "Now that person has been dropped off, job done";
					}
					
				}
				else if (currFlr > finalFlr) {
					
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"moving down"+"]");
					System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+MotorTime+"]");
					g2.setStat4("Status:Moving down");
					mot.moveDown((currFlr-finalFlr));
					if(mot.shutdown()==true) {
						g2.setStat4("Status:MOTOR ERROR");
						shutdown=true;
						sCount = true;
						return "ERROR";
					}
					else {
						shutdown=false;
						currFlr = finalFlr;
						g2.setFlr4("Floor: "+Integer.toString(currFlr));
						
						cartDoor.openDoor();
						cartDoor.closeDoor();
						
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+cMon.getStatus4()+"]");
						System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Now that person has been dropped off, job done"+"]");
						System.out.println();
						g2.setStat4("Status:IDLE");
						
						sCount = true;
						cMon.setFalse4();
						return "Now that person has been dropped off, job done";
					}

				}

				
				
				return "NA";
			}

		};

		public abstract elevatorstatemch next();
		public abstract String dowork();
	}
	
	

	//state machine to cycle through the states we have
		public synchronized static void StateMachine2() {
			elevatorstatemch state = elevatorstatemch.getInfo;
			cMon.setTrue4();
			while(true) {
				state.dowork();
				if(sCount == true) {
					break;
				}
//				if(shutdown=true) {
//					break;
//				}
				state = state.next();
				
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}

		public void run() {
			try {
				receiveSocket = new DatagramSocket(540);
				receiveSocket1 = new DatagramSocket(541);
			} catch (SocketException e) {
				e.printStackTrace();
			}
			while (true) {
				if(shutdown==true) {break;}
				
				receive();	
			}
		}
		

}
