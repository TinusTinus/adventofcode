package nl.mvdr.adventofcode.adventofcode2020.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonsterMessagesPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart2Test extends SolverTest<MonsterMessagesPart2> {

    /** Constructor. */
    public MonsterMessagesPart2Test() {
        super(MonsterMessagesPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        testSolution("12", "example-day19-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("384", "input-day19-2020.txt");
    }
}
