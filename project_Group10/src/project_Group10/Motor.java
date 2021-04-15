/**
 * 
 */
package project_Group10;

import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.text.DateFormat;
import java.util.Date;

/**
 * This class is the motor that moves the elevator. If the motor is on (true) then the elevator will move. It will also run 
 * for a certain amount of time depending on the floor instruction. It will also check for errors in the motor time. 
 * @author bs
 *
 */
public class Motor {

	private Boolean status;
	public String MTime;
//private static Sensor reached, check;
//private static long startTimeElev, currentTimeElev, endTimeElev, total;
	private Boolean shutdown = false;

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

		System.out.println("[" + LocalTime.now() + "]" + " " + "[" + Thread.currentThread().getName() + "]" + " " + "["
				+ "We are currently moving up to floor " + floor + "]");

		if (Integer.parseInt(MTime) > 14.4) {

			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(MTime) * floor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			shutdown = true;

			System.out.println("[" + LocalTime.now() + "]" + " " + "[" + Thread.currentThread().getName() + "]" + " "
					+ "[" + "Error: Elevator did not reach the floor" + "]");

		}

		else {

			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(MTime) * floor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("[" + LocalTime.now() + "]" + " " + "[" + Thread.currentThread().getName() + "]" + " "
					+ "[" + "We just arrived at floor " + floor + "]");
			shutdown = false;
		}
	}

	// Motor moves down to input floor and returns true if successful
	public void moveDown(int floor) {

		System.out.println("[" + LocalTime.now() + "]" + " " + "[" + Thread.currentThread().getName() + "]" + " " + "["
				+ "We are currently moving down to floor " + floor + "]");

		if (Integer.parseInt(MTime) > 14.4) {
			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(MTime) * floor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try {
				TimeUnit.SECONDS.sleep(Integer.parseInt(MTime) * floor);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("[" + LocalTime.now() + "]" + " " + "[" + Thread.currentThread().getName() + "]" + " "
					+ "[" + "We just arrived at floor " + floor + "]");
			shutdown = false;
		}
	}
	/*
	 * Shuts down the elevator 
	 */
	public Boolean shutdown() {

		return shutdown;

	}

}
