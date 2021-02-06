package project_Group10;

public class Scheduler extends Thread{
	
	
	public static String fInput[] = null;
	
	public synchronized void setfInput(String Inp[]) {
		
		while(fInput != null) {
			try {
				System.out.println("Input file is currently being processed");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		fInput = Inp;
		notifyAll();
		
	}
	
	public synchronized void getfInput() {
		
		while(fInput == null) {
			try {
				System.out.println("Waiting on an input file");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		notifyAll();
		System.out.println(fInput);
	}
	
	public synchronized void notifyElevator() {
		
	}
	
	public void run(){
		setfInput(Floor_main.data);
		String[] op = fInput;
	    for(String str : op)
	        System.out.println(str);
	}
	
	
	public static void main(String[] args) {
		
		Thread Floor = new Floor_main();
		Thread sch = new Scheduler();
		
		Floor.run();
		sch.run();
		
		
	}
	
	
	
}
