package project_Group10;

public class CartMonitor {
	/**
	 * In this class all the elevator are set to false which indicates that they are free and when they are true they are  busy
	 */
	public static boolean elev1 = false;
	public static boolean elev2 = false;
	public static boolean elev3 = false;
	public static boolean elev4 = false;
	
	
	public static void setTrue1() {
		elev1 = true;
	}
	public static void setTrue2() {
		elev2 = true;
	}
	public static void setTrue3() {
		elev3 = true;
	}
	public static void setTrue4() {
		elev4 = true;
	}
	
	public static void setFalse1() {
		elev1 = false;
	}
	public static void setFalse2() {
		elev2 = false;
	}
	public static void setFalse3() {
		elev3 = false;
	}
	public static void setFalse4() {
		elev4 = false;
	}
	
	public static boolean getStatus1() {
		return elev1;
	}
	public static boolean getStatus2() {
		return elev2;
	}
	public static boolean getStatus3() {
		return elev3;
	}
	public static boolean getStatus4() {
		return elev4;
	}
}
