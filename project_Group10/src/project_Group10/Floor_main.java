package project_Group10;
/**
 * This is the Floor subsystem class. It runs the floor subsystem which is responsible for reading the input file and sending the data to the 
 * scheduler for the elevator to use. This class will also be responsible for the floor lamps and buttons, but is not included in this version. This version, v1,
 * shows the communication from the Floor class to the elevator class via the scheduler. 
 * 
 * @author Akkash Anton Amalarajah 101102787
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

public class Floor_main extends Thread {
	
	public static int car=1;
	//public static boolean Inp = false;
	public Scheduler sch = new Scheduler();
	public static Object[] input = new Object[4];

	
	public synchronized static int currentFloor(){
		int floornum;
		while (true) {
		@SuppressWarnings("resource")
		Scanner floor = new Scanner(System.in);
		System.out.println("Which floor are you on?");
		floornum = floor.nextInt();
		if (floornum<5) {
			System.out.println("Curent floor: "+floornum);
			break;
		}
		else {
			System.out.println("There are only 5 floors, which floor are you on?");
		}
	}
		return floornum;
	}
	@SuppressWarnings("resource")
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
	public synchronized Object[] inputFile() {
		
		input[0]= getTime().toString();
		input[1]= String.valueOf(currentFloor());
		input[2]= direction();
		input[3]= String.valueOf(car);
		
		
		Scheduler.floorno = input[0].toString();
		Scheduler.Direc = input[1].toString();
		Scheduler.time = input[2].toString();
		Scheduler.car = input[3].toString();
		
		System.out.println(Arrays.toString(input));
		notifyAll();
		//Inp = true;
		return input;
		
	}
	
	public synchronized Object[] getInput() {
		return input;
	}
	
	/**
	 * Main run method to run the floor class subsystem.
	 */
	public synchronized void run() {
		inputFile();
	}
		
}
