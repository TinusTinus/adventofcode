package nl.mvdr.adventofcode.adventofcode2020.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ReportRepairPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class ReportRepairPart2Test extends SolverTest<ReportRepairPart2> {

    /** Constructor. */
    public ReportRepairPart2Test() {
        super(ReportRepairPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("241861950", "example-day01-2020.txt"),
                Arguments.of("232508760", "input-day01-2020.txt"));
    }
}
