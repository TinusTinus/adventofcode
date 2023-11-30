package nl.mvdr.adventofcode.adventofcode2019.day10;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonitoringStationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonitoringStationPart1Test extends SolverTest<MonitoringStationPart1> {

    /** Constructor. */
    public MonitoringStationPart1Test() {
        super(MonitoringStationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8", "example-day10-2019-0.txt"),
                Arguments.of("33", "example-day10-2019-1.txt"),
                Arguments.of("35", "example-day10-2019-2.txt"),
                Arguments.of("41", "example-day10-2019-3.txt"),
                Arguments.of("210", "example-day10-2019-4.txt"),
                Arguments.of("256", "input-day10-2019.txt"));
    }
}
