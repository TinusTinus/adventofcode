package nl.mvdr.adventofcode.adventofcode2020.day09;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link EncodingErrorPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class EncodingErrorPart2Test extends SolverTest<EncodingErrorPart2> {

    /** Constructor. */
    public EncodingErrorPart2Test() {
        super(EncodingErrorPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("20532569", "input-day09-2020.txt"));
    }
    
    /**
     * Test case based on an example from the puzzle text.
     */
    @Test
    public void testExample() {
        assertSolution(new EncodingErrorPart2(5), "62", "example-day09-2020.txt");
    }
}
