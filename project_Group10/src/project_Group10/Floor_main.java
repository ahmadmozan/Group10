package project_Group10;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Floor_main extends Thread {
	
	static String[] data= new String[6];
	
	private boolean empty = true;
	public Object contents = null;
	public static Thread floor;
	public  DatagramPacket sendPacket, receivePacket;
	public DatagramSocket sendReceiveSocket;
	static Lamp l;
	public Door Floor;
	public static Scheduler sch =  new Scheduler();
	
	public static int car=1;
	static Button b;
	static byte[] info;
	//public static Object[] input = new Object[6];

	//public static byte[] info=new byte[6];
	
	
	
	
	
	
	
	public Floor_main() {

	      try {
	         sendReceiveSocket = new DatagramSocket();
	      } catch (SocketException se) {   // Can't create the socket.
	         se.printStackTrace();
	         System.exit(1);
	      }
	}
	public void sendAndReceive()
	   {
	 
	      
	     // byte msg[]=output.getBytes();

	     
	      try {
	         sendPacket = new DatagramPacket(info, info.length,
	                                         InetAddress.getLocalHost(), 5000);
	      } catch (UnknownHostException e) {
	         e.printStackTrace();
	         System.exit(1);
	      }
	      int len = sendPacket.getLength();
	      System.out.print("Containing: ");
	      System.out.println(new String(sendPacket.getData(),0,len)); // or could print "s"

	      // Send the datagram packet to the server via the send/receive socket. 

	      try {
	         sendReceiveSocket.send(sendPacket);
	      } catch (IOException e) {
	         e.printStackTrace();
	         System.exit(1);
	      }

	      System.out.println("Information sent to Scheduler\n");
	      sendReceiveSocket.close();
	   }
	public static void main(String[] args) {
		
		System.out.println("creating threads...");
		floor=new Thread();
		floor.start();
		getData();
		//getArray();
		String[] op = data;
	    for(String str : op)
	        System.out.println(str);
	    convertToByte(data);
	}
	public static void getData() {
		BufferedReader reader;
		try {
			System.out.println("Getting data...");
			reader=new BufferedReader(new FileReader("C:\\Users\\akkas\\Desktop\\file.txt"));
			String line = reader.readLine();
			while(line !=null) {
				//System.out.println(line);
				for (int i=0;i<6;i++) {
				data[i]=line;
				line=reader.readLine();
				
				}
				//System.out.println(Arrays.toString(data));
				
				
			}
			reader.close();
		}catch (IOException e) {
			System.out.println("Error: File cannot be found");
			e.printStackTrace();
		}
	}
	
	
	
	
	public static byte[] convertToByte(String[] data) {
		for(int i=0;i<data.length;i++) {
			String str=data[i];
			info=str.getBytes();
			System.out.println(info);
			
		}
		return info;
	}

//	public void lamps() {
//		b= new Button();
//		l = new Lamp();
//		l.getlu(Button.direction().equals("Up");
//		
//	}
	public boolean door() {
		Floor= new Door();
		boolean openClose;
		if (Door.openDoor()==true) {
			openClose=true;
			System.out.println("The doors are opening");
		}
		else {
			openClose=false;
			System.out.println("The doors are closing");
		}
		return openClose;
	}
	
	/**
	 * Main run method to run the floor class subsystem.
	 */
	public synchronized void run() {
		sch.mn.inputFile();
	}
	
}
