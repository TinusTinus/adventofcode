package nl.mvdr.adventofcode.adventofcode2017.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DiskDefragmentationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart2Test extends SolverTest<DiskDefragmentationPart2> {

    /** Constructor. */
    public DiskDefragmentationPart2Test() {
        super(DiskDefragmentationPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1242", "example-day14-2017.txt"));
    }
}
