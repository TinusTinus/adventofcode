package nl.mvdr.adventofcode.adventofcode2018.day02;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link InventoryManagementSystemPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class InventoryManagementSystemPart2Test extends SolverTest<InventoryManagementSystemPart2> {
	/** Constructor. */
	public InventoryManagementSystemPart2Test() {
		super(InventoryManagementSystemPart2.class);
	}
	
	/** Test case based on the example from the puzzle. */
	@Test
	public void testExample() {
		testSolution("fgij", "example-day02-2018-part2.txt");
	}
	
	/** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("bvnfawcnyoeyudzrpgslimtkj", "input-day02-2018.txt");
    }
}
