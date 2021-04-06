package project_Group10;

import java.util.concurrent.TimeUnit;

public class Door{
	
	public static boolean door = true;  	// Door closed = true
	public static String DTime;
	private static Elevator ElevaD;
	


	// gets door
	
	public static boolean getDo() {
		return door;
	}
	

	// opens door
	public static boolean openDoor() {
		DTime= ElevaD.info[4];
		
		if(door == true) {
			door = false;
			System.out.println("opening door");
			try {
				
				TimeUnit.SECONDS.sleep(Integer.parseInt(DTime)/2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}else {
			return false;
		}
	}
	
	// closes door
	public static boolean closeDoor() {
		if(door == true) {
			System.out.println("door already closed");
			return true;
		}else {
			door = true;
			System.out.println("closing door");
			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(DTime)/2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return true;
		}
	}


}
	

