package nl.mvdr.adventofcode.adventofcode2017.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RecursiveCircusPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RecursiveCircusPart1Test extends SolverTest<RecursiveCircusPart1> {

    /** Constructor. */
    public RecursiveCircusPart1Test() {
        super(RecursiveCircusPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("tknk", "example-day07-2017.txt"));
    }
}
