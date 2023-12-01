package nl.mvdr.adventofcode.adventofcode2022.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RopeBridgePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RopeBridgePart1Test extends SolverTest<RopeBridgePart1> {

    /** Constructor. */
    public RopeBridgePart1Test() {
        super(RopeBridgePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("13", "example-day09-2022-0.txt"),
                Arguments.of("5683", "input-day09-2022.txt"));
    }
}
