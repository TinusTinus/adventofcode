package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link PermutationPromenadePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PermutationPromenadePart1Test extends SolverTest<PermutationPromenadePart1> {

    /** Constructor. */
    public PermutationPromenadePart1Test() {
        super(PermutationPromenadePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new PermutationPromenadePart1(5), "baedc", "example-day16-2017.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("paedcbfghijklmno", "example-day16-2017.txt"));
    }
}
