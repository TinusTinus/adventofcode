package nl.mvdr.adventofcode.adventofcode2019.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SunnyPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SunnyPart1Test extends SolverTest<SunnyPart1> {

    /** Constructor. */
    public SunnyPart1Test() {
        super(SunnyPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("15508323", "input-day05-2019.txt"));
    }
}
