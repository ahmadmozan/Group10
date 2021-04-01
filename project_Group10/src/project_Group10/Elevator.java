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
	public static byte[] message;
	public String Information1[];
	public String Information2[];
	private static ElevatorCart[] carts;

/**
 * This method returns to the scheduler class the results of the task complete. It updates the scheduler.
 */
public synchronized static void outPut() {
	
    System.out.println("we are currently on the "+ info[1]+"floor.\n");
    System.out.println("we are currently going"+ info[2]+"\n");
    System.out.println("the current time is "+ info[0]+"\n");
    System.out.println("we are currently in car number "+info[3]+"\n");

}


public  Elevator(int i) {
	
	carts = new ElevatorCart[i];
	
	for (int x=0; x<i; x++) {
		
		carts[x] = new ElevatorCart(x);
	}
}


public static void toSched() {
	try {
		receiveSocket = new DatagramSocket(550);
		sendSocket = new DatagramSocket();
		EM = new DatagramSocket(5501);
		EMM = new DatagramSocket(5502);
		
	} catch(SocketException se) {
        se.printStackTrace();
        System.exit(1);
     } 
	
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
	
	if(s == "free") {
		for (int i=0; i<carts.length; i++) {
			if(carts[i].cartStatus() == false) {
				x++;
			}
		}
		
		free2[0] = (byte) x;
		try {
			sendPacket = new DatagramPacket(free2, free2.length,InetAddress.getLocalHost(),5000);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			sendSocket.send(sendPacket);
		}catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
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
	if(s2 == "1") {
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
		carts[0].setDestFlr(Integer.parseInt(info[1]), Integer.parseInt(info[3]));
	}
	else if(s2 == "2") {
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
		carts[1].setDestFlr(Integer.parseInt(info[1]), Integer.parseInt(info[3]));
		
	}
	else {
		System.out.println("Whoops wrong information");
	}
	
}


public void run(){
	
	//flr.sch.mn.getInfo();
	//udp call

    }

public static void main(String[] args) {
	
	Elevator elv = new Elevator(2);
	elv.toSched();
}

}