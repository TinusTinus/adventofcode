package nl.mvdr.adventofcode.adventofcode2020.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PasswordPhilosophyPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PasswordPhilosophyPart2Test extends SolverTest<PasswordPhilosophyPart2> {

    /** Constructor. */
    public PasswordPhilosophyPart2Test() {
        super(PasswordPhilosophyPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day02-2020.txt"),
                Arguments.of("502", "input-day02-2020.txt"));
    }
}
