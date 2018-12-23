package nl.mvdr.adventofcode.adventofcode2018.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Test class for {@link ReposeRecordPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart2Test extends SolverTest<ReposeRecordPart2> {
    /** Constructor. */
    public ReposeRecordPart2Test() {
        super(ReposeRecordPart2.class);
    }
    
    /** Test case based on the puzzle's example. */
    @Test
    public void testExample() {
        assertSolution("4455", "example-day04-2018.txt");
    }
    
    /** Test case based on the puzzle's example. */
    @Test
    public void testExampleJumbled() {
        assertSolution("4455", "example-day04-2018-jumbled.txt");
    }
}
