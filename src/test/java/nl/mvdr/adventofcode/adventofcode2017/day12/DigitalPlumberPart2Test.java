package nl.mvdr.adventofcode.adventofcode2017.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DigitalPlumberPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DigitalPlumberPart2Test extends SolverTest<DigitalPlumberPart2> {

    /** Constructor. */
    public DigitalPlumberPart2Test() {
        super(DigitalPlumberPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day12-2017.txt"));
    }
}
