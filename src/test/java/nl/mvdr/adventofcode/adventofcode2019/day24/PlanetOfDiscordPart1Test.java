package nl.mvdr.adventofcode.adventofcode2019.day24;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PlanetOfDiscordPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class PlanetOfDiscordPart1Test extends SolverTest<PlanetOfDiscordPart1> {

    /** Constructor. */
    public PlanetOfDiscordPart1Test() {
        super(PlanetOfDiscordPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2129920", "example-day24-2019-0.txt"),
                Arguments.of("2129920", "example-day24-2019-1.txt"),
                Arguments.of("2129920", "example-day24-2019-2.txt"),
                Arguments.of("2129920", "example-day24-2019-3.txt"),
                Arguments.of("2129920", "example-day24-2019-4.txt"),
                Arguments.of("2129920", "example-day24-2019-5.txt"),
                Arguments.of("28778811", "input-day24-2019.txt"));
    }
}
