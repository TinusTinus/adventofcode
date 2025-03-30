package nl.mvdr.adventofcode.adventofcode2016.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link TaxicabPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TaxicabPart2Test extends SolverTest<TaxicabPart2> {

    /** Constructor. */
    public TaxicabPart2Test() {
        super(TaxicabPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("4", "example-day01-3.txt"));
    }
}
