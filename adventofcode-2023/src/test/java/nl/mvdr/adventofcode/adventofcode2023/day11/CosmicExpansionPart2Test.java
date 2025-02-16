package nl.mvdr.adventofcode.adventofcode2023.day11;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import nl.mvdr.adventofcode.solver.SolverTest;

/**
 * Unit test cases for {@link CosmicExpansionPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class CosmicExpansionPart2Test extends SolverTest<CosmicExpansionPart2> {

    /** Constructor. */
    public CosmicExpansionPart2Test() {
        super(CosmicExpansionPart2.class);
    }
    
    @Test
    public void testExample2() {
        assertSolution(new CosmicExpansionPart2(2), "374", "example-day11-2023.txt");
    }
    
    @Test
    public void testExample10() {
        assertSolution(new CosmicExpansionPart2(10), "1030", "example-day11-2023.txt");
    }
    
    @Test
    public void testExample100() {
        assertSolution(new CosmicExpansionPart2(100), "8410", "example-day11-2023.txt");
    }
    
    /** @return arguments for {@link SolverTest#testSolution(String, String)} */
    static Stream<Arguments> testSolution() {
        return Stream.of(
                Arguments.of("82000210", "example-day11-2023.txt"));
    }
}
