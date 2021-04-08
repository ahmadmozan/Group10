/**
 * 
 */
package project_Group10;

import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

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
		
		System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"We are currently moving up to floor " + floor+"]");
		
		if(Integer.parseInt(MTime)>7.18) {

			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Error: Elevator did not reach the floor"+"]");
			try {
				TimeUnit.SECONDS.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			shutdown=true;
		}
		
		else {
		
		try {
			TimeUnit.SECONDS.sleep(Integer.parseInt(MTime)*floor);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
		System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"We just arrived at floor " + floor+"]");
		shutdown=false;
		}
	}
	
	// Motor moves down to input floor and returns true if successful
	public void moveDown(int floor) {
			
		System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"We are currently moving down to floor " + floor+"]");
		if(Integer.parseInt(MTime)>7.18) {

			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Error: Elevator did not reach the floor"+"]");
			shutdown=true;
		}
		else {
			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(MTime)*floor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"We just arrived at floor " + floor+"]");
			shutdown=false;
			}
	}
	}
	
	public Boolean shutdown() {
		
		return shutdown;
		
	}
	
		
	

}
