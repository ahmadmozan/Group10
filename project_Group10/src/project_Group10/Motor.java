/**
 * 
 */
package project_Group10;

import java.util.concurrent.TimeUnit;

/**
 * @author bs
 *
 */
public class Motor {
	
	
private Boolean status ;
public static String MTime;
private static Elevator ElevaM;

	// Turns on Motor
	public void turnOn() {
		
		status = true;
	}

	// Turns off Motor
	public void turnOff() {
		
		status = false;	
	}
	
	// Checks Motor status	
	public Boolean checkStatus() {
		
		return status;	
	}
	
	
	// Motor moves up to input floor and returns true if successful
	public void moveUp(int floor) {
		MTime= ElevaM.info[5];
		
		
		System.out.println("we are currently moving up to floor " + floor);
		try {
			TimeUnit.SECONDS.sleep(Integer.parseInt(MTime)*floor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("we just arrived at floor " + floor);
	}
	
	// Motor moves down to input floor and returns true if successful
	public void moveDown(int floor) {
			
		System.out.println("we are currently moving down to floor " + floor);
		try {
			TimeUnit.SECONDS.sleep(Integer.parseInt(MTime)*floor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("we just arrived at floor " + floor);
	}
		
	

}
