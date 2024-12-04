package nl.mvdr.adventofcode.adventofcode2022.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CampCleanupPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CampCleanupPart1Test extends SolverTest<CampCleanupPart1> {

    /** Constructor. */
    public CampCleanupPart1Test() {
        super(CampCleanupPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day04-2022.txt"));
    }
}
