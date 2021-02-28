/**
 * 
 */
package iteration1;

/**
 * @author bs
 *
 */
public class Sensor {
	
	Boolean status ;
	

	// Turns on sensor
	public void turnOn() {
		
		status = true;
	}

	// Turns off sensor
	public void turnOff() {
		
		status = false;	
	}
	
	// Checks sensor status	
	public Boolean checkStatus() {
		
		return status;	
	}
	
	
	// Sends signal when floor detected
	public Boolean sendSignal() {
		
		return true;	
	}
	
	

}