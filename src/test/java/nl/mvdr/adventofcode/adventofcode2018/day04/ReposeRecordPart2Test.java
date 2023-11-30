package nl.mvdr.adventofcode.adventofcode2018.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Test class for {@link ReposeRecordPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ReposeRecordPart2Test extends SolverTest<ReposeRecordPart2> {
    /** Constructor. */
    public ReposeRecordPart2Test() {
        super(ReposeRecordPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4455", "example-day04-2018.txt"),
                Arguments.of("4455", "example-day04-2018-jumbled.txt"),
                Arguments.of("96951", "input-day04-2018.txt"));
    }
}
