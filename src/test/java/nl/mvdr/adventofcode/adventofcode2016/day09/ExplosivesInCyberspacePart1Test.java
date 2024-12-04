package nl.mvdr.adventofcode.adventofcode2016.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ExplosivesInCyberspacePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ExplosivesInCyberspacePart1Test extends SolverTest<ExplosivesInCyberspacePart1> {

    /** Constructor. */
    public ExplosivesInCyberspacePart1Test() {
        super(ExplosivesInCyberspacePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("6", "example-day09-2016-0.txt"),
                Arguments.of("7", "example-day09-2016-1.txt"),
                Arguments.of("9", "example-day09-2016-2.txt"),
                Arguments.of("11", "example-day09-2016-3.txt"),
                Arguments.of("6", "example-day09-2016-4.txt"),
                Arguments.of("18", "example-day09-2016-5.txt"));
    }
}
