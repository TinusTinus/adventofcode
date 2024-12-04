package nl.mvdr.adventofcode.adventofcode2020.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link ReportRepairPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ReportRepairPart1Test extends SolverTest<ReportRepairPart1> {

    /** Constructor. */
    public ReportRepairPart1Test() {
        super(ReportRepairPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                // In this list, the two entries that sum to 2020 are 1721 and 299. Multiplying
                // them together produces 1721 * 299 = 514579, so the correct answer is 514579.
                Arguments.of("514579", "example-day01-2020.txt"));
    }
}
