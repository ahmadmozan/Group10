/**
 * 
 */
package iteration1;

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
	
	// Checks sensor status	
	public Boolean checkStatus() {
		
		return status;	
	}
	
	
	// Motor moves up to input floor and returns true if successful
	public Boolean moveUp(String floor) {
		
		return true;	
	}
	
	// Motor moves down to input floor and returns true if successful
		public Boolean movedown(String floor) {
			
			return true;	
		}
		
	

}
