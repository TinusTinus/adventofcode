package nl.mvdr.adventofcode.adventofcode2020.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TicketTranslationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TicketTranslationPart1Test extends SolverTest<TicketTranslationPart1> {

    /** Constructor. */
    public TicketTranslationPart1Test() {
        super(TicketTranslationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("71", "example-day16-2020.txt"));
    }
}
