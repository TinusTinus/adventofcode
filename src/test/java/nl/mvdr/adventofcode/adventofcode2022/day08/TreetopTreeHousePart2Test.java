package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TreetopTreeHousePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TreetopTreeHousePart2Test extends SolverTest<TreetopTreeHousePart2> {

    /** Constructor. */
    public TreetopTreeHousePart2Test() {
        super(TreetopTreeHousePart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8", "example-day08-2022.txt"));
    }
}
