package nl.mvdr.adventofcode.adventofcode2022.day10;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CathodeRayTubePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CathodeRayTubePart2Test extends SolverTest<CathodeRayTubePart2> {

    /** Constructor. */
    public CathodeRayTubePart2Test() {
        super(CathodeRayTubePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        String expected = """
                ##..##..##..##..##..##..##..##..##..##..
                ###...###...###...###...###...###...###.
                ####....####....####....####....####....
                #####.....#####.....#####.....#####.....
                ######......######......######......####
                #######.......#######.......#######.....
                """;
        assertSolution(expected, "example-day10-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        String expected = "?";
        assertSolution(expected, "input-day10-2022.txt");
    }
}
