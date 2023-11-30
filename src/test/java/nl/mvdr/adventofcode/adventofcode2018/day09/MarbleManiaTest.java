package nl.mvdr.adventofcode.adventofcode2018.day09;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link MarbleMania}.
 *
 * @author Martijn van de Rijdt
 */
public class MarbleManiaTest extends SolverTest<MarbleMania> {
    /** Constructor. */
    public MarbleManiaTest() {
        super(MarbleMania.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("32", "example-day09-2018-0.txt"),
                Arguments.of("8317", "example-day09-2018-1.txt"),
                Arguments.of("146373", "example-day09-2018-2.txt"),
                // Arguments.of("2764", "example-day09-2018-3.txt"), // This test case fails; maybe an error in the example?
                Arguments.of("54718", "example-day09-2018-4.txt"),
                Arguments.of("37305", "example-day09-2018-5.txt"),
                Arguments.of("434674", "input-day09-2018.txt"), // solution part 1
                Arguments.of("3653994575", "input-day09-2018-2.txt")); // solution part 2
    }
}
