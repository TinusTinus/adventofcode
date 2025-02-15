package nl.mvdr.adventofcode.adventofcode2017.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link DuelingGeneratorsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DuelingGeneratorsPart2Test extends SolverTest<DuelingGeneratorsPart2> {

    /** Constructor. */
    public DuelingGeneratorsPart2Test() {
        super(DuelingGeneratorsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("309", "example-day15-2017.txt"));
    }
}
