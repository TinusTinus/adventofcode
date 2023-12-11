package nl.mvdr.adventofcode.adventofcode2023.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CosmicExpansionPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CosmicExpansionPart1Test extends SolverTest<CosmicExpansionPart1> {

    /** Constructor. */
    public CosmicExpansionPart1Test() {
        super(CosmicExpansionPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("374", "example-day11-2023.txt"),
                Arguments.of("9550717", "input-day11-2023.txt"));
    }
}
