/**
 * 
 */
package project_Group10;

/**
 * @author bs
 *
 */
public class Sensor {
	
	public static Boolean status  = false;
	
	
	// Checks sensor status	
	public Boolean checkStatus() {
		
		return status;	
	}
	
	
	// Sends signal when floor detected
	public Boolean sendSignal() {
		status = true;
		return status;	
	}
	
	// clears signal when signal already used
	public void clearSignal() {
		status = false;
	}

}
