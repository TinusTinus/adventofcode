package nl.mvdr.adventofcode.adventofcode2018.day13;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test for {@link MineCartMadnessPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MineCartMadnessPart1Test extends SolverTest<MineCartMadnessPart1> {
    /** Constructor. */
    public MineCartMadnessPart1Test() {
        super(MineCartMadnessPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0,3", "example-day13-2018-0.txt"),
                Arguments.of("0,3", "example-day13-2018-1.txt"),
                Arguments.of("7,3", "example-day13-2018-2.txt"),
                Arguments.of("7,3", "example-day13-2018-3.txt"));
    }
}
