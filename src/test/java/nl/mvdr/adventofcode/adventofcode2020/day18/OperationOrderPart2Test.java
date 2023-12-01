package nl.mvdr.adventofcode.adventofcode2020.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OperationOrderPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class OperationOrderPart2Test extends SolverTest<OperationOrderPart2> {

    /** Constructor. */
    public OperationOrderPart2Test() {
        super(OperationOrderPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("231", "example-day18-2020-0.txt"),
                Arguments.of("51", "example-day18-2020-1.txt"),
                Arguments.of("46", "example-day18-2020-2.txt"),
                Arguments.of("1445", "example-day18-2020-3.txt"),
                Arguments.of("669060", "example-day18-2020-4.txt"),
                Arguments.of("23340", "example-day18-2020-5.txt"),
                Arguments.of("694173", "example-day18-2020-6.txt"),
                Arguments.of("231235959382961", "input-day18-2020.txt"));
    }
}
