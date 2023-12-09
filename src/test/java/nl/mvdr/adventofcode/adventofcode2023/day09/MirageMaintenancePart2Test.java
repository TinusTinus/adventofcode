package nl.mvdr.adventofcode.adventofcode2023.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MirageMaintenancePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MirageMaintenancePart2Test extends SolverTest<MirageMaintenancePart2> {

    /** Constructor. */
    public MirageMaintenancePart2Test() {
        super(MirageMaintenancePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day09-2023.txt"),
                Arguments.of("933", "input-day09-2023.txt"));
    }
}
