package nl.mvdr.adventofcode.adventofcode2019.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link SensorBoostPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SensorBoostPart1Test extends SolverTest<SensorBoostPart1> {

    /** Constructor. */
    public SensorBoostPart1Test() {
        super(SensorBoostPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3638931938", "input-day09-2019.txt"));
    }
}
