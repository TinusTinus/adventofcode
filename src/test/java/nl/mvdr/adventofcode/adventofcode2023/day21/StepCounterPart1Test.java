package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link StepCounterPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class StepCounterPart1Test extends SolverTest<StepCounterPart1> {

    /** Constructor. */
    public StepCounterPart1Test() {
        super(StepCounterPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "input-day21-2023.txt"));
    }
    
    /**
     * Test case for 0 steps.
     * 
     * After 0 steps the only reachable plot is the starting plot.
     */
    @Test
    public void testInput0Steps() {
        assertSolution(new StepCounterPart1(0), "1", "input-day21-2023.txt");
    }

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
        StepCounterPart1 solver = new StepCounterPart1(numberOfSteps);
        
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
                Arguments.of(4, 2),
                Arguments.of(6, 3),
                Arguments.of(16, 6));
    }
}
