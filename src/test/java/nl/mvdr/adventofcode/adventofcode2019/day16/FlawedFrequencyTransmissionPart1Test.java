package nl.mvdr.adventofcode.adventofcode2019.day16;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link FlawedFrequencyTransmissionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class FlawedFrequencyTransmissionPart1Test extends SolverTest<FlawedFrequencyTransmissionPart1> {

    /** Constructor. */
    public FlawedFrequencyTransmissionPart1Test() {
        super(FlawedFrequencyTransmissionPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0After0Phases() {
        assertSolution(new FlawedFrequencyTransmissionPart1(0), "12345678", "example-day16-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0After1Phase() {
        assertSolution(new FlawedFrequencyTransmissionPart1(1), "48226158", "example-day16-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0After2Phases() {
        assertSolution(new FlawedFrequencyTransmissionPart1(2), "34040438", "example-day16-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0After3Phases() {
        assertSolution(new FlawedFrequencyTransmissionPart1(3), "03415518", "example-day16-2019-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0After4Phases() {
        assertSolution(new FlawedFrequencyTransmissionPart1(4), "01029498", "example-day16-2019-0.txt");
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("24176176", "example-day16-2019-1.txt"),
                Arguments.of("73745418", "example-day16-2019-2.txt"),
                Arguments.of("52432133", "example-day16-2019-3.txt"));
    }
}
