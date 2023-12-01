package nl.mvdr.adventofcode.adventofcode2020.day15;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link RambunctiousRecitationPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class RambunctiousRecitationPart1Test extends SolverTest<RambunctiousRecitationPart1> {

    /** Constructor. */
    public RambunctiousRecitationPart1Test() {
        super(RambunctiousRecitationPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("436", "example-day15-2020-0.txt"),
                Arguments.of("1", "example-day15-2020-1.txt"),
                Arguments.of("10", "example-day15-2020-2.txt"),
                Arguments.of("27", "example-day15-2020-3.txt"),
                Arguments.of("78", "example-day15-2020-4.txt"),
                Arguments.of("438", "example-day15-2020-5.txt"),
                Arguments.of("1836", "example-day15-2020-6.txt"),
                Arguments.of("412", "input-day15-2020.txt"));
    }
}
