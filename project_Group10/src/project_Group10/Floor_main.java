//package project_Group10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Floor_main extends Thread {
	
	public static String[] data= new String[4];
	static String[] output= new String[4];
	public static Thread floor;
	
	public synchronized void getData() {
		BufferedReader reader;
		try {
			System.out.println("Getting data...");
			reader=new BufferedReader(new FileReader("C:\\Users\\ahmad\\Documents\\InputFile.txt"));
			String line = reader.readLine();
			while(line !=null) {
				for (int i=0;i<4;i++) {
				data[i]=line;
				line=reader.readLine();
				}
				while(data != null) {
		            try {
		                System.out.println("Input File is currently full");
		                wait();
		            }catch (InterruptedException e) {
		                System.out.println(e);
		            }
		        }
		        notifyAll();
				
				
				
			}
			reader.close();
		}catch (IOException e) {
			System.out.println("Error: File cannot be found");
			e.printStackTrace();
		}
	}
	
	
	
	public synchronized static String[] getArray() {
		output[0]=data[0];
		output[1]=data[1];
		output[2]=data[2];
		output[3]=data[3];
		
		//System.out.println(output);
		return output;	
	}
	
	public void run() {
		getData();
	}
		
}
