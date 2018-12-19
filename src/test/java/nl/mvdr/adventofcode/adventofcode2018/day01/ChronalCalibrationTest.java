package nl.mvdr.adventofcode.adventofcode2018.day01;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ChronalCalibration}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationTest extends SolverTest<ChronalCalibration> {
	
	/** Constructor. */
	public ChronalCalibrationTest() {
		super(ChronalCalibration.class);
	}
	
	/** Test case based on the first example from the puzzle text. */
	@Test
	public void testExample0() {
		assertSolution("3", "example0-day01-2018.txt");
	}
	
	/** Test case based on the second example from the puzzle text. */
	@Test
	public void testExample1() {
		assertSolution("3", "example1-day01-2018.txt");
	}
	
	/** Test case based on the third example from the puzzle text. */
	@Test
	public void testExample2() {
		assertSolution("0", "example2-day01-2018.txt");
	}
	
	/** Test case based on the second example from the puzzle text. */
	@Test
	public void testExample3() {
		assertSolution("-6", "example3-day01-2018.txt");
	}
}
