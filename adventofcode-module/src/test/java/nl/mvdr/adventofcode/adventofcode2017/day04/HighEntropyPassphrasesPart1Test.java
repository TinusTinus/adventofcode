package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link HighEntropyPassphrasesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart1Test extends SolverTest<HighEntropyPassphrasesPart1> {

    /** Constructor. */
    public HighEntropyPassphrasesPart1Test() {
        super(HighEntropyPassphrasesPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1", "example-day04-2017-0.txt"),
                Arguments.of("0", "example-day04-2017-1.txt"),
                Arguments.of("1", "example-day04-2017-2.txt"),
                Arguments.of("2", "example-day04-2017-3.txt"));
    }
}
