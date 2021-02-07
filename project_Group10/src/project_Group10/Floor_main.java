package project_Group10;
/**
 * This is the Floor subsystem class. It runs the floor subsystem which is responsible for reading the input file and sending the data to the 
 * scheduler for the elevator to use. This class will also be responsible for the floor lamps and buttons, but is not included in this version. This version, v1,
 * shows the communication from the Floor class to the elevator class via the scheduler. 
 * 
 * @author Akkash Anton Amalarajah 101102787
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Floor_main extends Thread {
	
	public static String[] data= new String[4];
	public boolean empty = true;
	static String[] output= new String[4];
	/** 
	 * This method is used to collect the data from the provided input file. It collects the data and stores it in a String Array.
	 * 
	 */
	public synchronized void getData() {
		
		for(int x = 0; x < data.length; x++) {
			if(data[x] !=null) {
				empty = false;
				break;
			}
		}
		BufferedReader reader;
		while(empty == false) {
            try {
                System.out.println("Input File is currently full");
                wait();
            }catch (InterruptedException e) {
                System.out.println(e);
            }
        }
		try {
			System.out.println("Getting data...");//Startreading input file to get data
			reader=new BufferedReader(new FileReader("C:\\Users\\ahmad\\Documents\\InputFile.txt"));
			String line = reader.readLine();
			while(line !=null) {
				for (int i=0;i<4;i++) {
				data[i]=line;
				line=reader.readLine();
				}	
			}
			notifyAll();
			reader.close();
		}catch (IOException e) {
			System.out.println("Error: File cannot be found");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method to work with multiple input entries for multiple elevators. Still in development for v1.
	 * @return String output
	 */
	public synchronized static String[] getArray() {
		output[0]=data[0];
		output[1]=data[1];
		output[2]=data[2];
		output[3]=data[3];
		
		//System.out.println(output);
		return output;	
	}
	/**
	 * Main run method to run the floor class subsystem.
	 */
	public synchronized void run() {
		getData();
	}
		
}
