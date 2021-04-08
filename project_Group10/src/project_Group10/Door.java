package project_Group10;

import java.util.concurrent.TimeUnit;



public class Door{
	private static long startTimeElev, currentTimeElev, endTimeElev,total; 
	public static boolean door = true;  	// Door closed = true
	public static String DTime;
	


	// gets door
	
	public static boolean getDo() {
		return door;
	}
	

	// opens door
	public static boolean openDoor() {
		
		if(door == true) {
			door = false;
			System.out.println("Opening door");
			if(Integer.parseInt(DTime)>9.52) {
				System.out.println("Error: Door did not open");
				System.out.println("Attempting to open door again");
				DTime="9.52";
			}
//			startTimeElev= System.currentTimeMillis();
//			endTimeElev= (long) (startTimeElev + ((9.52/2)));
			
			try {
				
				TimeUnit.SECONDS.sleep(Integer.parseInt(DTime)/2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			currentTimeElev=System.currentTimeMillis();
//			total=currentTimeElev-startTimeElev;
			}
			return true;
		}
	
	
	// closes door
	public static boolean closeDoor() {
		if(door == true) {
			System.out.println("Door already closed");
			return true;
		}else {
			
			System.out.println("Closing door");
			if(Integer.parseInt(DTime)>9.52) {
				System.out.println("Error: Door did not open");
				System.out.println("Attempting to open door again");
				DTime="9.52";
			}
//			startTimeElev= System.currentTimeMillis();
//			
//			endTimeElev= (long) (startTimeElev + ((9.52/2)));
			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(DTime)/2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			door = true;
			return true;
		}
	}


}
	

