package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CategorySixPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CategorySixPart2Test extends SolverTest<CategorySixPart2> {

    /** Constructor. */
    public CategorySixPart2Test() {
        super(CategorySixPart2.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("19498", "input-day23-2019.txt"));
    }
}
