package nl.mvdr.adventofcode.adventofcode2020.day19;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link MonsterMessagesPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class MonsterMessagesPart1Test extends SolverTest<MonsterMessagesPart1> {

    /** Constructor. */
    public MonsterMessagesPart1Test() {
        super(MonsterMessagesPart1.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample0() {
        assertSolution("2", "example-day19-2020-0.txt");
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample1() {
        assertSolution("3", "example-day19-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("279", "input-day19-2020.txt");
    }
}
