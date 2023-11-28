package nl.mvdr.adventofcode.adventofcode2017.day14;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DiskDefragmentationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart1Test extends SolverTest<DiskDefragmentationPart1> {

    /** Constructor. */
    public DiskDefragmentationPart1Test() {
        super(DiskDefragmentationPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("8108", "example-day14-2017.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("8226", "input-day14-2017.txt");
    }
}
