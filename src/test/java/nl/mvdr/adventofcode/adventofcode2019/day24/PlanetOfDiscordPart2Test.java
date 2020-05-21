package nl.mvdr.adventofcode.adventofcode2019.day24;

import org.junit.jupiter.api.Test;

import nl.mvdr.adventofcode.SolverTest;

/**
 * Unit test cases for {@link PlanetOfDiscordPart2}.
 *
 * @author Martijn van de Rijdt
 */
public class PlanetOfDiscordPart2Test extends SolverTest<PlanetOfDiscordPart2> {

    /** Constructor. */
    public PlanetOfDiscordPart2Test() {
        super(PlanetOfDiscordPart2.class);
    }
    
    /** Test case based on an example from the puzzle text. */
    @Test
    public void testExample() {
        assertSolution(new PlanetOfDiscordPart2(10), "99", "example-day24-2019-0.txt");
    }
    
    /** Test case based on the accepted solution. */
    @Test
    public void testSolution() {
        assertSolution("?", "input-day24-2019.txt"); 
    }
}
