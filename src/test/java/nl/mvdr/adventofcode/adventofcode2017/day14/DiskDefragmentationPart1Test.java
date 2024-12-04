package nl.mvdr.adventofcode.adventofcode2017.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link DiskDefragmentationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class DiskDefragmentationPart1Test extends SolverTest<DiskDefragmentationPart1> {

    /** Constructor. */
    public DiskDefragmentationPart1Test() {
        super(DiskDefragmentationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8108", "example-day14-2017.txt"));
    }
}
