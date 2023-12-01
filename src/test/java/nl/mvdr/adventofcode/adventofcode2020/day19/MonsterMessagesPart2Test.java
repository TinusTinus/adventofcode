package nl.mvdr.adventofcode.adventofcode2020.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonsterMessagesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart2Test extends SolverTest<MonsterMessagesPart2> {

    /** Constructor. */
    public MonsterMessagesPart2Test() {
        super(MonsterMessagesPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("12", "example-day19-2020-1.txt"),
                Arguments.of("384", "input-day19-2020.txt"));
    }
}
