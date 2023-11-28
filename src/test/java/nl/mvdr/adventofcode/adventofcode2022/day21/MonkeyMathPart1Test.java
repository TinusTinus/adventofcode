package nl.mvdr.adventofcode.adventofcode2022.day21;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonkeyMathPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonkeyMathPart1Test extends SolverTest<MonkeyMathPart1> {

    /** Constructor. */
    public MonkeyMathPart1Test() {
        super(MonkeyMathPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("152", "example-day21-2022.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("256997859093114", "input-day21-2022.txt");
    }
}
