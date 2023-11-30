package nl.mvdr.adventofcode.adventofcode2019.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CarePackagePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CarePackagePart1Test extends SolverTest<CarePackagePart1> {

    /** Constructor. */
    public CarePackagePart1Test() {
        super(CarePackagePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("344", "input-day13-2019.txt"));
    }
}
