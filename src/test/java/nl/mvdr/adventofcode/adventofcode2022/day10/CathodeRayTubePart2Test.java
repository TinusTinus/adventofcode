package nl.mvdr.adventofcode.adventofcode2022.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

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
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("""
                        ##..##..##..##..##..##..##..##..##..##..
                        ###...###...###...###...###...###...###.
                        ####....####....####....####....####....
                        #####.....#####.....#####.....#####.....
                        ######......######......######......####
                        #######.......#######.......#######.....
                        """, "example-day10-2022.txt"));
    }
}
