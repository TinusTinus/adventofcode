package nl.mvdr.adventofcode.adventofcode2018.day04;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Test class for {@link ReposeRecord}.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordTest extends SolverTest<ReposeRecord> {
    /** Constructor. */
    public ReposeRecordTest() {
        super(ReposeRecord.class);
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
}
