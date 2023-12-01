package nl.mvdr.adventofcode.adventofcode2022.day19;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link NotEnoughMineralsPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class NotEnoughMineralsPart1Test extends SolverTest<NotEnoughMineralsPart1> {

    /** Constructor. */
    public NotEnoughMineralsPart1Test() {
        super(NotEnoughMineralsPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("33", "example-day19-2022.txt"),
                Arguments.of("1480", "input-day19-2022.txt"));
    }
}
