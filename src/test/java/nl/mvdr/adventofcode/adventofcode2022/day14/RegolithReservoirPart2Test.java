package nl.mvdr.adventofcode.adventofcode2022.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegolithReservoirPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class RegolithReservoirPart2Test extends SolverTest<RegolithReservoirPart2> {

    /** Constructor. */
    public RegolithReservoirPart2Test() {
        super(RegolithReservoirPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("93", "example-day14-2022.txt"));
    }
}
