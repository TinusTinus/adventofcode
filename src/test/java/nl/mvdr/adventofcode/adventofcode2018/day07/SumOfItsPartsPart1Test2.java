package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link SumOfItsPartsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsPart1Test2 extends SolverTest<SumOfItsPartsPart2> {
    /** Constructor. */
    public SumOfItsPartsPart1Test2() {
        super(SumOfItsPartsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("15", "example-day07-2018.txt"));
    }

    /** Test case based on an example from the puzzle. */
    @Test
    public void testExample() {
        testSolution("15", "example-day07-2018.txt");
    }
}
