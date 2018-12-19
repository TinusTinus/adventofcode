package nl.mvdr.adventofcode.adventofcode2018.day01;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test cases for {@link ChronalCalibration}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationTest {
	
	/** Object under test. */
	private ChronalCalibration chronalCalibration;
	
	/** Setup method. */
	@BeforeEach
	public void setUp() {
		this.chronalCalibration = new ChronalCalibration();
	}
	
	/** Test case based on the first example from the puzzle text. */
	@Test
	public void testExample0() {
		String inputFile = "example0-day01-2018.txt";
		
		String result = this.chronalCalibration.solve(inputFile);
		
		Assertions.assertEquals("3", result);
	}
	
	/** Test case based on the second example from the puzzle text. */
	@Test
	public void testExample1() {
		String inputFile = "example1-day01-2018.txt";
		
		String result = this.chronalCalibration.solve(inputFile);
		
		Assertions.assertEquals("3", result);
	}
	
	/** Test case based on the third example from the puzzle text. */
	@Test
	public void testExample2() {
		String inputFile = "example2-day01-2018.txt";
		
		String result = this.chronalCalibration.solve(inputFile);
		
		Assertions.assertEquals("0", result);
	}
	
	/** Test case based on the second example from the puzzle text. */
	@Test
	public void testExample3() {
		String inputFile = "example3-day01-2018.txt";
		
		String result = this.chronalCalibration.solve(inputFile);
		
		Assertions.assertEquals("-6", result);
	}
}
