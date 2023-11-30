package nl.mvdr.adventofcode.adventofcode2017.day10;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link KnotHashPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class KnotHashPart1Test extends SolverTest<KnotHashPart1> {

    /** Constructor. */
    public KnotHashPart1Test() {
        super(KnotHashPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1980", "input-day10-2017.txt"));
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new KnotHashPart1(5), "12", "example-day10-2017-0.txt");
    }
}
