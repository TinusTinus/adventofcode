package nl.mvdr.adventofcode.adventofcode2018.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link InventoryManagementSystem}.
 *
 * @author Martijn van de Rijdt
 */
public class InventoryManagementSystemTest extends SolverTest<InventoryManagementSystem> {
	/** Constructor. */
	public InventoryManagementSystemTest() {
		super(InventoryManagementSystem.class);
	}
	
	/** Test case based on the example from the puzzle. */
	@Test
	public void testExample() {
		assertSolution("12", "example-day02.txt");
	}
}
