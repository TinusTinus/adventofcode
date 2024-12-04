package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ChronalCalibrationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart1Test extends SolverTest<ChronalCalibrationPart1> {

    /** Constructor. */
    public ChronalCalibrationPart1Test() {
        super(ChronalCalibrationPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day01-2018-0.txt"),
                Arguments.of("3", "example-day01-2018-1.txt"),
                Arguments.of("0", "example-day01-2018-2.txt"),
                Arguments.of("-6", "example-day01-2018-3.txt"));
    }
}
