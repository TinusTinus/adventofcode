package nl.mvdr.adventofcode.adventofcode2019.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DonutMazePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DonutMazePart1Test extends SolverTest<DonutMazePart1> {

    /** Constructor. */
    public DonutMazePart1Test() {
        super(DonutMazePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("23", "example-day20-2019-0.txt"),
                Arguments.of("58", "example-day20-2019-1.txt"),
                Arguments.of("620", "input-day20-2019.txt"));
    }
}
