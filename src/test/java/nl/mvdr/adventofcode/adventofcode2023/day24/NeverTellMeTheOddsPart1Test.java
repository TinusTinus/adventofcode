package nl.mvdr.adventofcode.adventofcode2023.day24;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NeverTellMeTheOddsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class NeverTellMeTheOddsPart1Test extends SolverTest<NeverTellMeTheOddsPart1> {

    /** Constructor. */
    public NeverTellMeTheOddsPart1Test() {
        super(NeverTellMeTheOddsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("?", "input-day24-2023.txt"));
    }
    
    @Test
    public void testExample() {
        assertSolution(new NeverTellMeTheOddsPart1(7, 27), "2", "example-day24-2023.txt");
    }
}
