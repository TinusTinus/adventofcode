package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NeverTellMeTheOddsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart2Test extends SolverTest<NeverTellMeTheOddsPart2> {

    /** Constructor. */
    public NeverTellMeTheOddsPart2Test() {
        super(NeverTellMeTheOddsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("843888100572888", "input-day24-2023.txt"));
    }
}
