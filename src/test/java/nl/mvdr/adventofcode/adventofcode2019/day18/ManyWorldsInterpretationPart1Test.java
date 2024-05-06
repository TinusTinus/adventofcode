package nl.mvdr.adventofcode.adventofcode2019.day18;

import nl.mvdr.adventofcode.SolverTest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

/**
 * Unit test cases for {@link ManyWorldsInterpretationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class ManyWorldsInterpretationPart1Test extends SolverTest<ManyWorldsInterpretationPart1> {

    /** Constructor. */
    public ManyWorldsInterpretationPart1Test() {
        super(ManyWorldsInterpretationPart1.class);
    }

    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("8", "example-day18-2019-0.txt"),
                Arguments.of("6", "example-day18-2019-1.txt"),
                Arguments.of("0", "example-day18-2019-2.txt"),
                Arguments.of("86", "example-day18-2019-3.txt"),
                Arguments.of("132", "example-day18-2019-4.txt"),
                Arguments.of("136", "example-day18-2019-5.txt"),
                Arguments.of("81", "example-day18-2019-6.txt"),
                Arguments.of("?", "input-day18-2019.txt"));
    }
}
