package nl.mvdr.adventofcode.adventofcode2020.day23;

import java.util.stream.Stream;

import nl.mvdr.adventofcode.FunctionSolver;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CrabCupsPart1Kt}.
 *
 * @author Martijn van de Rijdt
 */
public class CrabCupsPart1Test extends SolverTest<FunctionSolver<String>> {

    private static FunctionSolver<String> createSolver(int turns) {
        return new FunctionSolver<>(lines -> CrabCupsPart1Kt.solvePart1(lines, turns));
    }

    /** Constructor. */
    public CrabCupsPart1Test() {
        super(createSolver(100));
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0Turns() {
        assertSolution(createSolver(0), "25467389", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1Turn() {
        assertSolution(createSolver(1), "54673289", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample2Turns() {
        assertSolution(createSolver(2), "32546789", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample3Turns() {
        assertSolution(createSolver(3), "34672589", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample4Turns() {
        assertSolution(createSolver(4), "32584679", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample5Turns() {
        assertSolution(createSolver(5), "36792584", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample6Turns() {
        assertSolution(createSolver(6), "93672584", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample7Turns() {
        assertSolution(createSolver(7), "92583674", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample8Turns() {
        assertSolution(createSolver(8), "58392674", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample9Turns() {
        assertSolution(createSolver(9), "83926574", "example-day23-2020.txt");
    }

    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample10Turns() {
        assertSolution(createSolver(10), "92658374", "example-day23-2020.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("67384529", "example-day23-2020.txt"),
                Arguments.of("32897654", "input-day23-2020.txt"));
    }
}
