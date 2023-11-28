package nl.mvdr.adventofcode.adventofcode2020.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandyHaversacksPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class HandyHaversacksPart2Test extends SolverTest<HandyHaversacksPart2> {

    /** Constructor. */
    public HandyHaversacksPart2Test() {
        super(HandyHaversacksPart2.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * A single shiny gold bag must contain 1 dark olive bag (and the 7 bags within
     * it) plus 2 vibrant plum bags (and the 11 bags within each of those):
     * 1 + 1*7 + 2 + 2*11 = 32 bags!
     */
    @Test
    public void testExample0() {
        testSolution("32", "example-day07-2020-0.txt");
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * In this example, a single shiny gold bag must contain 126 other bags.
     */
    @Test
    public void testExample1() {
        testSolution("126", "example-day07-2020-1.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        testSolution("18885", "input-day07-2020.txt");
    }
}
