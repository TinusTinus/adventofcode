package nl.mvdr.adventofcode.adventofcode2017.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HexEdPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HexEdPart2Test extends SolverTest<HexEdPart2> {

    /** Constructor. */
    public HexEdPart2Test() {
        super(HexEdPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1524", "input-day11-2017.txt"));
    }
}
