package nl.mvdr.adventofcode.adventofcode2023.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CamelCardsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CamelCardsPart1Test extends SolverTest<CamelCardsPart1> {

    /** Constructor. */
    public CamelCardsPart1Test() {
        super(CamelCardsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6440", "example-day07-2023.txt"));
    }
}
