package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.NoSuchElementException;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.Solver;
import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link DonutMazePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DonutMazePart2Test extends SolverTest<DonutMazePart2> {

    /** Constructor. */
    public DonutMazePart2Test() {
        super(DonutMazePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("26", "example-day20-2019-0.txt"),
                Arguments.of("396", "example-day20-2019-2.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        Solver solver = new DonutMazePart2();
        
        Assertions.assertThrows(NoSuchElementException.class, () -> solver.solve("example-day20-2019-1.txt"));
    }
}
