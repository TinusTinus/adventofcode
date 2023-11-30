package nl.mvdr.adventofcode.adventofcode2017.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RecursiveCircusPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RecursiveCircusPart2Test extends SolverTest<RecursiveCircusPart2> {

    /** Constructor. */
    public RecursiveCircusPart2Test() {
        super(RecursiveCircusPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("60", "example-day07-2017.txt"),
                Arguments.of("362", "input-day07-2017.txt"));
    }
}
