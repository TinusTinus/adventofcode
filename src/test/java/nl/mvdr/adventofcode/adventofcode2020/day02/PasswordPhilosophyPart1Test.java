package nl.mvdr.adventofcode.adventofcode2020.day02;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PasswordPhilosophyPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PasswordPhilosophyPart1Test extends SolverTest<PasswordPhilosophyPart1> {

    /** Constructor. */
    public PasswordPhilosophyPart1Test() {
        super(PasswordPhilosophyPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day02-2020.txt"),
                Arguments.of("548", "input-day02-2020.txt"));
    }
}
