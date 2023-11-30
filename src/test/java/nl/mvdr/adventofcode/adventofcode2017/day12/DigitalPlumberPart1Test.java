package nl.mvdr.adventofcode.adventofcode2017.day12;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DigitalPlumberPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DigitalPlumberPart1Test extends SolverTest<DigitalPlumberPart1> {

    /** Constructor. */
    public DigitalPlumberPart1Test() {
        super(DigitalPlumberPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6", "example-day12-2017.txt"),
                Arguments.of("141", "input-day12-2017.txt"));
    }
}
