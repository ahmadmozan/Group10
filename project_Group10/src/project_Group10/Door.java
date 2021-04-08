package project_Group10;

import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

/**
 *  In this class door was Imlemented so  it can be used in elevator to show an open close doors and its times
 * @author Akkash Anton, ousama
 *
 */


public class Door{
	private static long startTimeElev, currentTimeElev, endTimeElev,total; 
	public static boolean door = true;  	// Door closed = true
	public static String DTime;// variable to store the door time 
	


	/**
	 *  this will get the door for elevator
	 * @return
	 */
	
	public static boolean getDo() {
		return door;
	}
	

	/**
	 *this will open the door of the elevator 
	 * @return true
	 */
	public static boolean openDoor() {
		
		if(door == true) {
			door = false;
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Opening door"+"]");
			if(Double.parseDouble(DTime)>9.52) {
				System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Error: Door did not open"+"]");
				System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Attempting to open door again"+"]");
				try {
					TimeUnit.SECONDS.sleep((long) 9.52);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				DTime="9.52";
			}
//			startTimeElev= System.currentTimeMillis();
//			endTimeElev= (long) (startTimeElev + ((9.52/2)));
			
			try {
				
				TimeUnit.SECONDS.sleep((long) (Double.parseDouble(DTime)/2));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			currentTimeElev=System.currentTimeMillis();
//			total=currentTimeElev-startTimeElev;
			}
			return true;
		}
	
	
	/**
	 * closes door
	 * @return true 
	 */
	public static boolean closeDoor() {
		if(door == true) {
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Door already closed"+"]");
			return true;
		}else {
			
			System.out.println("["+ new SimpleDateFormat("hh.mm aa").format(new Date()).toString()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Closing door"+"]");
			try {
				TimeUnit.SECONDS.sleep((long) 9.52);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			startTimeElev= System.currentTimeMillis();
//			
//			endTimeElev= (long) (startTimeElev + ((9.52/2)));
			try {
				TimeUnit.SECONDS.sleep((long) (Double.parseDouble(DTime)/2));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			door = true;
			return true;
		}
	}


}
	

