package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PlanetOfDiscordPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PlanetOfDiscordPart2Test extends SolverTest<PlanetOfDiscordPart2> {

    /** Constructor. */
    public PlanetOfDiscordPart2Test() {
        super(PlanetOfDiscordPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new PlanetOfDiscordPart2(10), "99", "example-day24-2019-0.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("1922", "example-day24-2019-0.txt"));
    }
}
