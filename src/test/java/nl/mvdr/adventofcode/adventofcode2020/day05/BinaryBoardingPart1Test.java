package nl.mvdr.adventofcode.adventofcode2020.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BinaryBoardingPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BinaryBoardingPart1Test extends SolverTest<BinaryBoardingPart1> {

    /** Constructor. */
    public BinaryBoardingPart1Test() {
        super(BinaryBoardingPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("801", "input-day05-2020.txt"));
    }
}
