package nl.mvdr.adventofcode.adventofcode2019.day23;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link CategorySixPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class CategorySixPart1Test extends SolverTest<CategorySixPart1> {

    /** Constructor. */
    public CategorySixPart1Test() {
        super(CategorySixPart1.class);
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("26744", "input-day23-2019.txt"));
    }
}
