package nl.mvdr.adventofcode.adventofcode2017.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ParticleSwarmPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ParticleSwarmPart1Test extends SolverTest<ParticleSwarmPart1> {

    /** Constructor. */
    public ParticleSwarmPart1Test() {
        super(ParticleSwarmPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("0", "example-day20-2017-0.txt"),
                Arguments.of("119", "input-day20-2017.txt"));
    }
}
