package nl.mvdr.adventofcode.adventofcode2016.day01;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link TaxicabPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class TaxicabPart1Test extends SolverTest<TaxicabPart1> {

    /** Constructor. */
    public TaxicabPart1Test() {
        super(TaxicabPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("5", "example-day01-2016-0.txt"),
                Arguments.of("2", "example-day01-2016-1.txt"),
                Arguments.of("12", "example-day01-2016-2.txt"),
                Arguments.of("230", "input-day01-2016.txt"));
    }
}
