package project_Group10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main extends Thread {

	public boolean empty= true;
	public Object[] data = new Object[6];
	
	/**
	 * This method is retrieving the data from the floor class via the scheduler. 
	 * It checks if the information was collected or not before attempting to collect it
	 */
		
	public synchronized void getInfo() {

			while(Elevator.info[0] == null && Elevator.info[1] == null && Elevator.info[2] == null && Elevator.info[3] == null) {
	            try {
	                System.out.println("Need Info first");
	                wait();
	            }catch (InterruptedException e) {
	                System.out.println(e);
	            }
	        }
			
	        System.out.println(Elevator.info[0]);
	        System.out.println(Elevator.info[1]);
	        System.out.println(Elevator.info[2]);
	        System.out.println(Elevator.info[3]);
	        
	        System.out.println();
	    }
	
	
	/**
	 * Generate the input file and store it in an Object array
	 */
	@SuppressWarnings("static-access")
	public synchronized  void inputFile() {

		BufferedReader reader;
//		try {
//			System.out.println("Getting data...");
//			reader=new BufferedReader(new FileReader("C:\\Users\\ahmad\\Documents\\InputFile.txt"));
//			String line = reader.readLine();
//			while(line !=null) {
//				//System.out.println(line);
//				for (int i=0;i<6;i++) {
//				data[i]=line;
//				line=reader.readLine();
//				Floor_main.input[i] = data[i];
//				System.out.println(Floor_main.input[i]);
//				}
//				//System.out.println(Arrays.toString(data));
//			}
//			reader.close();
//		}catch (IOException e) {
//			System.out.println("Error: File cannot be found");
//			e.printStackTrace();
//		}
//		Floor_main.b = new Button();
//		Floor_main.input[0]=Floor_main.b.getTime().toString();
//		Floor_main.input[1]= String.valueOf(Floor_main.b.currentFloor());
//		Floor_main.input[2] = Floor_main.b.direction();
//		Floor_main.input[3]=String.valueOf(Floor_main.car);
		
		
		//System.out.println(Arrays.toString(Floor_main.input));
		notifyAll();
		//return Floor_main.input;
	}
	
	
	/**
	 * This function sets the input file into an array of its own to be able to use it else where in the project
	 * 
	 * @param Inp  - which is input file
	 */
	public synchronized void setfInput() {
		
//		while(Floor_main.input[0] == null && Floor_main.input[1] == null && Floor_main.input[2] == null && Floor_main.input[3] == null) {
//			try {
//				System.out.println("null");
//				wait();
//			}catch(InterruptedException e) {
//				System.out.println(e);
//			}
//		}
		
		System.out.println("HELOEOOEOE");
		
		
		
//		Scheduler.floorno = (String) Floor_main.input[1];		// time/ floor / up/down / elevator no./ door timer/ motor timer
//		Scheduler.Direc = (String) Floor_main.input[2];
//		Scheduler.time = (String) Floor_main.input[0];
//		Scheduler.car = (String) Floor_main.input[3];
//		Scheduler.Dtime = (String) Floor_main.input[4];
//		Scheduler.Mtime = (String) Floor_main.input[5];
		
		
		Elevator.info[0] = Scheduler.time; 
		Elevator.info[1] = Scheduler.floorno;
		Elevator.info[2] = Scheduler.Direc; 
		Elevator.info[3] = Scheduler.car;
		
		
		Door.DTime = (String) Scheduler.Dtime;
		Motor.MTime = (String) Scheduler.Mtime;
		
		notifyAll();
	}
	
	
}
