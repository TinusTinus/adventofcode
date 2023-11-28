package nl.mvdr.adventofcode.adventofcode2017.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DiskDefragmentationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart2Test extends SolverTest<DiskDefragmentationPart2> {

    /** Constructor. */
    public DiskDefragmentationPart2Test() {
        super(DiskDefragmentationPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("1242", "example-day14-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("1128", "input-day14-2017.txt");
    }
}
