package project_Group10;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Button {
	private String direction= null;
	private int floorNum;
	private static int max_floors= 5;
	public static int destFloor;
	
	
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
	public static Object getTime() {
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.mmm");
		String dateString=sdf.format(date);
		System.out.println(dateString);
		return dateString;
	}
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
			System.out.println("There are only 5 floors, which floor are you on?");
		}
	}
		return floornum;
	}
	public synchronized static int destFloor() {
		int floor;
		while(true) {
			@SuppressWarnings("resource")
			Scanner enterFloor = new Scanner(System.in);
			System.out.println("which floor would you like to go to?");
			floor=enterFloor.nextInt();
			if (floor<max_floors) {
				System.out.println("Going to floor: "+ floor);
				break;
			}
			else {
				System.out.println("There are only 5 floors");
			}
			
		}
		destFloor = floor;
		return floor;
		
	}

	public static int getdestFloor() {
		return destFloor;
	}
}

