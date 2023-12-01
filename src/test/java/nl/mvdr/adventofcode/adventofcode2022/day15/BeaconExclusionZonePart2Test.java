package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BeaconExclusionZonePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart2Test extends SolverTest<BeaconExclusionZonePart2> {

    /** Constructor. */
    public BeaconExclusionZonePart2Test() {
        super(BeaconExclusionZonePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("12691026767556", "input-day15-2022.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new BeaconExclusionZonePart2(20), "56000011", "example-day15-2022.txt");
    }
}
