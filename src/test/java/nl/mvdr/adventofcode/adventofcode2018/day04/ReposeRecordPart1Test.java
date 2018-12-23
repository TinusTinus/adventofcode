package nl.mvdr.adventofcode.adventofcode2018.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Test class for {@link ReposeRecordPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart1Test extends SolverTest<ReposeRecordPart1> {
    /** Constructor. */
    public ReposeRecordPart1Test() {
        super(ReposeRecordPart1.class);
    }
    
    /** Test case based on the puzzle's example. */
    @Test
    public void testExample() {
        assertSolution("240", "example-day04-2018.txt");
    }
    
    /** Test case based on the puzzle's example. */
    @Test
    public void testExampleJumbled() {
        assertSolution("240", "example-day04-2018-jumbled.txt");
    }
    
    /** Test case based on the puzzle's accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("131469", "input-day04-2018.txt");
    }
}
