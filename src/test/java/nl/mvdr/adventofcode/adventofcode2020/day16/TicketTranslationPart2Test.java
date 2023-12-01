package nl.mvdr.adventofcode.adventofcode2020.day16;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TicketTranslationPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class TicketTranslationPart2Test extends SolverTest<TicketTranslationPart2> {

    /** Constructor. */
    public TicketTranslationPart2Test() {
        super(TicketTranslationPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("3173135507987", "input-day16-2020.txt"));
    }
}
