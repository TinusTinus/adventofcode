package nl.mvdr.adventofcode.adventofcode2022.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegolithReservoirPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RegolithReservoirPart1Test extends SolverTest<RegolithReservoirPart1> {

    /** Constructor. */
    public RegolithReservoirPart1Test() {
        super(RegolithReservoirPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("24", "example-day14-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("892", "input-day14-2022.txt");
    }
}
