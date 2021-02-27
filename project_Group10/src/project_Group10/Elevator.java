package Group10;
/**
 * This is the Elevator class subsystem. This class will run what the elevator will do with the given data from the floor. It will determine which floor to go to 
 * and will notify the scheduler when it has reached the appropriate floor. This class will also have buttons inside to indicate teh floor to go to
 * and will have lamps, however they are not implemented in this version. 
 * @author Ousama Al-chami
 */
import java.util.Random;

public class Elevator extends Thread {

	public static String info[] = null;
	public static String  Signal;;
	
/**
 * This method is retreiving the data from the floor class via the scheduler. 
 * It checks if the information was collected or not before attempting to collect it
 */
	
public synchronized void getInfo() {

		while(info == null) {
            try {
                System.out.println("Need Info first");
                wait();
            }catch (InterruptedException e) {
                System.out.println(e);
            }
        }
		info = Scheduler.fInput;
        notifyAll();
        System.out.println();
    }

/**
 * This method returns to the scheduler class the results of the task complete. It updates the scheduler.
 */
public synchronized void outPut() {
	
	while(info != null) {
		try {
            System.out.println("Info has already been collected!");
            wait();
        }catch (InterruptedException e) {
            System.out.println(e);
        }
	}
	notifyAll();
    System.out.println("we are currently on the "+ info[1]+"floor.\n");
    System.out.println("we are currently going"+ info[2]+"\n");
    System.out.println("the current time is "+ info[0]+"\n");
    System.out.println("we are currently in car number "+info[3]+"\n");

}
public synchronized void getSignal() {

    while( Signal == "false") {
        try {
            System.out.println("nobdoy is using the elavator yet");
            wait();
        }catch (InterruptedException e) {
            System.out.println(e);
        }

        if (Signal== "True") {
             System.out.println("the elevator is heading to ur service");
        }
    }
     notifyAll();
}
public void  elevButton() {
	Button newbutton= new Button();
	newbutton.destFloor();
}
 /*
 * This method runs the elevator class subsystem.
 */
public void run(){
	
    getInfo();
    outPut();

    }
}