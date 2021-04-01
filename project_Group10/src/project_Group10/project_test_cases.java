package project_Group10;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class project_test_cases {


	@Test
	public void test() {
		// TEST CASES FOR EACH CLASS
		Thread floor = new Floor_main();
		Thread sch = new Scheduler();
		Thread elv = new Elevator(2);
		//Lamp lamp = new Lamp();
		Door door = new Door();
		Button button = new Button();
		Sensor sensor = new Sensor();
		Motor motor = new Motor();
		
		
		// FLOOR CLASS TESTS
		
//		String[] input2 = {"13", "6", "Up", "1"};
//		((Floor_main) floor).input[1] = "6";
//		((Floor_main) floor).input[2] = "Up";
//		((Floor_main) floor).input[3] = "1";
//		assertTrue(((Floor_main) floor).input[1].equals("6"));
//		assertTrue(((Floor_main) floor).input[2].equals("Up"));
//		assertFalse(((Floor_main) floor).input[3].equals("3"));
		
		
		// SCHEDULER CLASS TESTS - (currently not working still developing it)
		/*
		 * ((Scheduler) sch).setfInput(); assertTrue(((Scheduler)
		 * sch).getFloor().equals("6")); assertTrue(((Scheduler)
		 * sch).getTime().equals("18:14:587.4")); assertTrue(((Scheduler)
		 * sch).getDirection().equals("Up")); assertTrue(((Scheduler)
		 * sch).getCar().equals("3"));
		 */
		
		// ELEVATOR CLASS TESTS
		/* since input file is not working the elevator class cannot be tested */
		
		// DOOR CLASS TESTS
		assertFalse(door.door == false);
		assertTrue(door.openDoor() == true);
		assertTrue(door.openDoor() == false);
		
		// BUTTON CLASS TESTS
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.mmm");
		String dateString=sdf.format(date);
		assertTrue(button.getTime().equals(dateString));
		
		button.destFloor = 3;
		assertTrue(button.getdestFloor() == 3);
		
		// SENSOR CLASS TESTS
		assertTrue(sensor.checkStatus().equals(false));
		assertTrue(sensor.sendSignal() == true);

		// MOTOR CLASS TESTS
		motor.turnOn();
		assertTrue(motor.checkStatus() == true);
		motor.turnOff();
		assertTrue(motor.checkStatus() == false);
		// STATE MACHINE TESTS
		
		
	}

}
