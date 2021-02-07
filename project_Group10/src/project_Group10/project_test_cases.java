package project_Group10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class project_test_cases {


	@Test
	void test() {
		
		Thread floor = new Floor_main();
		Thread sch = new Scheduler();
		Thread elv = new Elevator();
		
		((Floor_main) floor).getData();
		String[] DATA1 = (((Floor_main) floor).data);
		assertTrue(DATA1[0].equals("18:14:587.4"));
		assertTrue(DATA1[1].equals("6"));
		assertTrue(DATA1[2].equals("Up"));
		assertTrue(DATA1[3].equals("3"));
		
		
		
		
	}

}
