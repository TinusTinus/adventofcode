package nl.mvdr.adventofcode.adventofcode2022.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RegolithReservoirPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RegolithReservoirPart1Test extends SolverTest<RegolithReservoirPart1> {

    /** Constructor. */
    public RegolithReservoirPart1Test() {
        super(RegolithReservoirPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("24", "example-day14-2022.txt"),
                Arguments.of("892", "input-day14-2022.txt"));
    }
}
