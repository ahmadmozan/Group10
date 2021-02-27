package project_Group10;

public class Lamp extends Thread{
	
	public static String lightUp = null;
	public static String lightDown = null ;
	public static String lightFlo = null ;
	
	Lamp(String lu,String ld, String lf){
		lightUp=lu;// used to indicate light of the elevator is going up
		lightDown=ld;//used to indicate light of the elevator is going down
		lightFlo=lf; //used to for the floor number
}
	// this will get the data for the direction of elevator which is up and make sure it is not null
	public synchronized String getlu(String lu) {
		return lu;
	}
	public synchronized void lu(String lu) {
		
		while(lu == null) {
			try {
				System.out.println("nobody is using the elevator");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		lu=getlu(toString());
		System.out.println(getlu(toString()));
}
	
	// this will get the data for the direction of elevator which is down and make sure it is not null
	public synchronized String getld(String ld) {
		return ld;
	}
	public synchronized void ld(String ld) {
		
		while(ld == null) {
			try {
				System.out.println("nobody is using the elevator");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		ld=getld(toString());
		System.out.println(getld(toString()));
}
	
	
	// this will get the data for the floor number and make sure it is not null
	
	public synchronized String getlf(String lf) {
		return lf;
	}
	public synchronized void lf(String lf) {
		
		while(lf == null) {
			try {
				System.out.println("nobody is using the elevator");
				wait();
			}catch (InterruptedException e) {
				System.out.println(e);
			}
		}
		lf=getld(toString());
		System.out.println(getlf(toString()));
}
	
public synchronized void run(){
		
		lu(lightUp);
		ld(lightDown);
		lf(lightFlo);
		
	}


}
