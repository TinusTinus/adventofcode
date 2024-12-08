package nl.mvdr.adventofcode.adventofcode2023.day05;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link IfYouGiveASeedAFertilizerPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class IfYouGiveASeedAFertilizerPart1Test extends SolverTest<IfYouGiveASeedAFertilizerPart1> {

    /** Constructor. */
    public IfYouGiveASeedAFertilizerPart1Test() {
        super(IfYouGiveASeedAFertilizerPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("35", "example-day05-2023.txt"));
    }
}
