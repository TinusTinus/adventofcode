package nl.mvdr.adventofcode.adventofcode2020.day18;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link OperationOrderPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class OperationOrderPart1Test extends SolverTest<OperationOrderPart1> {

    /** Constructor. */
    public OperationOrderPart1Test() {
        super(OperationOrderPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("71", "example-day18-2020-0.txt"),
                Arguments.of("51", "example-day18-2020-1.txt"),
                Arguments.of("26", "example-day18-2020-2.txt"),
                Arguments.of("437", "example-day18-2020-3.txt"),
                Arguments.of("12240", "example-day18-2020-4.txt"),
                Arguments.of("13632", "example-day18-2020-5.txt"),
                Arguments.of("26457", "example-day18-2020-6.txt"),
                Arguments.of("8929569623593", "input-day18-2020.txt"));
    }
}
