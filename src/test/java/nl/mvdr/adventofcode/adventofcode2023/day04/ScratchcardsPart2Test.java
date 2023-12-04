package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ScratchcardsPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ScratchcardsPart2Test extends SolverTest<ScratchcardsPart2> {

    /** Constructor. */
    public ScratchcardsPart2Test() {
        super(ScratchcardsPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("30", "example-day04-2023.txt"),
                Arguments.of("14427616", "input-day04-2023.txt"));
    }
}
