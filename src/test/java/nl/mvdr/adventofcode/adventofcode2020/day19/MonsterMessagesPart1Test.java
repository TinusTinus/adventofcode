package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonsterMessagesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart1Test extends SolverTest<MonsterMessagesPart1> {

    /** Constructor. */
    public MonsterMessagesPart1Test() {
        super(MonsterMessagesPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("2", "example-day19-2020-0.txt"),
                Arguments.of("3", "example-day19-2020-1.txt"));
    }
}
