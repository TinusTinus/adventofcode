package nl.mvdr.adventofcode.adventofcode2022.day11;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyInTheMiddlePart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyInTheMiddlePart1Test extends SolverTest<MonkeyInTheMiddlePart1> {

    /** Constructor. */
    public MonkeyInTheMiddlePart1Test() {
        super(MonkeyInTheMiddlePart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("10605", "example-day11-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("50830", "input-day11-2022.txt");
    }
}
