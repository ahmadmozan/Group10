package project_Group10;

import java.util.Random;

public class Elevator extends Thread {

	public static String info[] = null;
	
public synchronized void getInfo() {

		while(info != null) {
            try {
                System.out.println("Info has already been collected!");
                wait();
            }catch (InterruptedException e) {
                System.out.println(e);
            }
        }
		info = Scheduler.fInput;
        notifyAll();
        System.out.println();
    }


public synchronized void outPut() {
	
	while(info == null) {
		try {
            System.out.println("Need Info first!");
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

public void run(){
	
    getInfo();
    outPut();

    }
}