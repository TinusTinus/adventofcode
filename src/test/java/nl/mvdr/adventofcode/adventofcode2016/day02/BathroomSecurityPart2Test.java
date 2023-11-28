package nl.mvdr.adventofcode.adventofcode2016.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BathroomSecurityPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BathroomSecurityPart2Test extends SolverTest<BathroomSecurityPart2> {

    /** Constructor. */
    public BathroomSecurityPart2Test() {
        super(BathroomSecurityPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5DB3", "example-day02-2016.txt"),
                Arguments.of("46C92", "input-day02-2016.txt"));
    }
}
