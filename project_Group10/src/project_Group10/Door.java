package project_Group10;

public abstract class Door{
	
	public static boolean door = true;  	// Door closed = true


	// this will make sure that some data is not outputting when door is not being used 
	
	public static boolean getDo() {
		return door;
	}
	

	// this will make sure that some data is not outputting when door is not being used 
	public static boolean openDoor() {
		if(door == true) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean closeDoor() {
		if(door == true) {
			return false;
		}else {
			return true;
		}
	}


}
	

