package project_Group10;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import project_Group10.ElevatorCart.elevatorstatemch;
import project_Group10.Scheduler.statemachine;

public class project_test_cases {


	@Test
	public void test() {
		
		/////////////////////////////////////
		//// TEST CASES FOR EACH CLASS//////
		////////////////////////////////////
		
		
		/////////////////////////////////////
		// creating instances of classes ////
		/////////////////////////////////////
		
		Floor_main floor = new Floor_main();
		//Scheduler sch = new Scheduler();
		Elevator elvMain = new Elevator();
		Lamp lamp = new Lamp();
		Door door = new Door();
		Button button = new Button();
		Sensor sensor = new Sensor();
		Motor motor = new Motor();
		CartMonitor cMon = new CartMonitor();
		OutputGui g1 =new OutputGui();
		ElevatorCart elv1 = new ElevatorCart(cMon,g1);
		ElevatorCart1 elv2 = new ElevatorCart1(cMon,g1);
		ElevatorCart2 elv3 = new ElevatorCart2(cMon,g1);
		ElevatorCart3 elv4 = new ElevatorCart3(cMon,g1);
		
		
		/////////////////////////////////////
		/////// FLOOR CLASS TESTS ///////////
		/////////////////////////////////////
		
//		String[] input2 = {"13", "6", "Up", "1"};
//		((Floor_main) floor).input[1] = "6";
//		((Floor_main) floor).input[2] = "Up";
//		((Floor_main) floor).input[3] = "1";
//		assertTrue(((Floor_main) floor).input[1].equals("6"));
//		assertTrue(((Floor_main) floor).input[2].equals("Up"));
//		assertFalse(((Floor_main) floor).input[3].equals("3"));
		
		
		/////////////////////////////////////
		/////// SCHEDULER CLASS TESTS ///////
		/////////////////////////////////////
		
		statemachine schst = statemachine.Fetch;
		assertTrue(schst.equals(statemachine.Fetch));
		schst = schst.next();
		assertTrue(schst.equals(statemachine.SendElevator));
		schst = schst.next();
		assertTrue(schst.equals(statemachine.Repeat));
		
		/////////////////////////////////////
		////// ELEVATOR CLASS TESTS /////////
		/////////////////////////////////////
		
		
		
		
		
		/////////////////////////////////////
		///////// DOOR CLASS TESTS //////////
		/////////////////////////////////////
		
		door.DTime = "5";
		assertTrue(door.getDo() == true);
		door.openDoor();
		assertTrue(door.getDo() == false);
		door.openDoor();
		assertTrue(door.getDo() == false);
		door.closeDoor();
		assertTrue(door.getDo() == true);
		
		
		/////////////////////////////////////
		/////// BUTTON CLASS TESTS //////////
		/////////////////////////////////////
		
		Date date= new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss.mmm");
		String dateString=sdf.format(date);
		assertTrue(button.getTime().equals(dateString));
		
		button.destFloor = 3;
		assertTrue(button.getdestFloor() == 3);
		
		
		/////////////////////////////////////
		//////// Lamp Class TESTS ///////////
		/////////////////////////////////////
		
		lamp.setDirec("Up");
		lamp.setNum("14");
		assertTrue(lamp.lightUp.equals("Up"));
		assertTrue(lamp.lightDown.equals("Up"));
		assertTrue(lamp.lightFlo.equals("14"));
		assertTrue(lamp.setNum("14") == true);
		lamp.setOn("Down", "13");
		assertTrue(lamp.lightUp.equals("Down"));
		assertTrue(lamp.lightDown.equals("Down"));
		assertTrue(lamp.lightFlo.equals("13"));
		
		
		/////////////////////////////////////
		///////// SENSOR CLASS TESTS ////////
		/////////////////////////////////////
		
		assertTrue(sensor.checkStatus().equals(false));
		assertTrue(sensor.sendSignal() == true);
		sensor.clearSignal();
		assertTrue(sensor.checkStatus().equals(false));

		
		/////////////////////////////////////
		//////// MOTOR CLASS TESTS //////////
		/////////////////////////////////////
		
		motor.MTime = "8";
		motor.turnOn();
		assertTrue(motor.checkStatus() == true);
		motor.turnOff();
		assertTrue(motor.checkStatus() == false);
		assertTrue(motor.shutdown() == false);
		motor.moveUp(1);
		assertFalse(motor.shutdown() == false);
		
		
		/////////////////////////////////////
		//// CartMonitor Class TESTS ////////
		/////////////////////////////////////
		
		assertTrue(cMon.getStatus1() == false);
		assertTrue(cMon.getStatus2() == false);
		assertTrue(cMon.getStatus3() == false);
		assertTrue(cMon.getStatus4() == false);
		cMon.setTrue1();
		cMon.setTrue2();
		cMon.setTrue3();
		cMon.setTrue4();
		assertTrue(cMon.getStatus1() == true);
		assertTrue(cMon.getStatus2() == true);
		assertTrue(cMon.getStatus3() == true);
		assertTrue(cMon.getStatus4() == true);
		cMon.setFalse1();
		cMon.setFalse2();
		cMon.setFalse3();
		cMon.setFalse4();
		assertTrue(cMon.getStatus1() == false);
		assertTrue(cMon.getStatus2() == false);
		assertTrue(cMon.getStatus3() == false);
		assertTrue(cMon.getStatus4() == false);
		
		
		/////////////////////////////////////
		//// ElevatorCarts Class TESTS ///////
		/////////////////////////////////////
		
		assertTrue(elv1.cMon == cMon);
		assertTrue(elv2.cMon == cMon);
		assertTrue(elv3.cMon == cMon);
		assertTrue(elv4.cMon == cMon);
		
		assertTrue(elv1.g2 == g1);
		assertTrue(elv2.g2 == g1);
		assertTrue(elv3.g2 == g1);
		assertTrue(elv4.g2 == g1);
		
		elevatorstatemch stmchn = elevatorstatemch.getInfo;
		assertTrue(stmchn.equals(elevatorstatemch.getInfo));
		stmchn = stmchn.next();
		assertTrue(stmchn.equals(elevatorstatemch.Move));
		stmchn = stmchn.next();
		assertTrue(stmchn.equals(elevatorstatemch.Move2));
		
	}

}
