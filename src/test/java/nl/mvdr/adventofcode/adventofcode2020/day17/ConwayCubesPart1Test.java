package nl.mvdr.adventofcode.adventofcode2020.day17;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ConwayCubesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ConwayCubesPart1Test extends SolverTest<ConwayCubesPart1> {

    /** Constructor. */
    public ConwayCubesPart1Test() {
        super(ConwayCubesPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0Cycles() {
        assertSolution(new ConwayCubesPart1(0), "5", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Cycle() {
        assertSolution(new ConwayCubesPart1(1), "11", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Cycles() {
        assertSolution(new ConwayCubesPart1(2), "21", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3Cycles() {
        assertSolution(new ConwayCubesPart1(3), "38", "example-day17-2020.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6Cycles() {
        assertSolution(new ConwayCubesPart1(6), "112", "example-day17-2020.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("112", "example-day17-2020.txt"));
    }
}
