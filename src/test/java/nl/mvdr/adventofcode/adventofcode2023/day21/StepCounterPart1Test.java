package nl.mvdr.adventofcode.adventofcode2023.day21;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

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
    
    @Test
    public void testExample0Steps() {
        assertSolution(new StepCounterPart1(0), "1", "example-day21-2023.txt");
    }
    
    @Test
    public void testExample1Step() {
        assertSolution(new StepCounterPart1(1), "2", "example-day21-2023.txt");
    }
    
    @Test
    public void testExample2Steps() {
        assertSolution(new StepCounterPart1(2), "4", "example-day21-2023.txt");
    }

    @Test
    public void testExample3Steps() {
        assertSolution(new StepCounterPart1(3), "6", "example-day21-2023.txt");
    }
    
    @Test
    public void testExample6Steps() {
        assertSolution(new StepCounterPart1(6), "16", "example-day21-2023.txt");
    }
    
    @Test
    public void testInput0Steps() {
        assertSolution(new StepCounterPart1(0), "1", "input-day21-2023.txt");
    }
}
