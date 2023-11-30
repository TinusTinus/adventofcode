package nl.mvdr.adventofcode.adventofcode2019.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ProgramAlarmPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ProgramAlarmPart1Test extends SolverTest<ProgramAlarmPart1> {

    /** Constructor. */
    public ProgramAlarmPart1Test() {
        super(ProgramAlarmPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("9581917", "input-day02-2019.txt"));
    }
}
