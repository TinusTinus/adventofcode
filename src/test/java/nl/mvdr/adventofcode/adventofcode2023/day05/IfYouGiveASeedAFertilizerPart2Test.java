package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link IfYouGiveASeedAFertilizerPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class IfYouGiveASeedAFertilizerPart2Test extends SolverTest<IfYouGiveASeedAFertilizerPart2> {

    /** Constructor. */
    public IfYouGiveASeedAFertilizerPart2Test() {
        super(IfYouGiveASeedAFertilizerPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("46", "example-day05-2023.txt"));
    }
}
