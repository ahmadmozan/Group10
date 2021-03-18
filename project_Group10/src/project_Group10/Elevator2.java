package project_Group10;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;

public class Elevator2 {

	private DatagramPacket sendPacket, receivePacket, rp;
	private DatagramSocket sendReceiveSocket, correctinfo;
	public String mode;
	public String Reading2;
	public String Information[];
	ByteArrayOutputStream Data = new ByteArrayOutputStream();
	public String Signal2;
	public String return2;

	public Elevator2() {
		try {
			sendReceiveSocket = new DatagramSocket();
			correctinfo = new DatagramSocket(9988);
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);

		}
	}

	public void Receieve() {
		int messagesProcessed = 0;

		try {
			DatagramSocket socket = new DatagramSocket(23); // Creates socket bound to port 69

			for (int i = 0; i < 4; i++) {

				byte[] requestByteArray = "request".getBytes();
				boolean receieved = false; // defines a flag to check for receieving a actual packet vs a nothing to
											// report packet ("null")
				DatagramPacket recievedPacket = new DatagramPacket(new byte[4], 17); // Creates a packet to recieve into
				DatagramPacket requestPacket = new DatagramPacket(requestByteArray, requestByteArray.length,
						InetAddress.getLocalHost(), 22);

				while (!receieved) { // Loop until a non null packet is recieved
//						printPacket(requestPacket, true);
					socket.send(requestPacket); // Send a request to the intermediate server
					socket.receive(recievedPacket); // Receive the response
//						printPacket(recievedPacket, false);
					if (!(new String(recievedPacket.getData()).trim().equals("NA"))) {// If the response is not null,
																						// ie. a actual response
						receieved = true; // Break out of loop
					}
					Thread.sleep(1000);
				}

				byte[] dataArray = recievedPacket.getData(); // get the data from the packet to analyze
				if (dataArray[0] != 0 | dataArray[0] != 1) { // If the prefix is not invalid
					throw new IOException("Bad Packet (Invalid Request)"); // Get the length of the first string
				}

				Information[i] = new String(dataArray);

			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	public synchronized void getSignal2() {

		while (Signal2 == "false") {
			try {
				System.out.println("nobdoy is using the elavator yet");
				wait();
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			if (Signal2 == "True") {
				System.out.println("the second elevator is heading to ur service");
			}
		}
		notifyAll();
	}

	public synchronized void returnSignal2() {

		while (return2 == "false") {
			try {
				System.out.println("nobdoy is using the elavator 1 or 2");
				wait();
			} catch (InterruptedException e) {
				System.out.println(e);
			}

			if (return2 == "True") {
				System.out.println("the second elevator is heading to ur service");
			}
		}
		notifyAll();
	}

	public static void main(String args[]) {
		Elevator2 newe2 = new Elevator2();
		while (true) {
			newe2.Receieve();
		}
	}

}
