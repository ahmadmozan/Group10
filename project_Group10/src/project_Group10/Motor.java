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
private static Sensor reached, check;
private static long startTimeElev, currentTimeElev, endTimeElev, total;
private Boolean shutdown;

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
		
		System.out.println("We are currently moving up to floor " + floor);
		startTimeElev= System.currentTimeMillis();
		
		endTimeElev= (long) (startTimeElev + (7.18*floor));
		
		try {
			TimeUnit.SECONDS.sleep(Integer.parseInt(MTime)*floor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		currentTimeElev=System.currentTimeMillis();
		total=currentTimeElev-startTimeElev;
		if (endTimeElev>=total) {
			
			System.out.println("Error: Elevator did not reach the floor");
			shutdown=true;
		}
		
		System.out.println("We just arrived at floor " + floor);
		shutdown=false;
	}
	
	// Motor moves down to input floor and returns true if successful
	public void moveDown(int floor) {
			
		System.out.println("We are currently moving down to floor " + floor);
		startTimeElev= System.currentTimeMillis();
		endTimeElev= (long) (startTimeElev + (7.18*floor));
		try {
			TimeUnit.SECONDS.sleep(Integer.parseInt(MTime)*floor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		currentTimeElev=System.currentTimeMillis();
		total=currentTimeElev-startTimeElev;
		if (endTimeElev>=total) {//and if sensor is activaed or not
			
			System.out.println("Error: Elevator did not reach the floor");
			shutdown=true;
		}
		else {
		System.out.println("We just arrived at floor " + floor);
		shutdown=false;
		}
	}
	
	public Boolean shutdown() {
		
		return shutdown;
		
	}
	
		
	

}
