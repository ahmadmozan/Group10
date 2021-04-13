package project_Group10;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import project_Group10.ElevatorCart.elevatorstatemch;
import project_Group10.Scheduler.statemachine;

/**
This is the Elevator class subsystem. This class will run what the elevator will do with the given data from the floor. It will determine which floor to go to 
* and will notify the scheduler when it has reached the appropriate floor. This class will also have buttons inside to indicate teh floor to go to
* and will have lamps, however they are not implemented in this version. 
* @author Ousama Al-chami
*/
public class Elevator extends Thread {

	public static String info[] = new String[6];
	public String Signal2;
	public String return2;
	public String Signal1;
	public String return1;

	public static Floor_main flr = new Floor_main();

	public static DatagramPacket sendPacket, receivePacket, receivePacketElev, receivePacketElev1;
	public static DatagramSocket receiveSocket,sendSocket, EM, EMM;
	
	public static DatagramPacket elevPacket1, elevPacket12, elevPacket2, elevPacket22, elevPacket3, elevPacket32, elevPacket4, elevPacket42;
	public static DatagramSocket elevSocket1, elevSocket12, elevSocket2, elevSocket22, elevSocket3, elevSocket32, elevSocket4, elevSocket42;
	
	
	public static byte[] message;
	public static CartMonitor cMon;
	public static OutputGui g;



	public  Elevator() {
		cMon = new CartMonitor();
		try {
			receiveSocket = new DatagramSocket(550);
			sendSocket = new DatagramSocket();
			elevSocket1 = new DatagramSocket();
			elevSocket12 = new DatagramSocket();
			elevSocket2 = new DatagramSocket();
			elevSocket22 = new DatagramSocket();
			elevSocket3 = new DatagramSocket();
			elevSocket32 = new DatagramSocket();
			elevSocket4 = new DatagramSocket();
			elevSocket42 = new DatagramSocket();
			EM = new DatagramSocket(5501);
			EMM = new DatagramSocket(5502);

		} catch(SocketException se) {
			se.printStackTrace();
			System.exit(1);
		} 
	}
	
	/**
	 * Using UDP to receive information from scheduler and send the information required by elevator back 
	 */
	public synchronized static void toSched() {

		System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"now waiting to receive information from Scheduler"+"]");

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
		System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+s+"]");

		if(s.equals("free") ) {
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"choosing elevator"+"]");
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"elv1: " + cMon.getStatus1()+"]");
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"elv2: " + cMon.getStatus2()+"]");
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"elv3: " + cMon.getStatus3()+"]");
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"elv4: " + cMon.getStatus4()+"]");
			if (cMon.getStatus1() == false) {
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
				System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(sendPacket.getData(), StandardCharsets.UTF_8)+"]");
			}
			else if (cMon.getStatus2() == false) {
				String X = "2";
				free2 = new byte[1];
				free2 = X.getBytes();
				CartMonitor.setTrue2();
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
				System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(sendPacket.getData(), StandardCharsets.UTF_8)+"]");
			}
			else if (cMon.getStatus3() == false) {
				String X = "3";
				free2 = new byte[1];
				free2 = X.getBytes();
				CartMonitor.setTrue2();
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
				System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(sendPacket.getData(), StandardCharsets.UTF_8)+"]");
			}
			else if (cMon.getStatus4() == false) {
				String X = "4";
				free2 = new byte[1];
				free2 = X.getBytes();
				CartMonitor.setTrue2();
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
				System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(sendPacket.getData(), StandardCharsets.UTF_8)+"]");
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
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+new String(sendPacket.getData(), StandardCharsets.UTF_8)+"]");
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
		//System.out.println(s2);

		if(s2.equals("1") ) {
			byte[] Info = new byte[100];
			receivePacketElev1 = new DatagramPacket(Info, Info.length);
			try {
				EMM.receive(receivePacketElev1);
			} catch (IOException e) {
				e.printStackTrace();
			}

			String Info1 = new String(receivePacketElev1.getData(), StandardCharsets.UTF_8);
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+Info1+"]");
			String[] Info2 = Info1.split(" ");
			info = Info2;
			//System.out.println(info[1]);
			//System.out.println(info[3]);
			//System.out.println(info[4]);
			//System.out.println(info[5]);
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
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Elevator1 on the move"+"]");
			
			
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
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+Info1+"]");
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
				elevPacket2 = new DatagramPacket(go, go.length, InetAddress.getLocalHost(), 520);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket2.send(elevPacket2);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Elevator2 on the move"+"]");
			
			try {
				elevPacket22 = new DatagramPacket(receivePacketElev1.getData(), receivePacketElev1.getData().length, InetAddress.getLocalHost(), 521);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket22.send(elevPacket22);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(s2.equals("3")) {
			byte[] Info = new byte[100];
			receivePacketElev1 = new DatagramPacket(Info, Info.length);
			try {
				EMM.receive(receivePacketElev1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String Info1 = new String(receivePacketElev1.getData(), StandardCharsets.UTF_8);
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+Info1+"]");
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
				elevPacket3 = new DatagramPacket(go, go.length, InetAddress.getLocalHost(), 530);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket3.send(elevPacket3);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Elevator3 on the move"+"]");
			
			try {
				elevPacket32 = new DatagramPacket(receivePacketElev1.getData(), receivePacketElev1.getData().length, InetAddress.getLocalHost(), 531);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket32.send(elevPacket32);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(s2.equals("4")) {
			byte[] Info = new byte[100];
			receivePacketElev1 = new DatagramPacket(Info, Info.length);
			try {
				EMM.receive(receivePacketElev1);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String Info1 = new String(receivePacketElev1.getData(), StandardCharsets.UTF_8);
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+Info1+"]");
			String[] Info2 = Info1.split(" ");
			info = Info2;
			
			//System.out.println(info[4]);
			//System.out.println(info[5]);
			
			byte[] go = "go".getBytes();
			try {
				elevPacket4 = new DatagramPacket(go, go.length, InetAddress.getLocalHost(), 540);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket4.send(elevPacket4);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Elevator4: on the move"+"]");
			
			try {
				elevPacket42 = new DatagramPacket(receivePacketElev1.getData(), receivePacketElev1.getData().length, InetAddress.getLocalHost(), 541);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			}
			try {
				elevSocket42.send(elevPacket42);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		else {
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Whoops wrong information"+"]");
		}

	}

	
	public synchronized void run() {
		
		while(true) {
			toSched();
		}
	}

	public synchronized static void main(String[] args) {

		
	
		Elevator elvMain = new Elevator();
		OutputGui g1 =new OutputGui();
		g1.frm();
		ElevatorCart elv1 = new ElevatorCart(cMon,g1);
		ElevatorCart1 elv2 = new ElevatorCart1(cMon,g1);
		ElevatorCart2 elv3 = new ElevatorCart2(cMon,g1);
		ElevatorCart3 elv4 = new ElevatorCart3(cMon,g1);
		
		elvMain.start();
		elv1.start();
		elv2.start();
		elv3.start();
		elv4.start();
	}
}