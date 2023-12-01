package nl.mvdr.adventofcode.adventofcode2020.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BinaryBoardingPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryBoardingPart2Test extends SolverTest<BinaryBoardingPart2> {

    /** Constructor. */
    public BinaryBoardingPart2Test() {
        super(BinaryBoardingPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("597", "input-day05-2020.txt"));
    }
}
