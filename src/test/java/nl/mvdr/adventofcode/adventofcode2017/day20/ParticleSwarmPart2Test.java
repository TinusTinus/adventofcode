package nl.mvdr.adventofcode.adventofcode2017.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ParticleSwarmPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart2Test extends SolverTest<ParticleSwarmPart2> {

    /** Constructor. */
    public ParticleSwarmPart2Test() {
        super(ParticleSwarmPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day20-2017-0.txt"),
                Arguments.of("1", "example-day20-2017-1.txt"));
    }
}
