package project_Group10;

public class Lamp{
	
	private static String lightUp = null;
	private static String lightDown = null ;
	private static String lightFlo = null ;
	
		
	
	
	// this will get the data for the direction of elevator which is up and make sure it is not null
		public synchronized boolean setDirec(String lu) {
			lightUp = lu;
			lightDown= lu;
			return true;
		}
		
		
		
	// this will get the data for the direction of elevator which is down and make sure it is not null
	public synchronized boolean setNum(String ld) {
		
		lightFlo = ld;
		return true;
	}
	
	
	// this will get the data for the direction of elevator which is down and make sure it is not null
		public synchronized boolean setOn(String direc,String flrNum) {
			setDirec(direc);
			System.out.println("Elevator going " + direc);
			setNum(flrNum);
			System.out.println("Floor Number: " + flrNum);
			return true;
		}


}