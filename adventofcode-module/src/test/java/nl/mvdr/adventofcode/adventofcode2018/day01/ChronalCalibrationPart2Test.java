package nl.mvdr.adventofcode.adventofcode2018.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ChronalCalibrationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ChronalCalibrationPart2Test extends SolverTest<ChronalCalibrationPart2> {

    /** Constructor. */
    public ChronalCalibrationPart2Test() {
        super(ChronalCalibrationPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day01-2018-0.txt"),
                Arguments.of("0", "example-day01-2018-4.txt"),
                Arguments.of("10", "example-day01-2018-5.txt"),
                Arguments.of("5", "example-day01-2018-6.txt"),
                Arguments.of("14", "example-day01-2018-7.txt"));
    }
}
