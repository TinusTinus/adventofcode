package nl.mvdr.adventofcode.adventofcode2023.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ScratchcardsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ScratchcardsPart1Test extends SolverTest<ScratchcardsPart1> {

    /** Constructor. */
    public ScratchcardsPart1Test() {
        super(ScratchcardsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("13", "example-day04-2023.txt"),
                Arguments.of("?", "input-day04-2023.txt"));
    }
}
