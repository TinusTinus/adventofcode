package nl.mvdr.adventofcode.adventofcode2019.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SunnyPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SunnyPart2Test extends SolverTest<SunnyPart2> {

    /** Constructor. */
    public SunnyPart2Test() {
        super(SunnyPart2.class);
    }
    
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9006327", "input-day05-2019.txt"));
    }
}
