package nl.mvdr.adventofcode.adventofcode2023.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CubeConundrumPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CubeConundrumPart1Test extends SolverTest<CubeConundrumPart1> {

    /** Constructor. */
    public CubeConundrumPart1Test() {
        super(CubeConundrumPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8", "example-day02-2023.txt"));
    }
}
