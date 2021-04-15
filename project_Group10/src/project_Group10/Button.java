package project_Group10;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;
/**
 * This class is used to control the Buttons on the system. It has the floor buttons indicating up or down and elevator buttons indicating which floor to 
 * travel to.
 * @author Akkash Anton Amalarajah
 *
 */
public class Button {
	private String direction= null;
	private static int floorNum;// floor of which the person is at in the moment 
	private static int max_floors= 5;
	public static int destFloor;
	
	/**
	 * Button for the floor to use to indicate the direction
	 * @return
	 */
	public synchronized static String direction() {
		String direction;
		while (true){
		
		Scanner upDown = new Scanner(System.in);
		System.out.println("Enter: 'Up' or 'Down'.");
		direction = upDown.nextLine();
		
		if (direction.equals("Up")) {
		System.out.println("Going: "+ direction);
		break;
		}
		else if (direction.equals("Down")) {
			System.out.println("Going: "+ direction);
			break;
		}
		else {
			System.out.println("Enter a valid direction: 'Up' or 'Down'");
		}
		}
		return direction;
	}
	/*
	 * Get the time the button was pressed. USed by floor
	 */
	public static String getTime() {
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.mmm");
		String dateString=sdf.format(date);
		System.out.println(dateString);
		return dateString;
	}
	/* 
	 * Get the floor which the button was pressed. Used by floor
	 */
	public synchronized static int currentFloor(){
		int floornum;
		while (true) {
		@SuppressWarnings("resource")
		Scanner floor = new Scanner(System.in);
		System.out.println("Which floor are you on?");
		floornum = floor.nextInt();
		if (floornum<max_floors) {
			System.out.println("Curent floor: "+floornum);
			break;
		}
		else {
			System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"There are only 5 floors, which floor are you on?"+"]");
		}
	}
		return floornum;
	}
	/*
	 * Determine which floor you want to go to. Used by elevator. 
	 */
	public synchronized static int destFloor() {
		int floor;
		while(true) {
			@SuppressWarnings("resource")
			Scanner enterFloor = new Scanner(System.in);
			System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"which floor would you like to go to?"+"]");
			floor=enterFloor.nextInt();
			if (floor<max_floors) {
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"Going to floor: "+ floor+"]");
				break;
			}
			else {
				System.out.println("["+ LocalTime.now()+"]"+ " "+"["+  Thread.currentThread().getName()+"]"+" "+"["+"There are only 5 floors"+"]");
			}
			
		}
		destFloor = floor;// the floor of which the person would like to be dropped off at
		return floor;
		
	}
	/*
	 * Return the destination Floor 
	 */
	public static int getdestFloor() {
		return destFloor;
	}
	/*
	 * Return the floor number
	 */
	public static int getfloorNum() {
		return floorNum;
	}
}

