package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CarePackagePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart2Test extends SolverTest<CarePackagePart2> {

    /** Constructor. */
    public CarePackagePart2Test() {
        super(CarePackagePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("17336", "input-day13-2019.txt"));
    }
}
