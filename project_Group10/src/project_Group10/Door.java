package project_Group10;

public class Door{
	
	public static boolean door = true;  	// Door closed = true


	// gets door
	
	public static boolean getDo() {
		return door;
	}
	

	// opens door
	public static boolean openDoor() {
		if(door == true) {
			door = false;
			return true;
		}else {
			return false;
		}
	}
	
	// closes door
	public static boolean closeDoor() {
		if(door == true) {
			return false;
		}else {
			door = false;
			return true;
		}
	}


}
	

