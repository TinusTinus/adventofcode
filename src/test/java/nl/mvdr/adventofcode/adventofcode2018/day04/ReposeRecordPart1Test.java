package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Test class for {@link ReposeRecordPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart1Test extends SolverTest<ReposeRecordPart1> {
    /** Constructor. */
    public ReposeRecordPart1Test() {
        super(ReposeRecordPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("240", "example-day04-2018.txt"),
                Arguments.of("240", "example-day04-2018-jumbled.txt"));
    }
}
