package nl.mvdr.adventofcode.adventofcode2022.day15;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link BeaconExclusionZonePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class BeaconExclusionZonePart1Test extends SolverTest<BeaconExclusionZonePart1> {

    /** Constructor. */
    public BeaconExclusionZonePart1Test() {
        super(BeaconExclusionZonePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day15-2022.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new BeaconExclusionZonePart1(10), "26", "example-day15-2022.txt");
    }
}
