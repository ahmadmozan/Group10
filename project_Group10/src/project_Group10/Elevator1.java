package project_Group10;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;


public class Elevator1 {
	
	private DatagramPacket sendPacket, receivePacket,rp;
	private DatagramSocket sendReceiveSocket,correctinfo;
	public String mode;
	public byte Information[];
	ByteArrayOutputStream Data= new ByteArrayOutputStream();
	public String Reading;
	public String Signal1;
	public String return1;
	
	
	public Elevator1() {
		try {
			sendReceiveSocket= new DatagramSocket();
			correctinfo=new DatagramSocket(8888);
		}catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
			
		}
	}
	
	
	public void sendAndReceieve() {
		try {
			Reading ="im busy at the moment"; 
			byte string[]=Reading.getBytes();
			mode="PASS";
			byte string1[] =mode.getBytes();
			
			Data.write(string);
			Data.write( (byte) 0);
			Data.write( (byte) 1);
			Data.write( string1);
			Data.write( (byte) 0);
			
			
			
			Information= Data.toByteArray();
		
			
				sendPacket=new DatagramPacket(Information,Information.length ,InetAddress.getLocalHost(),9999);
			}catch (UnknownHostException e) {
				e.printStackTrace();
				System.exit(1);
			}
		catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
			
			System.out.println("elevator : in on elevator : ");
			System.out.println("To host: "+ sendPacket.getAddress());
			System.out.println("Port"+sendPacket.getPort());
			int l=sendPacket.getLength();
			System.out.println("the Length "+l+"has "+new String(sendPacket.getData(),0,l));
			
			try {
				this.sendReceiveSocket.send(this.sendPacket);
			}catch(IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		//	System.out.println("the Client's Packet has been sent ");
			
			byte rightpath[]= new byte[1111];
				try {
					rp= new DatagramPacket(rightpath,rightpath.length);
					correctinfo.receive(rp);
				}catch(IOException e) {
					e.printStackTrace();
					System.exit(1);
				}
				String inform=new String(rp.getData(),0,rightpath.length);
				System.out.println(inform);
			
		
			byte Rin[] =new byte[11111];//Rin short for received information
			receivePacket= new DatagramPacket(Rin,Rin.length);
			try {
				this.sendReceiveSocket.receive(receivePacket);
			}catch(IOException e) {
				e.printStackTrace();
				sendReceiveSocket.close();
				System.exit(l);
			}
			
			System.out.println("elevator : Receiving the data: ");
			System.out.println("To host: "+ receivePacket.getAddress());
			System.out.println("Port"+receivePacket.getPort());
			 l=receivePacket.getLength();
			System.out.println("the Length "+l+"has "+new String(Rin,0,l)+" ");
	}

	public synchronized void getSignal() {

	    while( Signal1 == "false") {
	        try {
	            System.out.println("nobdoy is using the elavator yet");
	            wait();
	        }catch (InterruptedException e) {
	            System.out.println(e);
	        }

	        if (Signal1 == "True") {
	             System.out.println("the elevator is heading to ur service");
	        }
	    }
	     notifyAll();
	}
	
	public synchronized void returnSignal() {

	    while( return1 == "false") {
	        try {
	            System.out.println("nobdoy is using the elavator yet");
	            wait();
	        }catch (InterruptedException e) {
	            System.out.println(e);
	        }

	        if (return1 == "True") {
	             System.out.println("the elevator is heading to ur service");
	        }
	    }
	     notifyAll();
	}

		
		
		public static void main(String args[]) {
			Elevator1 newe=new Elevator1();
			newe.sendAndReceieve();
			System.exit(1);
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
