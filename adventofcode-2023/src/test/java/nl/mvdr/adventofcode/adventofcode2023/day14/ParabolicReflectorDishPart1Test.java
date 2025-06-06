package nl.mvdr.adventofcode.adventofcode2023.day14;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link ParabolicReflectorDishPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ParabolicReflectorDishPart1Test extends SolverTest<ParabolicReflectorDishPart1> {

    /** Constructor. */
    public ParabolicReflectorDishPart1Test() {
        super(ParabolicReflectorDishPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("136", "example-day14-2023.txt"));
    }
}
