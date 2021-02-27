package project_Group10;

public class Door extends Thread {
	
	public static String doorOpen = null;
	public static String doorClo= null ;
	// this will be used as a constructor for other class the scheduler and which is the communication between floor and elevator 
	Door(String newDO, String newDC){
		doorOpen= newDO;// value for door open
		doorClo=newDC;// value for door closed
}
	// this will make sure that some data is not outputting when door is not being used 
	
	public synchronized String getDo(String newDo) {
		return newDo;
	}
	public synchronized void Do(String newDo) {
		
		while(newDo == null) {
			try {
				System.out.println("nobody is using the elevator");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		newDo=getDo(toString());
		System.out.println(getDo(toString()));
}
	// this will make sure that some data is not outputting when door is not being used 
	public synchronized String getDc(String newDc) {
		return newDc;
	}
	public synchronized void Dc(String newDc) {
		
		while(newDc == null) {
			try {
				System.out.println("nobody is using the elevator");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		newDc=getDc(toString());
		System.out.println(getDc(toString()));
}

public synchronized void run(){
		
		Do(doorOpen);
		Dc(doorClo);
		
	}

}
	

