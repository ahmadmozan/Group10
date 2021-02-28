/**
 * 
 */
package project_Group10;

/**
 * @author bs
 *
 */
public class Motor {
	
	
private Boolean status ;
	

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
		
		System.out.println("we are currently moving up to floor " + floor);
		System.out.println("we just arrived at floor " + floor);
	}
	
	// Motor moves down to input floor and returns true if successful
	public void moveDown(int floor) {
			
		System.out.println("we are currently moving down to floor " + floor);
		System.out.println("we just arrived at floor " + floor);
	}
		
	

}
