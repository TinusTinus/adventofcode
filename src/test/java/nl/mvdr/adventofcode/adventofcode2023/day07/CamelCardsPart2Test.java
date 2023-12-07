package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CamelCardsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CamelCardsPart2Test extends SolverTest<CamelCardsPart2> {

    /** Constructor. */
    public CamelCardsPart2Test() {
        super(CamelCardsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5905", "example-day07-2023.txt"),
                Arguments.of("?", "input-day07-2023.txt"));
    }
}
