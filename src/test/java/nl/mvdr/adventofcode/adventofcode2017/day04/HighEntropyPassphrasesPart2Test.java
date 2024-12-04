package nl.mvdr.adventofcode.adventofcode2017.day04;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HighEntropyPassphrasesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HighEntropyPassphrasesPart2Test extends SolverTest<HighEntropyPassphrasesPart2> {

    /** Constructor. */
    public HighEntropyPassphrasesPart2Test() {
        super(HighEntropyPassphrasesPart2.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3", "example-day04-2017-4.txt"));
    }
}
