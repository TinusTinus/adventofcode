package nl.mvdr.adventofcode.adventofcode2022.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyInTheMiddlePart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyInTheMiddlePart2Test extends SolverTest<MonkeyInTheMiddlePart2> {

    /** Constructor. */
    public MonkeyInTheMiddlePart2Test() {
        super(MonkeyInTheMiddlePart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("2713310158", "example-day11-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("14399640002", "input-day11-2022.txt");
    }
}
