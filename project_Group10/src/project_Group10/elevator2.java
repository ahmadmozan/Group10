package project_Group10;


	import java.io.*;
	import java.net.*;
	import java.nio.file.Files;
	import java.nio.file.Paths;
	import java.io.ByteArrayOutputStream;


	public class elevator2 {
		
		private DatagramPacket sendPacket, receivePacket,rp;
		private DatagramSocket sendReceiveSocket,correctinfo;
		public String mode;
		public String Reading2;
		public byte Information[];
		ByteArrayOutputStream Data= new ByteArrayOutputStream();
		public String Signal2;
		public String return2;
		
		public elevator2() {
			try {
				sendReceiveSocket= new DatagramSocket();
				correctinfo=new DatagramSocket(9988);
			}catch (SocketException se) {
				se.printStackTrace();
				System.exit(1);
				
			}
		}
		
		
		public void sendAndReceieve() {
			try {
				Reading2="other elevator wasbusy so this is in use";
				mode="PASS";
				byte string2[] =mode.getBytes();
				byte string3[] =Reading2.getBytes();
				
				Data.write(string3);
				Data.write( (byte) 0);
				Data.write( (byte) 1);
				Data.write( string2);
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
				
				System.out.println("elevator2 : in on elevator2 : ");
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
				
				System.out.println("elevator2 : Receiving the data: ");
				System.out.println("To host: "+ receivePacket.getAddress());
				System.out.println("Port"+receivePacket.getPort());
				 l=receivePacket.getLength();
				System.out.println("the Length "+l+"has "+new String(Rin,0,l)+" ");
		}
		
		
		public synchronized void getSignal2() {

		    while( Signal2 == "false") {
		        try {
		            System.out.println("nobdoy is using the elavator yet");
		            wait();
		        }catch (InterruptedException e) {
		            System.out.println(e);
		        }

		        if (Signal2 == "True") {
		             System.out.println("the second elevator is heading to ur service");
		        }
		    }
		     notifyAll();
		}
		
		public synchronized void returnSignal2() {

		    while( return2 == "false") {
		        try {
		            System.out.println("nobdoy is using the elavator 1 or 2");
		            wait();
		        }catch (InterruptedException e) {
		            System.out.println(e);
		        }

		        if (return2 == "True") {
		             System.out.println("the second elevator is heading to ur service");
		        }
		    }
		     notifyAll();
		}


			
			
			public static void main(String args[]) {
				elevator2 newe2=new elevator2();
				newe2.sendAndReceieve();
				System.exit(1);
			}
		
		
}
