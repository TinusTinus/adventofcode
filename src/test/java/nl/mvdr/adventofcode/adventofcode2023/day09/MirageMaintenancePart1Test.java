package nl.mvdr.adventofcode.adventofcode2023.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MirageMaintenancePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MirageMaintenancePart1Test extends SolverTest<MirageMaintenancePart1> {

    /** Constructor. */
    public MirageMaintenancePart1Test() {
        super(MirageMaintenancePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("114", "example-day09-2023.txt"),
                Arguments.of("1666172641", "input-day09-2023.txt"));
    }
}
