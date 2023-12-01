package nl.mvdr.adventofcode.adventofcode2020.day09;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link EncodingErrorPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class EncodingErrorPart1Test extends SolverTest<EncodingErrorPart1> {

    /** Constructor. */
    public EncodingErrorPart1Test() {
        super(EncodingErrorPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("144381670", "input-day09-2020.txt"));
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this example, after the 5-number preamble, almost every number is the sum
     * of two of the previous 5 numbers; the only number that does not follow this
     * rule is 127.
     */
    @Test
    public void testExample() {
        assertSolution(new EncodingErrorPart1(5), "127", "example-day09-2020.txt");
    }
}
