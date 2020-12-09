package nl.mvdr.adventofcode.adventofcode2020.day07;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link HandyHaversacksPart1}.
 *
 * @author Martijn van de Rijdt
 */
public class HandyHaversacksPart1Test extends SolverTest<HandyHaversacksPart1> {

    /** Constructor. */
    public HandyHaversacksPart1Test() {
        super(HandyHaversacksPart1.class);
    }
    
    /**
     * Test case based on an example from the puzzle text.
     * 
     * The following options would be available to you:
     * 
     * <ul>
     * <li>A bright white bag, which can hold your shiny gold bag directly.</li>
     * <li>A muted yellow bag, which can hold your shiny gold bag directly, plus
     * some other bags.</li>
     * <li>A dark orange bag, which can hold bright white and muted yellow bags,
     * either of which could then hold your shiny gold bag.</li>
     * <li>A light red bag, which can hold bright white and muted yellow bags,
     * either of which could then hold your shiny gold bag.</li>
     * </ul>
     * 
     * So, in this example, the number of bag colors that can eventually contain at
     * least one shiny gold bag is 4.
     * 
     */
    @Test
    public void testExample() {
        assertSolution("4", "example-day07-2020.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("348", "input-day07-2020.txt");
    }
}
