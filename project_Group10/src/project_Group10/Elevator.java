package project_Group10;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
/**
 * This is the Elevator class subsystem. This class will run what the elevator will do with the given data from the floor. It will determine which floor to go to 
 * and will notify the scheduler when it has reached the appropriate floor. This class will also have buttons inside to indicate teh floor to go to
 * and will have lamps, however they are not implemented in this version. 
 * @author Ousama Al-chami
 */
import java.util.Random;
import java.util.concurrent.TimeUnit;

import project_Group10.ElevatorCart.elevatorstatemch;
import project_Group10.Scheduler.statemachine;

public class Elevator extends Thread {

	public static String info[] = new String[6];
	public String Signal2;
	public String return2;
	public String Signal1;
	public String return1;
	public static boolean moveit1 = true;
	public static boolean moveit2 = true;


	public static Floor_main flr = new Floor_main();

	public static DatagramPacket sendPacket, receivePacket, receivePacketElev, receivePacketElev1;
	public static DatagramSocket receiveSocket,sendSocket, EM, EMM;
	
	public static DatagramPacket elevPacket1, elevPacket12, elevPacket2, elevPacket3, elevPacket4;
	public static DatagramSocket elevSocket1, elevSocket12, elevSocket2, elevSocket3, elevSocket4;
	
	
	public static byte[] message;



	public static void toSched() {

		System.out.println("now waiting to receive information from Scheduler");

		byte[] free = new byte[4];
		receivePacket = new DatagramPacket(free, free.length);
		try {
			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		byte[] free2 = new byte[1];
		int x = 0;
		String s = new String(receivePacket.getData(), StandardCharsets.UTF_8);
		System.out.println(s);

		if(s.equals("free") ) {
			System.out.println("choosing elevator");
			System.out.println("elv1: " + ElevatorCart.cart1.status);
			System.out.println("elv2: " + ElevatorCart1.cart2.status);
			if (ElevatorCart.cart1.status == false) {
				String X = "1";
				free2 = new byte[1];
				free2 = X.getBytes();

				try {
					sendPacket = new DatagramPacket(free2, free2.length, InetAddress.getLocalHost(), 5500);

				} catch (UnknownHostException e) {
					e.printStackTrace();
					System.exit(1);
				}

				try {
					sendSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
				System.out.println(new String(sendPacket.getData(), StandardCharsets.UTF_8));
			}
			
			else if (ElevatorCart1.cart2.status == false) {
				String X = "2";
				free2 = new byte[1];
				free2 = X.getBytes();

				try {
					sendPacket = new DatagramPacket(free2, free2.length, InetAddress.getLocalHost(), 5500);

				} catch (UnknownHostException e) {
					e.printStackTrace();
					System.exit(1);
				}

				try {
					sendSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
				System.out.println(new String(sendPacket.getData(), StandardCharsets.UTF_8));
			}
			

		}
		else{
			String X = Integer.toString(0);
			free2 = X.getBytes();
			try {
				sendPacket = new DatagramPacket(free2, free2.length, InetAddress.getLocalHost(), 5500);

			} catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}

			try {
				sendSocket.send(sendPacket);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
			System.out.println(new String(sendPacket.getData(), StandardCharsets.UTF_8));
		}



		byte[] free3 = new byte[1];
		receivePacketElev = new DatagramPacket(free3, free3.length);

		try {
			EM.receive(receivePacketElev);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}

		String s2 = new String(receivePacketElev.getData(), StandardCharsets.UTF_8);
		System.out.println(s2);

		if(s2.equals("1") ) {
			byte[] Info = new byte[100];
			receivePacketElev1 = new DatagramPacket(Info, Info.length);
			try {
				EMM.receive(receivePacketElev1);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String Info1 = new String(receivePacketElev1.getData(), StandardCharsets.UTF_8);
			String[] Info2 = Info1.split(" ");
			info = Info2;
			//ElevatorCart.setDestFlr(Integer.parseInt(info[1]), Integer.parseInt(info[3]));
			//System.out.println(info[1]);
			//System.out.println(info[3]);
			//System.out.println(info[4]);
			//System.out.println(info[5]);
			//ElevatorCart.cart1.destFlr = Integer.parseInt(info[1]);
			//ElevatorCart.cart1.finalFlr = Integer.parseInt(info[3]);
			//ElevatorCart.cart1.DoorTime = info[4];
			//ElevatorCart.cart1.MotorTime = info[5];
			//carts[0].StateMachine2();
			byte[] go = "go".getBytes();
			try {
				elevPacket1 = new DatagramPacket(go, go.length, InetAddress.getLocalHost(), 510);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket1.send(elevPacket1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Elevator1 on the move");
			
			
			try {
				elevPacket12 = new DatagramPacket(receivePacketElev1.getData(), receivePacketElev1.getData().length, InetAddress.getLocalHost(), 511);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket12.send(elevPacket12);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(s2.equals("2")) {
			byte[] Info = new byte[100];
			receivePacketElev1 = new DatagramPacket(Info, Info.length);
			try {
				EMM.receive(receivePacketElev1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String Info1 = new String(receivePacketElev1.getData(), StandardCharsets.UTF_8);
			String[] Info2 = Info1.split(" ");
			info = Info2;
			//ElevatorCart1.setDestFlr(Integer.parseInt(info[1]), Integer.parseInt(info[3]));
			//System.out.println(info[4]);
			//System.out.println(info[5]);
			//ElevatorCart1.DoorTime = info[4];
			//ElevatorCart1.MotorTime = info[5];
			//carts[1].StateMachine2();
			byte[] go = "go".getBytes();
			try {
				elevPacket1 = new DatagramPacket(go, go.length, InetAddress.getLocalHost(), 520);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket2.send(elevPacket2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Elevator1 on the move");
			
			try {
				elevPacket12 = new DatagramPacket(receivePacketElev1.getData(), receivePacketElev1.getData().length, InetAddress.getLocalHost(), 521);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket12.send(elevPacket12);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("Whoops wrong information");
		}

	}


	public static void main(String[] args) {
		Elevator elv = new Elevator();
		try {
			receiveSocket = new DatagramSocket(550);
			sendSocket = new DatagramSocket();
			elevSocket1 = new DatagramSocket();
			elevSocket12 = new DatagramSocket();
			elevSocket2 = new DatagramSocket();
			elevSocket3 = new DatagramSocket();
			elevSocket4 = new DatagramSocket();
			EM = new DatagramSocket(5501);
			EMM = new DatagramSocket(5502);

		} catch(SocketException se) {
			se.printStackTrace();
			System.exit(1);
		} 
		while(true) {
			elv.toSched();
		}
	}
}