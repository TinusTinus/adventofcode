package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Unit test cases for {@link StepCounterPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class StepCounterPart2Test {

    /**
     * Checks that the solver produces the expected solution for the given number of steps,
     * using the example from the puzzle text as the input.
     * 
     * @param expectedResult expected number of reachable garden plots
     * @param numberOfSteps the elf's remaining number of steps for the day
     */
    @ParameterizedTest
    @MethodSource
    public void testExample(int expectedResult, int numberOfSteps) {
        var solver = new StepCounterPart2(numberOfSteps);
        
        var result = solver.solve("example-day21-2023.txt");
        
        Assertions.assertEquals("" + expectedResult, result);
    }
    
    /**
     * @return arguments for {@link #testExample(int, int)}
     */
    @SuppressWarnings("boxing")
    private static Stream<Arguments> testExample() {
        return Stream.of(
                Arguments.of(1, 0),
                Arguments.of(2, 1),
                Arguments.of(4, 2),
                Arguments.of(6, 3),
                Arguments.of(16, 6),
                Arguments.of(50, 10),
                Arguments.of(1594, 50),
                Arguments.of(6536, 100),
                Arguments.of(167004, 500),
                Arguments.of(668697, 1000),
                Arguments.of(16733044, 5000));
    }
}
