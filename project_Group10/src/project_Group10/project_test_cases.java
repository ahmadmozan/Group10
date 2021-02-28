package project_Group10;
/**
 * 
 * @author ahmad
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class project_test_cases {


	@Test
	void test() {
		
		Thread floor = new Floor_main();
		Thread sch = new Scheduler();
		Thread elv = new Elevator();
		
		((Floor_main) floor).inputFile();
		String[] DATA1 = (((Floor_main) floor).input.toString());
		assertTrue(DATA1[0].equals("18:14:587.4"));
		assertTrue(DATA1[1].equals("6"));
		assertTrue(DATA1[2].equals("Up"));
		assertTrue(DATA1[3].equals("3"));
		
		((Scheduler) sch).setfInput();
		assertTrue(((Scheduler) sch).getFloor().equals("6"));
		assertTrue(((Scheduler) sch).getTime().equals("18:14:587.4"));
		assertTrue(((Scheduler) sch).getDirection().equals("Up"));
		assertTrue(((Scheduler) sch).getCar().equals("3"));
	}

}
