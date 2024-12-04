package nl.mvdr.adventofcode.adventofcode2022.day08;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TreetopTreeHousePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TreetopTreeHousePart1Test extends SolverTest<TreetopTreeHousePart1> {

    /** Constructor. */
    public TreetopTreeHousePart1Test() {
        super(TreetopTreeHousePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("21", "example-day08-2022.txt"));
    }
}
