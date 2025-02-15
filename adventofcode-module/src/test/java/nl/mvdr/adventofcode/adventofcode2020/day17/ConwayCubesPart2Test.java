package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ConwayCubesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ConwayCubesPart2Test extends SolverTest<ConwayCubesPart2> {

    /** Constructor. */
    public ConwayCubesPart2Test() {
        super(ConwayCubesPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0Cycles() {
        assertSolution(new ConwayCubesPart2(0), "5", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Cycle() {
        assertSolution(new ConwayCubesPart2(1), "29", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Cycles() {
        assertSolution(new ConwayCubesPart2(2), "60", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6Cycles() {
        assertSolution(new ConwayCubesPart2(6), "848", "example-day17-2020.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("848", "example-day17-2020.txt"));
    }
}
