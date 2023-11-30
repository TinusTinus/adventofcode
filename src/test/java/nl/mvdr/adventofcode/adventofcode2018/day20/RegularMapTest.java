package nl.mvdr.adventofcode.adventofcode2018.day20;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link RegularMap}.
 *
 * @author Martijn van de Rijdt
 */
public class RegularMapTest extends SolverTest<RegularMap> {
    /** Constructor. */
    public RegularMapTest() {
        super(RegularMap.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("Part 1: 3, part 2: 0", "example-day20-2018-0.txt"),
                Arguments.of("Part 1: 10, part 2: 0", "example-day20-2018-1.txt"),
                Arguments.of("Part 1: 18, part 2: 0", "example-day20-2018-2.txt"),
                Arguments.of("Part 1: 23, part 2: 0", "example-day20-2018-3.txt"),
                Arguments.of("Part 1: 31, part 2: 0", "example-day20-2018-4.txt"),
                Arguments.of("Part 1: 3502, part 2: 8000", "input-day20-2018.txt"));
    }
}
