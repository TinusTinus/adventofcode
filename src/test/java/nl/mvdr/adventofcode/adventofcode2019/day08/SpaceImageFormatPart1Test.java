package nl.mvdr.adventofcode.adventofcode2019.day08;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link AmplificationCircuitPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SpaceImageFormatPart1Test extends SolverTest<SpaceImageFormatPart1> {

    /** Constructor. */
    public SpaceImageFormatPart1Test() {
        super(SpaceImageFormatPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2460", "input-day08-2019.txt"));
    }
    
    /** Test case based on the example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new SpaceImageFormatPart1(3, 2), "1", "example-day08-2019-0.txt");
    }
}
