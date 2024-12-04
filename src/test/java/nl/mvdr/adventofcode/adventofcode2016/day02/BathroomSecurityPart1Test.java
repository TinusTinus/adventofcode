package nl.mvdr.adventofcode.adventofcode2016.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BathroomSecurityPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BathroomSecurityPart1Test extends SolverTest<BathroomSecurityPart1> {

    /** Constructor. */
    public BathroomSecurityPart1Test() {
        super(BathroomSecurityPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1985", "example-day02-2016.txt"));
    }
}
