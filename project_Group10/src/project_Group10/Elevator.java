package project_Group10;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

	public static String info[] = new String[4];
	public static String  Signal;
	public static Floor_main flr = new Floor_main();
	private static int currElev = 1;
	public static boolean moveit = false;
	
	public static DatagramPacket sendPacket, receivePacket;
	public static DatagramSocket sendreceiveSocket;
	public static byte[] message;

/**
 * This method returns to the scheduler class the results of the task complete. It updates the scheduler.
 */
public synchronized static void outPut() {
	
    System.out.println("we are currently on the "+ info[1]+"floor.\n");
    System.out.println("we are currently going"+ info[2]+"\n");
    System.out.println("the current time is "+ info[0]+"\n");
    System.out.println("we are currently in car number "+info[3]+"\n");

}

//not uses yet will be used later
public synchronized void getSignal() {

    while( Signal == "false") {
        try {
            System.out.println("nobdoy is using the elavator yet");
            wait();
        }catch (InterruptedException e) {
            System.out.println(e);
        }

        if (Signal== "True") {
             System.out.println("the elevator is heading to ur service");
        }
    }
     notifyAll();
}


public static void receiver() {
	message = new byte[100];
	receivePacket = new DatagramPacket(message, message.length);

	try {
		System.out.println("Waiting for packets...");
		// Block until a datagram is received via sendReceiveSocket.
		sendreceiveSocket.receive(receivePacket);
	} catch (IOException e) {
		e.printStackTrace();
		System.exit(1);
	}
	System.out.println("Task received");
	System.out.println("From: " + receivePacket.getAddress());
	System.out.println("Port: " + receivePacket.getPort());
	System.out.println("Containing: ");
	int len = receivePacket.getLength();
	len = receivePacket.getLength();

	String received = new String(message, 0, len);
	System.out.println("received");
	System.out.println("Bytes" + Arrays.toString(message));
}

public static void toCart(byte[] message) {// need the info
	try {
		sendPacket = new DatagramPacket(message, message.length, InetAddress.getLocalHost(), 23);
	} catch (UnknownHostException e) {
		e.printStackTrace();
		System.exit(1);
	}

	System.out.println("Scheduler: Sending message to Elevator...");
	System.out.println("To Elevator: " + sendPacket.getAddress());
	System.out.println("Destionation Elevator port: " + sendPacket.getPort());
	int len = sendPacket.getLength();
	System.out.println("Length: " + len);
	System.out.println("Containing: ");
	System.out.println("String: " + new String(sendPacket.getData(), 0, len));
	System.out.println("Bytes" + Arrays.toString(message));

	try {
		sendreceiveSocket.send(sendPacket);
	} catch (IOException e) {
		e.printStackTrace();
		System.exit(1);
		;
		System.out.println("Packet sent");
	}
}

/////////////////////////////////////////
//// State Machine setup/////////////////
/////////////////////////////////////////

public enum elevatorstatemch{
	getInfo{
		public elevatorstatemch next() {
			return Move;
		}
		
		public String dowork() {
			System.out.println("Getting informtion on where to go");
			flr.sch.mn.getInfo();
			outPut();
			System.out.println();
			return "Getting informtion on where to go";
		}
		
	},
	Move{

		public elevatorstatemch next() {
			return Move2;
		}

		public String dowork() {
			
			while(moveit == false){
			}
			
			System.out.println("Information received!");
			System.out.println("lets move elevator to desired location");
			
			if(Door.getDo() == false) {
				Door.closeDoor();
			}
			
			if(currElev < Integer.parseInt(Scheduler.floorno)) {
				System.out.println("moving down");
				Scheduler.mot.moveDown(Button.getfloorNum());
			}
			
			if(currElev > Integer.parseInt(Scheduler.floorno)) {
				System.out.println("moving up");
				Scheduler.mot.moveUp(Button.getfloorNum());
			}
			
			
			
			Scheduler.sen.sendSignal();
			Door.openDoor();
			Scheduler.sen.clearSignal();
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Door.closeDoor();
			
			System.out.println("Person secured inside elevator");
			System.out.println();
			return "Person secured inside elevator";
		}
		
	},
	Move2{

		public elevatorstatemch next() {
			return getInfo;
		}

		public String dowork() {
			
			
			if(Door.getDo() == false) {
				Door.closeDoor();
			}
			
			currElev = Button.getdestFloor();
			
			if(currElev < Integer.parseInt(Scheduler.floorno)) {
				Scheduler.mot.moveUp(Button.getdestFloor());
			}
			if(currElev > Integer.parseInt(Scheduler.floorno)) {
				Scheduler.mot.moveDown(Button.getdestFloor());
			}
			
			
			Scheduler.sen.sendSignal();
			Door.openDoor();
			Scheduler.sen.clearSignal();
			
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			Door.closeDoor();
			System.out.println("Now that person has been dropped off, job done");
			System.out.println();
			
			info = new String[4];
			moveit = false;
			
			return "Now that person has been dropped off, job done";
		}
			
	};
	
	public abstract elevatorstatemch next();
	public abstract String dowork();
	
}

// state machine to cycle through the states we have
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

// to get destination after person enters elevator
public static void  elevButton() {
	Button newbutton= new Button();
	newbutton.destFloor();
	currElev = newbutton.destFloor;
}
 /*
 * This method runs the elevator class subsystem.
 */
public void run(){
	
	StateMachine2();

    }

public static void main(String[] args) {
	
	byte[] info0 = info[0].getBytes();
	byte[] info1 = info[1].getBytes();
	byte[] info2 = info[2].getBytes();
	byte[] info3 = info[3].getBytes();
	
	toCart(info0);
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	toCart(info1);
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	toCart(info2);
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
	toCart(info3);
	try {
		TimeUnit.SECONDS.sleep(5);
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
}

}