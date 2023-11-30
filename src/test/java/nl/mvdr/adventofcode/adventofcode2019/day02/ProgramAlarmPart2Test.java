package nl.mvdr.adventofcode.adventofcode2019.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProgramAlarmPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ProgramAlarmPart2Test extends SolverTest<ProgramAlarmPart2> {

    /** Constructor. */
    public ProgramAlarmPart2Test() {
        super(ProgramAlarmPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2505", "input-day02-2019.txt"));
    }
}
