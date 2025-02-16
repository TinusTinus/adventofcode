package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ParabolicReflectorDishPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ParabolicReflectorDishPart2Test extends SolverTest<ParabolicReflectorDishPart2> {

    /** Constructor. */
    public ParabolicReflectorDishPart2Test() {
        super(ParabolicReflectorDishPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("64", "example-day14-2023.txt"));
    }
}
