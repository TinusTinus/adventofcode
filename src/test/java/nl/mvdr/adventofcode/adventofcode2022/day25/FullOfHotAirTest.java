package nl.mvdr.adventofcode.adventofcode2022.day25;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FullOfHotAir}.
 *
 * @author Martijn van de Rijdt
 */
public class FullOfHotAirTest extends SolverTest<FullOfHotAir> {

    /** Constructor. */
    public FullOfHotAirTest() {
        super(FullOfHotAir.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2=-1=0", "example-day25-2022.txt"),
                Arguments.of("2=001=-2=--0212-22-2", "input-day25-2022.txt"));
    }
}
