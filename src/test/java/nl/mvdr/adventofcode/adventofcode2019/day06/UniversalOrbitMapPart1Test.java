package nl.mvdr.adventofcode.adventofcode2019.day06;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link UniversalOrbitMapPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class UniversalOrbitMapPart1Test extends SolverTest<UniversalOrbitMapPart1> {

    /** Constructor. */
    public UniversalOrbitMapPart1Test() {
        super(UniversalOrbitMapPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("42", "example-day06-2019-0.txt"));
    }
}
