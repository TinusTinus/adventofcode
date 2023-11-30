package nl.mvdr.adventofcode.adventofcode2017.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PermutationPromenadePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PermutationPromenadePart2Test extends SolverTest<PermutationPromenadePart2> {

    /** Constructor. */
    public PermutationPromenadePart2Test() {
        super(PermutationPromenadePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("abocefghijklmndp", "input-day16-2017.txt"));
    }
}
