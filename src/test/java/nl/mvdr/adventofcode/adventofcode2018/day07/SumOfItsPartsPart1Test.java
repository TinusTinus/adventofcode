package nl.mvdr.adventofcode.adventofcode2018.day07;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test for {@link SumOfItsPartsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class SumOfItsPartsPart1Test extends SolverTest<SumOfItsPartsPart1> {
    /** Constructor. */
    public SumOfItsPartsPart1Test() {
        super(SumOfItsPartsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("CABDFE", "example-day07-2018.txt"),
                Arguments.of("PFKQWJSVUXEMNIHGTYDOZACRLB", "input-day07-2018.txt"));
    }
}
