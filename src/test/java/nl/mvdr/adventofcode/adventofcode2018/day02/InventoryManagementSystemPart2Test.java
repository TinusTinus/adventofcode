package nl.mvdr.adventofcode.adventofcode2018.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

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
	
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("fgij", "example-day02-2018-part2.txt"));
    }
}
