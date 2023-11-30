package nl.mvdr.adventofcode.adventofcode2017.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DuelingGeneratorsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DuelingGeneratorsPart1Test extends SolverTest<DuelingGeneratorsPart1> {

    /** Constructor. */
    public DuelingGeneratorsPart1Test() {
        super(DuelingGeneratorsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("588", "example-day15-2017.txt"),
                Arguments.of("597", "input-day15-2017.txt"));
    }
}
