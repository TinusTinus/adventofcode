package nl.mvdr.adventofcode.adventofcode2019.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SensorBoostPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class SensorBoostPart2Test extends SolverTest<SensorBoostPart2> {

    /** Constructor. */
    public SensorBoostPart2Test() {
        super(SensorBoostPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("86025", "input-day09-2019.txt"));
    }
}
