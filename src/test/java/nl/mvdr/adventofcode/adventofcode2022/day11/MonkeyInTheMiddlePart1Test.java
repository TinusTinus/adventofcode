package nl.mvdr.adventofcode.adventofcode2022.day11;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link MonkeyInTheMiddlePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyInTheMiddlePart1Test extends SolverTest<MonkeyInTheMiddlePart1> {

    /** Constructor. */
    public MonkeyInTheMiddlePart1Test() {
        super(MonkeyInTheMiddlePart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("10605", "example-day11-2022.txt"));
    }
}
